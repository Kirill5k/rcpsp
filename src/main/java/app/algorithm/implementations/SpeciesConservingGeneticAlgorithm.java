package app.algorithm.implementations;

import app.algorithm.implementations.GeneticAlgorithm;
import app.algorithm.SpeciesConservation;
import app.asset.Activity;
import app.asset.BenchmarkInstance;
import app.asset.EventList;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kirill on 15/04/2016.
 */
public class SpeciesConservingGeneticAlgorithm extends GeneticAlgorithm {

    protected final int speciesDistance;

    public SpeciesConservingGeneticAlgorithm(BenchmarkInstance bi, int populationSize, int stopCriterion, double mutationRate) {
        super(bi, populationSize, stopCriterion, mutationRate);
        this.speciesDistance = SpeciesConservation.getAverageSpeciesDistance(population);
    }

    public List<EventList> findSolution() {
        for (int i = 0; i < stopCriterion; i++) {
            // DETERMINE SPECIES SEEDS
            List<EventList> speciesSeeds = SpeciesConservation.findSpeciesSeeds(population, speciesDistance);

            // DO CROSSOVER AND MUTATION
            Collections.shuffle(population);
            for (int j = 0; j < populationSize-1; j+=2)
                crossover(population.get(j), population.get(j + 1));

            // FIND NEW SPECIES SEEDS
            List<EventList> newGeneration = SpeciesConservation.conserveSpecies(population, speciesSeeds, speciesDistance);

            if (newGeneration.size() > populationSize) {
                System.out.println("Too many species " + newGeneration.size());
                Collections.sort(newGeneration, Comparator.reverseOrder());
                while (newGeneration.size() > 100)
                    newGeneration.remove(0);
            } else {
                // REMOVE WORST INDIVIDUALS
                Collections.sort(population);
                while (newGeneration.size() < populationSize) {
                    if (!newGeneration.contains(population.get(0)))
                        newGeneration.add(population.get(0));
                    else
                        population.remove(0);
                }
            }

            population.clear();
            population.addAll(newGeneration);
        }

        return population;
    }


}
