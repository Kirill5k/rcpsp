package app.algorithm;

import app.project.EventList;
import app.util.EventListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static app.util.EventListUtils.eventCrossover;
import static app.util.EventListUtils.eventMove;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by kirillb on 11/04/2017.
 */
class GeneticAlgorithm implements Algorithm {
    private static final Logger LOG = LoggerFactory.getLogger(GeneticAlgorithm.class);

    protected List<EventList> population;
    protected final EventList initialEL;
    protected final int populationSize;
    protected final int stopCriterion;
    private final double mutationRate;
    protected int schedulesCount = 0;

    GeneticAlgorithm(EventList initialEL, int populationSize, int stopCriterion, double mutationRate) {
        this.initialEL = initialEL;
        this.populationSize = populationSize;
        this.stopCriterion = stopCriterion;
        this.mutationRate = mutationRate;
        this.population = EventListUtils.initialisePopulation(initialEL, populationSize);
    }

    @Override
    public List<EventList> findSolution() {
        LOG.info("Population size {}, stopping criterion {}, mutation rate {}", populationSize, stopCriterion, mutationRate);
        while (schedulesCount < stopCriterion){
            arrangeInPairs();
            evolvePopulation();
            survivalOfTheFittest();
        }

        return population;
    }

    private void arrangeInPairs(){
        Collections.sort(population, comparing(EventList::makespan));
    }

    private void evolvePopulation(){
        IntStream.iterate(0, i -> i+2).limit(populationSize).forEach(i -> generateOffspring(population.get(i), population.get(i+1)));
    }

    private void generateOffspring(EventList ind1, EventList ind2) {
        population.add(mutate(eventCrossover(ind1, ind2)));
        population.add(mutate(eventCrossover(ind2, ind1)));
        schedulesCount +=2;
    }

    private EventList mutate(EventList ind){
        return Math.random() < mutationRate ? eventMove(ind, 3) : ind;
    }

    private void survivalOfTheFittest(){
        population = population.stream().sorted(comparing(EventList::makespan)).limit(populationSize).collect(toList());
    }
}
