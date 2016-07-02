package app.algorithm.implementations;

import app.algorithm.SpeciesConservation;
import app.asset.BenchmarkInstance;
import app.asset.EventList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kirill on 15/04/2016.
 */
class SpeciesConservingGeneticAlgorithm extends GeneticAlgorithm {

    protected int speciesDistance;

    public SpeciesConservingGeneticAlgorithm(EventList initialEL, int populationSize, int stopCriterion, double mutationRate) {
        super(initialEL, populationSize, stopCriterion, mutationRate);
        this.speciesDistance = SpeciesConservation.getAverageSpeciesDistance(population);
    }

    public List<EventList> findSolution() {
        for (int i = 0; i < stopCriterion; i++) {

            // DETERMINE SPECIES SEEDS
            List<EventList> speciesSeeds = SpeciesConservation.findSpeciesSeeds(population, speciesDistance);

            // DO CROSSOVER AND MUTATION
            establishPairsForSelectionBasedOnDistance();
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
