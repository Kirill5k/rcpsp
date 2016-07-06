package app.algorithm;

import app.project.EventList;
import app.utility.CommonOperations;
import app.utility.SpeciesConservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kirill on 15/04/2016.
 */
class SpeciesConservingGeneticAlgorithm extends GeneticAlgorithm {
    private static final Logger LOG = LoggerFactory.getLogger(SpeciesConservingGeneticAlgorithm.class);

    protected int speciesDistance;

    public SpeciesConservingGeneticAlgorithm(EventList initialEL, int populationSize, int stopCriterion, double mutationRate) {
        super(initialEL, populationSize, stopCriterion, mutationRate);
        this.speciesDistance = SpeciesConservation.getAverageSpeciesDistance(population)/2;
    }

    public List<EventList> findSolution() {
        LOG.info("Population size {}, stopping criterion {}, mutation rate {}, species distance {}", populationSize, stopCriterion, mutationRate, speciesDistance);
        if (speciesDistance == 0) return population;

        long start = System.currentTimeMillis();

        for (int i = 0; i < stopCriterion; i++) {

            // DETERMINE SPECIES SEEDS
            List<EventList> speciesSeeds = SpeciesConservation.findSpeciesSeeds(population, speciesDistance);

            // DO CROSSOVER AND MUTATION
            establishPairsForSelectionBasedOnDistance();
            evolvePopulation();

            // FIND NEW SPECIES SEEDS
            List<EventList> newSpeciesSeeds = SpeciesConservation.conserveSpecies(speciesSeeds, population, speciesDistance);

            if (newSpeciesSeeds.size() > populationSize) {
                newSpeciesSeeds = newSpeciesSeeds.stream().distinct().sorted().limit(populationSize).collect(Collectors.toList());
            } else {
                List<EventList> remainingIndividuals = population.stream().filter(CommonOperations.notContainedIn(newSpeciesSeeds))
                        .distinct().sorted().limit(populationSize-newSpeciesSeeds.size()).collect(Collectors.toList());
                newSpeciesSeeds.addAll(remainingIndividuals);
            }

            population.clear();
            population.addAll(newSpeciesSeeds);
        }

        return population;
    }




}
