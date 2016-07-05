package app.utility;

import app.project.Activity;
import app.project.ActivityList;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Kirill on 18/04/2016.
 */
public class SpeciesConservation {
    private SpeciesConservation(){}

    public static <T extends ActivityList> int getAverageSpeciesDistance(List<T> population) {
        int distance = 0;
        int count = 0;

        for (T i1 : population)
            for (T i2 : population)
                if (i1.getId() != i2.getId()) {
                    count++;
                    distance += calculateDistance(i1, i2);
                }

        return distance/count;
    }

    public static <T extends ActivityList> int calculateDistance(T i1, T i2) {
        Map<Activity, Integer> st1 = i1.getStartingTimes();
        Map<Activity, Integer> st2 = i2.getStartingTimes();

        if (st1.equals(st2))
            return 0;

        return st1.entrySet().stream().mapToInt(e -> Math.abs(e.getValue() - st2.get(e.getKey()))).sum();
    }

    public static <T extends ActivityList> List<T> getGlobalOptima(List<T> population, int speciesDistance) {
        final int bestMakespan = population.stream().mapToInt(T::getMakespan).min().getAsInt();
        List<T> best = population.stream().filter(i -> i.getMakespan() == bestMakespan).collect(Collectors.toList());
        return findSpeciesSeeds(best, speciesDistance);
    }

    public static <T extends ActivityList> List<T> findSpeciesSeeds(List<T> individuals, int speciesDistance) {
        Collections.sort(individuals);
        List<T> speciesSeeds = new ArrayList<>();
        speciesSeeds.add(individuals.get(0));

        individuals.stream().filter(withinDistance(speciesSeeds, speciesDistance).negate()).forEach(speciesSeeds::add);
        return speciesSeeds;
    }

    public static <T extends ActivityList> List<T> conserveSpecies(List<T> speciesSeeds, List<T> individuals, int speciesDistance) {
        return speciesSeeds.stream().map(ss -> findBetterIndividualWithinDistance(ss, individuals, speciesDistance)).distinct().collect(Collectors.toList());
    }

    private static <T extends ActivityList> T findBetterIndividualWithinDistance(T speciesSeed, List<T> individuals, int speciesDistance) {
        return individuals.stream()
                .filter(withinDistance(speciesSeed, speciesDistance).and(positiveDistance(speciesSeed)))
                .min(ActivityList::compareTo).orElse(speciesSeed);
    }

    public static <T extends ActivityList> Predicate<T> withinDistance(List<T> inds, int distance){
        return i -> inds.stream().anyMatch(withinDistance(i, distance));
    }

    public static <T extends ActivityList> Predicate<T> withinDistance(T ind, int distance){
        return i -> calculateDistance(i, ind) <= distance;
    }

    public static <T extends ActivityList> Predicate<T> positiveDistance(T ind) {
        return i -> calculateDistance(i, ind) > 0;
    }
}
