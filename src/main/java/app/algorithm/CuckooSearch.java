package app.algorithm;

import app.project.ActivityList;
import app.project.EventList;
import app.util.ActivityListUtils;
import app.util.EventListUtils;
import app.util.LevyFlights;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static app.factory.ProjectFactory.asRandomActivityList;
import static app.factory.ProjectFactory.asRandomEventList;
import static app.util.ActivityListUtils.initialisePopulation;
import static app.util.ActivityListUtils.randShiftMove;
import static app.util.ActivityListUtils.twoPointCrossover;
import static app.util.EventListUtils.eventMove;
import static app.util.LevyFlights.calculateSteps;
import static java.util.Comparator.comparing;

/**
 * Created by Kirill on 15/04/2017.
 */
class CuckooSearch implements Algorithm {
    private static final Logger LOG = LoggerFactory.getLogger(ImprovedCuckooSearch.class);

    private List<ActivityList> population;
    private final ActivityList initialAL;
    private final int populationSize;
    private final int stopCriterion;
    private final int pa;
    private final int maxSteps;
    private int schedulesCount = 0;

    CuckooSearch(ActivityList initialAL, int populationSize, int stopCriterion, double pa, int maxSteps) {
        this.initialAL = initialAL;
        this.populationSize = populationSize;
        this.stopCriterion = stopCriterion;
        this.pa = (int)Math.round(pa * populationSize);
        this.maxSteps = maxSteps;
        this.population = initialisePopulation(initialAL, populationSize);
    }

    @Override
    public List<ActivityList> findSolution() {
        LOG.info("Population size {}, stopping criterion {}, abandonment rate {}, max steps {}", populationSize, stopCriterion, pa, maxSteps);
        while (schedulesCount < stopCriterion){
            population.stream().min(comparing(ActivityList::makespan)).ifPresent(exploreWithLevyFlights());
            abandonWorstNests();
            buildNewNests();
        }

        return population;
    }

    protected Consumer<ActivityList> exploreWithLevyFlights(){
        return cuckoo -> {
            double levyNumber = LevyFlights.levyNumber();
            if (levyNumber >= 1) {
                ActivityList cuckoo2 = population.stream().findAny().get();
                population.add(twoPointCrossover(cuckoo, cuckoo2));
                population.add(twoPointCrossover(cuckoo2, cuckoo));
            } else {
                population.add(randShiftMove(cuckoo, calculateSteps(levyNumber, maxSteps)));
            }
            schedulesCount++;
        };
    }

    protected void abandonWorstNests(){
        Collections.sort(population, comparing(ActivityList::makespan).reversed());
        while (population.size() > pa) {
            population.remove(0);
        }
    }

    protected void buildNewNests(){
        while (population.size() < populationSize){
            population.add(asRandomActivityList(initialAL));
            schedulesCount++;
        }
    }
}
