package app.algorithm;

import app.project.EventList;
import app.utility.CommonOperations;
import app.utility.SpeciesConservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kirill on 24/02/2016.
 */
class GeneticAlgorithm implements Algorithm {
    private static final Logger LOG = LoggerFactory.getLogger(GeneticAlgorithm.class);

    protected final EventList initialEL;
    protected final int populationSize;
    protected final int stopCriterion;
    protected final double mutationRate;
    protected List<EventList> population;

    public GeneticAlgorithm(EventList initialEL, int populationSize, int stopCriterion, double mutationRate) {
        this.populationSize = populationSize;
        this.initialEL = initialEL;
        this.stopCriterion = stopCriterion;
        this.mutationRate = mutationRate;
        population = CommonOperations.initialisePopulation(initialEL, populationSize);
    }

    public List<EventList> findSolution() {
        LOG.info("Population size {}, stopping criterion {}, mutation rate {}", populationSize, stopCriterion, mutationRate);
        for (int i = 0; i < stopCriterion; i++) {
            establishPairsForSelectionBasedOnDistance();
            evolvePopulation();
            population = population.stream().sorted().distinct().limit(populationSize).collect(Collectors.toList());
        }

        return population;
    }


    protected void evolvePopulation() {
        for (int j = 0; j < populationSize-1; j+=2)
            crossover(population.get(j), population.get(j + 1));
    }

    protected void establishPairsForSelectionBasedOnDistance2() {
        Collections.sort(population);
        List<EventList> pairs = new ArrayList<>();


    }

    protected void establishPairsForSelectionBasedOnDistance() {
        Collections.sort(population);
        List<EventList> pairs = new ArrayList<>();
        while (population.size() > 1) {
            EventList ind1 = population.remove(0);
            EventList ind2 = population.stream().min((i1, i2) -> Integer.compare(SpeciesConservation.calculateDistance(i1, ind1), SpeciesConservation.calculateDistance(i2, ind1))).get();
            pairs.add(ind1);
            pairs.add(ind2);
            population.remove(ind2);
        }
        population.clear();
        population.addAll(pairs);
    }

    protected void crossover(EventList p1, EventList p2) {
        EventList o1 = CommonOperations.eventCrossover(p1, p2);
        EventList o2 = CommonOperations.eventCrossover(p2, p1);

        population.add(mutation(o1));
        population.add(mutation(o2));
    }

    protected EventList mutation(EventList individual){
        if(Math.random() < mutationRate)
            return CommonOperations.eventMove(individual);
        else
            return individual;
    }
}