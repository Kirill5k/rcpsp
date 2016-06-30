package app.utility;

import app.asset.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Kirill on 16/02/2016.
 */
public class Benchmarks {
    private Benchmarks() {}

    private final static String PSPLIB_CORE_PATH = "C:\\applications/";
    private final static String PSPLIB_INSTANCES_PATH = PSPLIB_CORE_PATH + "projects/";
    private final static String PSPLIB_SOLUTIONS_PATH = PSPLIB_CORE_PATH + "solutions/";

    public static Map<String, Integer> solutions;
    public static Map<String, BenchmarkInstance> instances30;
    public static Map<String, BenchmarkInstance> instances60;
    public static Map<String, BenchmarkInstance> instances120;

    static {
        try {
            solutions = getAllSolutions();
            instances30 = getInstances(30);
            instances60 = getInstances(60);
            instances120 = getInstances(120);
        } catch (FileNotFoundException e) {
            System.err.println("Solution or instance files could not be found!");
        }
    }

    private static Map<String, BenchmarkInstance> getInstances(int size) throws FileNotFoundException {
        Map<String, BenchmarkInstance> instances = new TreeMap<>();
        File[] files = new File(PSPLIB_INSTANCES_PATH + size + "/").listFiles();

        for (File file : files) {
            String instanceName = file.getName();
            BenchmarkInstance benchmark = Benchmarks.get(size, instanceName);
            instances.put(instanceName, benchmark);
        }
        return new TreeMap<>(instances);
    }

    private static BenchmarkInstance get(int size, String projectName) throws FileNotFoundException {
        List<Activity> activities = new ArrayList<>();
        Map<Integer, Integer> resources = new HashMap<>();
        int number = 0;

        Map<Integer, List<Integer>> successors = new HashMap<>();

        List<List<Integer>> rows = readPSPLIBfile(PSPLIB_INSTANCES_PATH + size + "/" + projectName);
        for(List<Integer> row : rows)
            if (rows.indexOf(row)==1) {
                resources.put(1, row.get(0));
                resources.put(2, row.get(1));
                resources.put(3, row.get(2));
                resources.put(4, row.get(3));
            } else if (rows.indexOf(row)>1){
                int duration = row.get(0);
                Map<Integer, Integer> resourceReq = new HashMap<>();
                if (row.get(1) > 0) resourceReq.put(1, row.get(1));
                if (row.get(2) > 0) resourceReq.put(2, row.get(2));
                if (row.get(3) > 0) resourceReq.put(3, row.get(3));
                if (row.get(4) > 0) resourceReq.put(4, row.get(4));

                successors.put(number, new ArrayList<>());
                for (int i=0; i<row.get(5); i++)
                    successors.get(number).add(row.get(6 + i) - 1);

                activities.add(new Activity(number, duration, resourceReq));
                number++;
            }

        successors.forEach((k, v) -> v.forEach(i -> activities.get(k).getSuccessors().add(activities.get(i))));
        activities.forEach(a -> a.getSuccessors().forEach(s -> s.getPredecessors().add(a)));
        return new BenchmarkInstance(activities, resources, projectName);
    }

    private static Map<String, Integer> getAllSolutions() throws FileNotFoundException {
        Map<String, Integer> solutions = new HashMap<>();
        solutions.putAll(getSolutions(30));
        solutions.putAll(getSolutions(60));
        solutions.putAll(getSolutions(90));
        solutions.putAll(getSolutions(120));
        return solutions;
    }

    private static Map<String, Integer> getSolutions(int size) throws FileNotFoundException {
        Map<String, Integer> solutions = new HashMap<>();
        List<List<Integer>> rows = readPSPLIBfile(PSPLIB_SOLUTIONS_PATH + "j" + size + ".sm");
        rows.stream().filter(r -> r.size() >= 3).forEach(r -> solutions.put("J" + size + r.get(0) + "_" + r.get(1) + ".RCP", r.get(2)));
        return solutions;
    }

    private static List<List<Integer>> readPSPLIBfile(String path) throws FileNotFoundException {
        List<List<Integer>> rows = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))){
            while (scanner.hasNext()) {
                List<Integer> row = new ArrayList<>();
                String[] data = scanner.nextLine().split(" ", -1);

                for (String s : data)
                    if (s.matches("^-?\\d+$"))
                        row.add(Integer.parseInt(s));
                rows.add(row);
            }
        }

        return rows;
    }

    public static List<BenchmarkInstance> getRandomInstance30(int amount) {
        List<BenchmarkInstance> benchmarks = new ArrayList<>(Benchmarks.instances30.values());
        Collections.shuffle(benchmarks);
        while (benchmarks.size() > amount)
            benchmarks.remove(0);

        return benchmarks;
    }

    public static BenchmarkInstance getRandomInstance30() {
        List<BenchmarkInstance> benchmarks = new ArrayList<>(Benchmarks.instances30.values());
        Collections.shuffle(benchmarks);
        return benchmarks.get(0);
    }
}
