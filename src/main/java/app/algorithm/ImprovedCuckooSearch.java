package app.algorithm;

import app.factory.ProjectFactory;
import app.project.EventList;
import app.util.EventListUtils;
import app.util.LevyFlights;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static app.factory.ProjectFactory.asPopulationOfEventLists;
import static app.factory.ProjectFactory.asRandomEventList;
import static app.util.EventListUtils.eventMove;
import static app.util.LevyFlights.calculateSteps;
import static app.util.SpeciesConservationUtils.calculateDistance;
import static java.util.Comparator.comparing;

/**
 * Created by Kirill on 23/02/2016.
 */
class ImprovedCuckooSearch implements Algorithm {
    private static final Logger LOG = LoggerFactory.getLogger(ImprovedCuckooSearch.class);

    protected List<EventList> population;
    protected final EventList initialEL;
    protected final int populationSize;
    protected final int stopCriterion;
    protected final int pc;
    protected final int pa;
    protected final int maxSteps;
    protected int schedulesCount = 0;

    public ImprovedCuckooSearch(EventList initialEL, int populationSize, int stopCriterion, double pa, double pc, int maxSteps) {
        this.initialEL = initialEL;
        this.populationSize = populationSize;
        this.stopCriterion = stopCriterion;
        this.pa = (int)Math.round(pa * populationSize);
        this.pc = (int)Math.round(pc * populationSize);
        this.maxSteps = maxSteps;
        this.population = asPopulationOfEventLists(initialEL, populationSize);
    }

    public List<EventList> findSolution() {
        LOG.info("Population size {}, stopping criterion {}, abandonment rate {}, smart cuckoos {}, max steps {}", populationSize, stopCriterion, pa, pc, maxSteps);
        while (schedulesCount < stopCriterion){
            population.stream().min(comparing(EventList::makespan)).ifPresent(exploreWithLevyFlights());
            exploreWithSmartCuckoos();
            abandonWorstNests();
            buildNewNests();
        }

        return population;
    }

    protected Consumer<EventList> exploreWithLevyFlights(){
        return cuckoo -> {
            double levyNumber = LevyFlights.levyNumber();
            if (levyNumber >= 1) {
                EventList cuckoo2 = population.stream().reduce(findFurthestCuckoo(cuckoo)).get();
                population.add(EventListUtils.eventCrossover(cuckoo, cuckoo2));
                population.add(EventListUtils.eventCrossover(cuckoo2, cuckoo));
            } else {
                population.add(eventMove(cuckoo, calculateSteps(levyNumber, maxSteps)));
            }
            schedulesCount++;
        };
    }

    private BinaryOperator<EventList> findFurthestCuckoo(EventList cuckoo){
        return (c1, c2) -> calculateDistance(c1, cuckoo) > calculateDistance(c2, cuckoo) ? c1 : c2;
    }

    protected void exploreWithSmartCuckoos(){
        Collections.shuffle(population);
        IntStream.range(0, pc).forEach(i -> {
            population.add(eventMove(population.get(i)));
            schedulesCount++;
        });
    }

    protected void abandonWorstNests(){
        Collections.sort(population, comparing(EventList::makespan).reversed());
        while (population.size() > pa) {
            population.remove(0);
        }
    }

    protected void buildNewNests(){
        while (population.size() < populationSize){
            population.add(asRandomEventList(initialEL));
            schedulesCount++;
        }
    }
}
