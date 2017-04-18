package app.algorithm;

import app.project.EventList;
import app.util.SpeciesConservationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static app.util.CommonUtils.notContainedIn;
import static app.util.SpeciesConservationUtils.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by Kirill on 15/04/2016.
 */
class SpeciesConservingCuckooSearch extends ImprovedCuckooSearch {
    private static final Logger LOG = LoggerFactory.getLogger(SpeciesConservingCuckooSearch.class);

    private final int speciesDistance;
    private List<EventList> speciesSeeds;

    public SpeciesConservingCuckooSearch(EventList initialEL, int populationSize, int stopCriterion, double pa, double pc, int maxSteps) {
        super(initialEL, populationSize, stopCriterion, pa, pc, maxSteps);
        this.speciesDistance = (int)(SpeciesConservationUtils.calculateAverageDistance(population) / Algorithms.SPECIES_DISTANCE_DIVIDER);
        this.speciesSeeds = new ArrayList<>();
    }

    public List<EventList> findSolution() {
        LOG.info("Population size {}, stopping criterion {}, abandonment rate {}, smart cuckoos {}, max steps {}, species distance {}",
                populationSize, stopCriterion, pa, pc, maxSteps, speciesDistance);
        if (speciesDistance == 0) return population;

        while (schedulesCount < stopCriterion) {
            findSpeciesSeeds();
            speciesSeeds.forEach(exploreWithLevyFlights());
            exploreWithSmartCuckoos();
            abandonWorstNests();
            buildNewNests();
            conserveSpecies();
        }

        return getGlobalOptima(population, speciesDistance);
    }

    private void findSpeciesSeeds() {
        speciesSeeds = SpeciesConservationUtils.findSpeciesSeeds(population, speciesDistance);
    }

    private void conserveSpecies(){
        speciesSeeds = SpeciesConservationUtils.conserveSpecies(speciesSeeds, population, speciesDistance);

        if (speciesSeeds.size() > populationSize) {
            population = speciesSeeds.stream().distinct().sorted(comparing(EventList::makespan))
                    .limit(populationSize).collect(toList());
        } else {
            population = population.stream().distinct().sorted(comparing(EventList::makespan))
                    .filter(notContainedIn(speciesSeeds))
                    .limit(populationSize-speciesSeeds.size()).collect(toList());
            population.addAll(speciesSeeds);
        }
    }


}
