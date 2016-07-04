package app.main;

import app.algorithm.AlgorithmType;
import app.algorithm.implementations.Algorithms;
import app.algorithm.CommonOperations;
import app.asset.Activity;
import app.asset.BenchmarkInstance;
import app.asset.EventList;
import app.exceptions.IncorrectAlgorithmTypeException;
import app.utility.Benchmarks;

import java.util.*;

/**
 * Created by Kirill on 20/06/2016.
 */
public class Tests {
    public static final int POPULATION_SIZE = 150;
    public static final double MUTATION_RATE = 0.3;
    public static final int STOP_CRITERION = 1000;

    public static EventList testGA(AlgorithmType type, BenchmarkInstance bi) {
        return CommonOperations.getBestSolution(Algorithms.GA(type, bi, POPULATION_SIZE, STOP_CRITERION, MUTATION_RATE));
    }

    public static void fullTestGA(AlgorithmType type, Set<Map.Entry<String, BenchmarkInstance>> instances) {
        System.out.println("----- " + type + " -----");
        System.out.println();
        int solved = 0;
        int count = 0;
        double dev = 0;
        double devSum = 0;

        for (Map.Entry<String, BenchmarkInstance> inst : instances) {
            String name = inst.getKey();
            System.out.println("#" + (++count) + " " + name);

            BenchmarkInstance bi = inst.getValue();
            EventList el = testGA(type, bi);
            if (el.getMakespan() == Benchmarks.solutions.get(name))
                solved++;

            int optima = Benchmarks.solutions.get(name);
            dev = CommonOperations.getDeviationFromOptima(el.getMakespan(), optima);
            if (dev > 0) {
                System.out.println("Received makespan: " + el.getMakespan() + " | expected makespan:  " + optima);
                System.out.println("Deviation: " + dev);
                devSum += dev;
            }
            System.out.println();
        }

        System.out.println("Solved " + solved + " out of " + instances.size());
        System.out.println("Average dev  " + (devSum/instances.size()));
    }

    public static BenchmarkInstance getTestBI() {
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

        BenchmarkInstance bi = new BenchmarkInstance(activities, resources, "Test instance");

        return bi;
    }
}
