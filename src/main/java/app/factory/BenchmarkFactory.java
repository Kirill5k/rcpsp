package app.factory;

import app.exception.UnknownProjectName;
import app.project.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Created by Kirill on 16/02/2016.
 */
public class BenchmarkFactory {
    private BenchmarkFactory() {}
    private static final Logger LOG = LoggerFactory.getLogger(BenchmarkFactory.class);

    public static final BenchmarkInstance TEST_BI = createTestBI();

    private final static String PSPLIB_CORE_PATH = "C:\\applications/";
    private final static String PSPLIB_INSTANCES_PATH = PSPLIB_CORE_PATH + "projects/";
    private final static String PSPLIB_SOLUTIONS_PATH = PSPLIB_CORE_PATH + "solutions/";

    private static Map<String, Integer> solutions;
    private static Map<String, BenchmarkInstance> j30;
    private static Map<String, BenchmarkInstance> j60;
    private static Map<String, BenchmarkInstance> j120;

    public static Map<String, Integer> solutions() {
        if (solutions == null){
            solutions = getAllSolutions();
        }
        return solutions;
    }

    public static Map<String, BenchmarkInstance> j30set(){
        if (j30 == null){
            j30 = getInstances(30);
        }
        return j30;
    }

    public static Map<String, BenchmarkInstance> j60set(){
        if (j60 == null){
            j60 = getInstances(60);
        }
        return j60;
    }

    public static Map<String, BenchmarkInstance> j120set(){
        if (j120 == null){
            j120 = getInstances(120);
        }
        return j120;
    }

    private static Map<String, BenchmarkInstance> getInstances(int size) {
        String path = PSPLIB_INSTANCES_PATH + size + "/";
        LOG.info("Scanning {} directory for benchmark instances", path);
        Map<String, BenchmarkInstance> instances = new TreeMap<>();
        File[] files = new File(path).listFiles();
        for (File file : files) {
            String instanceName = file.getName();
            BenchmarkInstance benchmark = get(size, instanceName);
            instances.put(instanceName, benchmark);
        }

        LOG.info("Scanning complete. Found {} instances", instances.size());
        return new TreeMap<>(instances);
    }

    public static BenchmarkInstance get(String projectName){
        switch (projectName.substring(0, 3)){
            case "J30": return get(30, projectName);
            case "J60": return get(60, projectName);
            case "J12": return get(120, projectName);
            default: throw new UnknownProjectName();
        }
    }

