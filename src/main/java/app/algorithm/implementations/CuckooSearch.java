package app.algorithm.implementations;

import app.algorithm.CommonOperations;
import app.asset.BenchmarkInstance;
import app.asset.EventList;
import app.utility.Benchmarks;
import org.apache.commons.math3.special.Gamma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kirill on 23/02/2016.
 */
class CuckooSearch {

    private final int populationSize;
    private final BenchmarkInstance benchmarkInstance;
    private final int stopCriterion;
    private List<EventList> population = new ArrayList<>();
    private final int pc;
    private final int pa;

    public CuckooSearch(BenchmarkInstance benchmarkInstance, int populationSize, int stopCriterion, double pa, double pc) {
        this.populationSize = populationSize;
        this.benchmarkInstance = benchmarkInstance;
        this.stopCriterion = stopCriterion;
        this.pa = (int)(pa * populationSize);
        this.pc = (int)(pc * populationSize);

        findSolution();
    }

    private void findSolution() {
//        Random rand = new Random();
//
//        for (int i = 0; i < populationSize; i++) {
//            population.add(Benchmarks.asRandomEventList(benchmarkInstance));
//        }
//
//        for (int i = 0; i < stopCriterion; i++) {
//            // LEVY FLIGHT CUCKOO
//            Collections.sort(population);
//            population.add(CommonOperations.eventMove(population.get(0)));
//
//            // SMART CUCKOOS
//            Collections.shuffle(population);
//            for (int j = 0; j < pc; j++) {
//                smartCuckoo(population.get(j));
//            }
//
//            // ABANDOMENT OF WORSE SOLUTIONS
//            Collections.sort(population, Collections.reverseOrder());
//            while (population.size() > populationSize-pa)
//                population.remove(0);
//
//            // EVENT CROSSOVER
//            for (int j = 0; j < pa; j++) {
//                breedCuckoo(population.get(j), population.get(rand.nextInt(population.size()-1)));
//            }
//        }

        // RANDOM EVENT MOVE ON BEST SOLUTION
        // RANDOM EVENT MOVE
        // DROP WORST SOLUTIONS
        // CREATE NEW SOLUTIONS WITH CROSSOVER
    }

//    public EventList getBestSolution() {
//        Collections.sort(population);
//        return population.get(0);
//    }
//
//    private final double BETA = 3/2;
//    private final double SIGMA = calculateSigma();
//
//    private double calculateSigma() {
//        double result = Gamma.gamma(BETA+1)*Math.sin(Math.PI*BETA/2)/(Gamma.gamma((1+BETA)/2)*BETA*Math.pow(2, (BETA-1)/2));
//        return Math.pow(result, 1/BETA);
//    }
//
//    private double calculateLevyFlight() {
//        return 0;
//    }
//
//    private void levyFlightCuckoo(EventList el) {
//
//    }
//
//    private void smartCuckoo(EventList el) {
//        population.add(CommonOperations.eventMove(el));
//    }
//
//    private void breedCuckoo(EventList el1, EventList el2) {
//        population.add(CommonOperations.eventCrossover(el1, el2));
//    }
}
