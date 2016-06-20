package app.algorithm.implementations;

import app.algorithm.implementations.GeneticAlgorithm;
import app.asset.BenchmarkInstance;
import app.asset.EventList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kirill on 18/04/2016.
 */
public class ParallelGeneticAlgorithm extends GeneticAlgorithm {
//    protected final ExecutorService executorService = Executors.newFixedThreadPool(25);
//
//    public ParallelGeneticAlgorithm(BenchmarkInstance benchmark, int populationSize, int stopCriterion, double mutationRate) {
//        super(benchmark, populationSize, stopCriterion, mutationRate);
//    }
//
//    @Override
//    public List<EventList> findSolution() {
//        for (int i = 0; i < stopCriterion; i++) {
//            performGAOperationsInParallel();
//
//            Collections.sort(population, Comparator.reverseOrder());
//            while (populationSize < population.size())
//                population.remove(0);
//        }
//        executorService.shutdown();
//        return population;
//    }
//
//    protected void performGAOperationsInParallel(){
//        Collections.shuffle(population);
//
//        CountDownLatch latch = new CountDownLatch(populationSize/2);
//        for (int j = 0; j < populationSize-1; j+=2) {
//            final int index = j;
//            executorService.execute(() -> {
//                try {
//                    crossover(population.get(index), population.get(index + 1));
//                } finally {
//                    latch.countDown();
//                }
//            });
//        }
//        try {
//            latch.await();
//        } catch (InterruptedException E) {
//            // handle
//        }
//    }
}