    private static BenchmarkInstance get(int size, String projectName) {
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

    private static Map<String, Integer> getAllSolutions() {
        Map<String, Integer> solutions = new HashMap<>();
        solutions.putAll(getSolutions(30));
        solutions.putAll(getSolutions(60));
        solutions.putAll(getSolutions(90));
        solutions.putAll(getSolutions(120));
        return solutions;
    }

    private static Map<String, Integer> getSolutions(int size) {
//        LOG.info("Scanning solutions file in {} for J{} instances", PSPLIB_SOLUTIONS_PATH, size);
        Map<String, Integer> solutions = new HashMap<>();
        List<List<Integer>> rows = readPSPLIBfile(PSPLIB_SOLUTIONS_PATH + "j" + size + ".sm");
        rows.stream().filter(r -> r.size() >= 3).forEach(r -> solutions.put("J" + size + r.get(0) + "_" + r.get(1) + ".RCP", r.get(2)));
        return solutions;
    }

    private static List<List<Integer>> readPSPLIBfile(String path) {
        try {
            return Files.lines(Paths.get(path)).map(splitStringIntoNumbers()).collect(toList());
        } catch (IOException e) {
            LOG.error("Solution or instance file {} could not be found!", path, e);
            throw new RuntimeException(e);
        }
    }

    private static Function<String, List<Integer>> splitStringIntoNumbers(){
        return string -> Arrays.stream(string.split(" ", -1)).filter(s -> s.matches("^-?\\d+$")).map(Integer::parseInt).collect(toList());
    }


    public static List<BenchmarkInstance> randomJ30Instances(int amount) {
        List<BenchmarkInstance> benchmarks = new ArrayList<>(BenchmarkFactory.j30set().values());
        Collections.shuffle(benchmarks);
        return benchmarks.stream().limit(amount).collect(toList());
    }

    public static BenchmarkInstance randomJ30Instance() {
        List<BenchmarkInstance> benchmarks = new ArrayList<>(BenchmarkFactory.j30set().values());
        Collections.shuffle(benchmarks);
        return benchmarks.get(0);
    }

    private static BenchmarkInstance createTestBI() {
        Map<Integer, Integer> resources = new HashMap<>();
        resources.put(1, 10);
        List<Activity> activities = new ArrayList<>();

        Map<Integer, Integer> resReq = new HashMap<>();
        resReq.put(1, 0);
        Activity a0 = new Activity(0, 0, resReq);

        Map<Integer, Integer> resReq1 = new HashMap<>();
        resReq1.put(1, 2);
        Activity a1 = new Activity(1, 2, resReq1);

        Map<Integer, Integer> resReq2 = new HashMap<>();
        resReq2.put(1, 2);
        Activity a2 = new Activity(2, 5, resReq2);

        Map<Integer, Integer> resReq3 = new HashMap<>();
        resReq3.put(1, 6);
        Activity a3 = new Activity(3, 8, resReq3);

        Map<Integer, Integer> resReq4 = new HashMap<>();
        resReq4.put(1, 5);
        Activity a4 = new Activity(4, 1, resReq4);

        Map<Integer, Integer> resReq5 = new HashMap<>();
        resReq5.put(1, 5);
        Activity a5 = new Activity(5, 10, resReq5);

        Map<Integer, Integer> resReq6 = new HashMap<>();
        resReq6.put(1, 3);
        Activity a6 = new Activity(6, 9, resReq6);

        Map<Integer, Integer> resReq7 = new HashMap<>();
        resReq7.put(1, 4);
        Activity a7 = new Activity(7, 2, resReq7);

        Map<Integer, Integer> resReq8 = new HashMap<>();
        resReq8.put(1, 7);
        Activity a8 = new Activity(8, 3, resReq8);

        Map<Integer, Integer> resReq9 = new HashMap<>();
        resReq9.put(1, 3);
        Activity a9 = new Activity(9, 8, resReq9);

        Map<Integer, Integer> resReq10 = new HashMap<>();
        resReq10.put(1, 2);
        Activity a10 = new Activity(10, 6, resReq10);

        Map<Integer, Integer> resReq11 = new HashMap<>();
        resReq11.put(1, 4);
        Activity a11 = new Activity(11, 6, resReq11);

        Map<Integer, Integer> resReq12 = new HashMap<>();
        resReq12.put(1, 4);
        Activity a12 = new Activity(12, 9, resReq12);

        Map<Integer, Integer> resReq13 = new HashMap<>();
        resReq13.put(1, 4);
        Activity a13 = new Activity(13, 2, resReq13);

        Map<Integer, Integer> resReq14 = new HashMap<>();
        resReq14.put(1, 4);
        Activity a14 = new Activity(14, 8, resReq14);

        Map<Integer, Integer> resReq15 = new HashMap<>();
        resReq15.put(1, 1);
        Activity a15 = new Activity(15, 6, resReq15);

        Map<Integer, Integer> resReq16 = new HashMap<>();
        resReq16.put(1, 1);
        Activity a16 = new Activity(16, 10, resReq16);

        Map<Integer, Integer> resReq17 = new HashMap<>();
        resReq17.put(1, 3);
        Activity a17 = new Activity(17, 5, resReq17);

        Map<Integer, Integer> resReq18 = new HashMap<>();
        resReq18.put(1, 2);
        Activity a18 = new Activity(18, 8, resReq18);

        Map<Integer, Integer> resReq19 = new HashMap<>();
        resReq19.put(1, 3);
        Activity a19 = new Activity(19, 3, resReq19);

        Map<Integer, Integer> resReq20 = new HashMap<>();
        resReq20.put(1, 0);
        Activity a20 = new Activity(20, 0, resReq20);

        activities.add(a0);
        activities.add(a1);
        activities.add(a2);
        activities.add(a3);
        activities.add(a7);
        activities.add(a5);
        activities.add(a6);
        activities.add(a13);
        activities.add(a4);
        activities.add(a14);
        activities.add(a9);
        activities.add(a16);
        activities.add(a8);
        activities.add(a17);
        activities.add(a10);
        activities.add(a12);
        activities.add(a15);
        activities.add(a19);
        activities.add(a11);
        activities.add(a18);
        activities.add(a20);

        a0.getSuccessors().add(a1);
        a0.getSuccessors().add(a2);
        a0.getSuccessors().add(a3);
        a0.getSuccessors().add(a7);

        a1.getSuccessors().add(a4);

        a2.getSuccessors().add(a5);
        a2.getSuccessors().add(a6);

        a3.getSuccessors().add(a6);

        a4.getSuccessors().add(a8);

        a5.getSuccessors().add(a8);
        a5.getSuccessors().add(a9);

        a6.getSuccessors().add(a14);

        a7.getSuccessors().add(a13);

        a8.getSuccessors().add(a10);
        a8.getSuccessors().add(a12);

        a9.getSuccessors().add(a12);

        a10.getSuccessors().add(a11);

        a11.getSuccessors().add(a20);

        a12.getSuccessors().add(a18);

        a13.getSuccessors().add(a14);
        a13.getSuccessors().add(a16);

        a14.getSuccessors().add(a15);

        a15.getSuccessors().add(a18);

        a16.getSuccessors().add(a17);

        a17.getSuccessors().add(a19);

        a18.getSuccessors().add(a20);

        a19.getSuccessors().add(a20);

        return new BenchmarkInstance(activities, resources, "Test instance");
    }
}
