package app.algorithm.implementations;

import app.algorithm.Algorithm;
import app.algorithm.CommonOperations;
import app.asset.BenchmarkInstance;
import app.asset.EventList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Kirill on 24/02/2016.
 */
public class GeneticAlgorithm {//implements Algorithm {
//    protected final BenchmarkInstance benchmark;
//    protected final int populationSize;
//    protected final int stopCriterion;
//    protected final double mutationRate;
//    protected final List<EventList> population;

//    public GeneticAlgorithm(BenchmarkInstance benchmark, int populationSize, int stopCriterion, double mutationRate) {
//        this.populationSize = populationSize;
//        this.benchmark = benchmark;
//        this.stopCriterion = stopCriterion;
//        this.mutationRate = mutationRate;
//        population = CommonOperations.initialisePopulation(benchmark, populationSize);
//    }

//    public List<EventList> findSolution() {
//        for (int i = 0; i < stopCriterion; i++) {
//            Collections.shuffle(population);
//            for (int j = 0; j < populationSize-1; j+=2)
//                crossover(population.get(j), population.get(j + 1));
//
//            Collections.sort(population, Comparator.reverseOrder());
//            while (populationSize < population.size())
//                population.remove(0);
//        }
//
//        return population;
//    }
//
//    protected void crossover(EventList mom, EventList dad) {
//        EventList son = CommonOperations.eventCrossover(mom, dad);
//        EventList daughter = CommonOperations.eventCrossover(dad, mom);
//
//        population.add(mutation(son));
//        population.add(mutation(daughter));
//    }
//
//    protected EventList mutation(EventList individual){
//        if(Math.random() < mutationRate)
//            return CommonOperations.eventMove(individual);
//        else
//            return individual;
//    }
}
