package app.algorithm;

import app.factory.ProjectFactory;
import app.project.ActivityList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;

import static app.factory.ProjectFactory.asPopulationOfActivityLists;
import static app.util.ActivityListUtils.randShiftMove;
import static app.util.ActivityListUtils.twoPointCrossover;
import static app.util.LevyFlights.calculateSteps;
import static app.util.LevyFlights.levyNumber;
import static java.util.Comparator.comparing;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toList;

/**
 * Created by kirillb on 18/04/2017.
 */
class FlowerPollinationAlgorithm implements Algorithm {
    private static final Logger LOG = LoggerFactory.getLogger(FlowerPollinationAlgorithm.class);

    private List<ActivityList> population;
    private final int populationSize;
    private final int stopCriterion;
    private final double ps;
    private final int maxSteps;
    private int schedulesCount = 0;
    private ActivityList bestIndividual = null;

    FlowerPollinationAlgorithm(ActivityList initialAL, int populationSize, int stopCriterion, double ps, int maxSteps) {
        this.populationSize = populationSize;
        this.stopCriterion = stopCriterion;
        this.ps = ps;
        this.maxSteps = maxSteps;
        this.population = asPopulationOfActivityLists(initialAL, populationSize);
    }

    @Override
    public List findSolution() {
        LOG.info("Population size {}, stopping criterion {}, switching probability {}, max steps {}", populationSize, stopCriterion, ps, maxSteps);
        findBest();
        while (schedulesCount < stopCriterion){
            population= population.stream().map(pollinateFlower()).collect(toList());
        }
        return population;
    }

    private void findBest(){
        bestIndividual = population.stream().min(comparing(ActivityList::makespan)).get();
    }

    private Function<ActivityList, ActivityList> pollinateFlower(){
        return ind -> {
            ActivityList newInd = Math.random() < ps ? localPollination() : globalPollination();
            checkIfBest(newInd);
            schedulesCount++;
            return newInd.makespan() < ind.makespan() ? newInd : ind;
        };
    }

    private ActivityList localPollination(){
        return randShiftMove(bestIndividual, calculateSteps(levyNumber(), maxSteps));
    }

    private ActivityList globalPollination(){
        ActivityList randInd1 = population.get(current().nextInt(populationSize-1));
        ActivityList randInd2 = population.get(current().nextInt(populationSize-1));
        return twoPointCrossover(randInd1, randInd2);
    }

    private void checkIfBest(ActivityList newInd){
        if (bestIndividual == null || bestIndividual.makespan() > newInd.makespan()){
            bestIndividual = newInd;
        }
    }
}
