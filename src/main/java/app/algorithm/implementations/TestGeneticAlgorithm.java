package app.algorithm.implementations;

import app.algorithm.CommonOperations;
import app.algorithm.NewSpeciesConservation;
import app.asset.BenchmarkInstance;
import app.asset.EventList;
import javafx.collections.transformation.SortedList;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kirill on 20/05/2016.
 */
public class TestGeneticAlgorithm {//extends GeneticAlgorithm {
    
//    private final int speciesSize;
    
//    public TestGeneticAlgorithm(BenchmarkInstance benchmark, int populationSize, int stopCriterion, double mutationRate, int speciesSize) {
////        super(benchmark, populationSize, stopCriterion, mutationRate);
////        this.speciesSize = speciesSize;
//    }

//    public List<EventList> findSolution() {
//        for (int i = 0; i < stopCriterion; i++) {
//            // ESTABLISH SPECIES
//            Set<Set<EventList>> species = NewSpeciesConservation.establishSpecies(population, speciesSize);
//
//            System.out.println("R" + i + ": population size: " + population.size() + " , amount of species: " + species.size());
//
//            // DO GA OPERATIONS
//            species.forEach(this::performGAOperations);
//
//            // CONSERVE SPECIES
//            population.clear();
//            for (Set<EventList> s : species) {
//                conserveSpecies(s);
//            }
//        }
//
//        Set<Set<EventList>> species = NewSpeciesConservation.establishSpecies(population, speciesSize);
//        return NewSpeciesConservation.conserveSpeciesSeeds(species);
//        //return population;
//    }

//    private void performGAOperations(Set<EventList> s) {
//        int originalSize = s.size();
//        List<EventList> l = new ArrayList<>(s);
//        Collections.shuffle(l);
//        for (int i = 0; i < originalSize-1; i+=2) {
//            s.add(mutation(CommonOperations.eventCrossover(l.get(0), l.get(1))));
//            s.add(mutation(CommonOperations.eventCrossover(l.get(1), l.get(0))));
//        }
//    }

//    private void conserveSpecies(Set<EventList> s) {
//        List<EventList> sortedS = new ArrayList<>(s);
//        Collections.sort(sortedS);
//
//        int count = 0;
//        while (count != sortedS.size()/2 + 1) {
//            if (sortedS.isEmpty())
//                break;
//
//            EventList e = sortedS.remove(0);
//
//            if (!population.contains(e)) {
//                population.add(e);
//                count++;
//            }
//        }
//    }
}
