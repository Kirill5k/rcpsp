package app.algorithm.impl;

import app.algorithm.Algorithm;
import app.algorithm.AlgorithmType;
import app.project.EventList;
import app.project.Project;
import app.project.impl.BenchmarkInstance;
import app.project.impl.SimpleEventList;
import app.exception.IncorrectAlgorithmTypeException;

import java.util.List;

/**
 * Created by Kirill on 23/02/2016.
 */
public class Algorithms {
    private Algorithms(){}

    public static <T extends Project> List<EventList> GA(AlgorithmType type, BenchmarkInstance bi, int popSize, int stopCrit, double mutRate) {
        return GA(type, new SimpleEventList(bi.getSequence(), bi.getResCapacities()), popSize, stopCrit, mutRate);
    }

    public static <T extends Project> List<EventList> GA(AlgorithmType type, EventList el, int popSize, int stopCrit, double mutRate) {
        Algorithm alg;
        switch (type){
            case NORMAL_GA: alg = new GeneticAlgorithm(el, popSize, stopCrit, mutRate); break;
            case NORMAL_SCGA: alg = new SpeciesConservingGeneticAlgorithm(el, popSize, stopCrit, mutRate); break;
            case PARALLEL_GA: alg = new ParallelGeneticAlgorithm(el, popSize, stopCrit, mutRate); break;
            default: throw new IncorrectAlgorithmTypeException(type + " is not supported");
        }

        return solve(alg);
    }

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
    */
}
