package app.algorithm.implementations;

import app.algorithm.implementations.GeneticAlgorithm;
import app.algorithm.SpeciesConservation;
import app.asset.Activity;
import app.asset.BenchmarkInstance;
import app.asset.EventList;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by Kirill on 15/04/2016.
 */
public class SpeciesConservingGeneticAlgorithm extends GeneticAlgorithm {

    protected int speciesDistance;

    public SpeciesConservingGeneticAlgorithm(BenchmarkInstance bi, int populationSize, int stopCriterion, double mutationRate) {
        super(bi, populationSize, stopCriterion, mutationRate);
        this.speciesDistance = SpeciesConservation.getAverageSpeciesDistance(population);
    }

    public List<EventList> findSolution() {
        for (int i = 0; i < stopCriterion; i++) {

            // DETERMINE SPECIES SEEDS
            List<EventList> speciesSeeds = SpeciesConservation.findSpeciesSeeds(population, speciesDistance);

            // DO CROSSOVER AND MUTATION
            selectionBasedOnDistance();
            evolvePopulation();

            // FIND NEW SPECIES SEEDS
            List<EventList> newGeneration = SpeciesConservation.conserveSpecies(population, speciesSeeds, speciesDistance);

            if (newGeneration.size() > populationSize)
                newGeneration = newGeneration.stream().sorted().limit(populationSize).collect(Collectors.toList());
            else
                newGeneration.addAll(population.stream().sorted().limit(populationSize - newGeneration.size()).collect(Collectors.toList()));

            population.clear();
            population.addAll(newGeneration);
        }

        return population;
    }


}
