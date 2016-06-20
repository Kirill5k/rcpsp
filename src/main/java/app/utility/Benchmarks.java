package app.utility;

import app.asset.Activity;
import app.asset.ActivityList;
import app.asset.BenchmarkInstance;
import app.asset.EventList;

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

                Activity activity = new Activity(number, duration, resourceReq);
                activities.add(activity);
                number++;
            }

        for (Map.Entry<Integer, List<Integer>> e : successors.entrySet())
            for (Integer i : e.getValue())
                activities.get(e.getKey()).getSuccessors().add(activities.get(i));

        return new BenchmarkInstance(projectName, resources, activities);
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

        for (List<Integer> row : rows) {
            if (row.size() < 3)
                continue;

            String name = "J" + size + row.get(0) + "_" + row.get(1) + ".RCP";
            solutions.put(name, row.get(2));
        }

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

    /*
    --------------------------------------------- LIST ----------------------------------------------------------
     */
    public static ActivityList asActivityList(BenchmarkInstance bi) {
        return new ActivityList(bi.getResources(), bi.getActivities());
    }

    public static EventList asEventList(BenchmarkInstance bi) {
        return new EventList(bi.getResources(), bi.getActivities());
    }

    public static ActivityList asRandomActivityList(BenchmarkInstance bi) {
        return new ActivityList(bi.getResources(), randomise(bi.getActivities()));
    }

    public static EventList asRandomEventList(BenchmarkInstance bi) {
        return new EventList(bi.getResources(), randomise(bi.getActivities()));
    }

    private static List<Activity> randomise(List<Activity> activities) {
        List<Activity> result = new ArrayList<>();

        List<Activity> dependencies = new ArrayList<>();
        for (Activity a : activities)
            if (a.getNumber()==0)
                result.add(a);
            else
                dependencies.addAll(a.getSuccessors());

        while (activities.size() != result.size()) {
            Activity a = activities.get((int)(Math.random() * activities.size()));
            if (!result.contains(a)) {
                if (!dependencies.contains(a)) {
                    result.add(a);
                    a.getSuccessors().forEach(dependencies::remove);
                }
            }
        }

        return result;
    }
    /*
    ----------------------------------------------------------------------------------------------------------------------
     */
}
