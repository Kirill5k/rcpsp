package app.algorithm;

import app.algorithm.implementations.*;
import app.asset.BenchmarkInstance;
import app.asset.EventList;

import java.util.List;

/**
 * Created by Kirill on 23/02/2016.
 */
public class Algorithms {
//
//    public static List<EventList> parallelSCGA(BenchmarkInstance bi, int popSize, int stopCrit, double mutRate, int speciesDist) {
//        Algorithm alg = new ParallelSpeciesConservingGeneticAlgorithm(bi, popSize, stopCrit, mutRate, speciesDist);
//        return solve(alg);
//    }
//
//    public static List<EventList> normalSCGA(BenchmarkInstance bi, int popSize, int stopCrit, double mutRate, int speciesDist) {
//        Algorithm alg = new SpeciesConservingGeneticAlgorithm(bi, popSize, stopCrit, mutRate, speciesDist);
//        return solve(alg);
//    }
//
    public static List<EventList> parallelGA(BenchmarkInstance bi, int popSize, int stopCrit, double mutRate) {
        Algorithm alg = new ParallelGeneticAlgorithm(bi, popSize, stopCrit, mutRate);
        return solve(alg);
    }

    public static List<EventList> normalGA(BenchmarkInstance bi, int popSize, int stopCrit, double mutRate) {
        Algorithm alg = new GeneticAlgorithm(bi, popSize, stopCrit, mutRate);
        return solve(alg);
    }

//    public static List<EventList> testGA(BenchmarkInstance bi, int popSize, int stopCrit, double mutRate, int speciesSize) {
//        Algorithm alg = new TestGeneticAlgorithm(bi, popSize, stopCrit, mutRate, speciesSize);
//        return solve(alg);
//    }

    private static List<EventList> solve(Algorithm alg) {
        final long start = System.nanoTime();
        List<EventList> finalPopulation = alg.findSolution();
        final long end = System.nanoTime();
        System.out.println("Took: " + ((end - start) / 1000000) + "ms");
        return finalPopulation;
    }

    /*
    public static EventList cuckooSearch(BenchmarkInstance benchmark, int populationSize, int stopCriterion, double pa, double pc) {
        final long start = System.nanoTime();
        CuckooSearch cs = new CuckooSearch(benchmark, populationSize, stopCriterion, pa, pc);
        //scga.printBestSpecies();

        final long end = System.nanoTime();
        System.out.println("Took: " + ((end - start) / 1000000) + "ms");
        return cs.getBestSolution();
    }

    public static List<EventList> GA(BenchmarkInstance benchmark, int populationSize, int stopCriterion, double mutationRate) {
        final long start = System.nanoTime();
        Algorithm ga = new GeneticAlgorithm(benchmark, populationSize, stopCriterion, mutationRate);
        finalPopulation = ga.findSolution();
        final long end = System.nanoTime();
        System.out.println("Took: " + ((end - start) / 1000000) + "ms");
        return finalPopulation;
    }

    public static List<EventList> GA(BenchmarkInstance benchmark, int populationSize, int stopCriterion, double mutationRate) {
        final long start = System.nanoTime();
        Algorithm ga = new ParallelGeneticAlgorithm(benchmark, populationSize, stopCriterion, mutationRate);
        finalPopulation = ga.findSolution();
        final long end = System.nanoTime();
        System.out.println("Took: " + ((end - start) / 1000000) + "ms");
        return finalPopulation;
    }

    public static List<EventList> SCGA(BenchmarkInstance benchmark, int populationSize, int stopCriterion, double mutationRate, int speciesDistance) {
        final long start = System.nanoTime();
        Algorithm scga = new SpeciesConservingGeneticAlgorithm(benchmark, populationSize, stopCriterion, mutationRate, speciesDistance);
        finalPopulation = scga.findSolution();

        final long end = System.nanoTime();
        System.out.println("Took: " + ((end - start) / 1000000) + "ms");
//        return ga.getFinalPopulation();
        return finalPopulation;
    }

    public static List<EventList> SCGA(BenchmarkInstance benchmark, int populationSize, int stopCriterion, double mutationRate, int speciesDistance) {
        final long start = System.nanoTime();
        Algorithm scga = new ParallelSpeciesConservingGeneticAlgorithm(benchmark, populationSize, stopCriterion, mutationRate, speciesDistance);
        finalPopulation = scga.findSolution();

        final long end = System.nanoTime();
        System.out.println("Took: " + ((end - start) / 1000000) + "ms");
//        return ga.getFinalPopulation();
        return finalPopulation;
    }
    */
}
