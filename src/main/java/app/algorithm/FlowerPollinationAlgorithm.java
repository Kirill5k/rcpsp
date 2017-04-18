package app.algorithm;

import app.project.ActivityList;
import app.util.LevyFlights;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static app.util.ActivityListUtils.initialisePopulation;
import static app.util.ActivityListUtils.randShiftMove;
import static app.util.ActivityListUtils.twoPointCrossover;
import static app.util.LevyFlights.calculateSteps;
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
    private ActivityList bestInvididual = null;

    FlowerPollinationAlgorithm(ActivityList initialAL, int populationSize, int stopCriterion, double ps, int maxSteps) {
        this.populationSize = populationSize;
        this.stopCriterion = stopCriterion;
        this.ps = ps;
        this.maxSteps = maxSteps;
        this.population = initialisePopulation(initialAL, populationSize);
    }

    @Override
    public List findSolution() {
        LOG.info("Population size {}, stopping criterion {}, switching probability {}, max steps {}", populationSize, stopCriterion, ps, maxSteps);
        findBest();
        while (schedulesCount < stopCriterion){
            population= population.stream().map(pollinationFlower()).collect(toList());
        }
        return population;
    }

    private void findBest(){
        bestInvididual = population.stream().min(comparing(ActivityList::makespan)).get();
    }

    private Function<ActivityList, ActivityList> pollinationFlower(){
        return ind -> {
            ActivityList newInd = Math.random() < ps ? localPollination() : globalPollination();
            checkIfBest(newInd);
            schedulesCount++;
            return newInd.makespan() < ind.makespan() ? newInd : ind;
        };
    }

    private ActivityList localPollination(){
        double levyNumber = LevyFlights.levyNumber();
        return randShiftMove(bestInvididual, calculateSteps(levyNumber, maxSteps));
    }

    private ActivityList globalPollination(){
        ActivityList randInd1 = population.get(current().nextInt(populationSize-1));
        ActivityList randInd2 = population.get(current().nextInt(populationSize-1));
        return twoPointCrossover(randInd1, randInd2);
    }

    private void checkIfBest(ActivityList newInd){
        if (bestInvididual != null && bestInvididual.makespan() > newInd.makespan()){
            bestInvididual = newInd;
        }
    }
}
