package app.util;

import app.project.Activity;
import app.project.ActivityList;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static app.util.CommonUtils.hasMakespanOf;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

/**
 * Created by Kirill on 18/04/2016.
 */
public class SpeciesConservationUtils {
    private SpeciesConservationUtils(){}

    public static <T extends ActivityList> double calculateAverageDistance(List<T> population) {
        return population.stream().mapToDouble(individual -> calculateAverageDistance(individual, population)).average().orElse(0);
    }

    private static <T extends ActivityList> double calculateAverageDistance(T i1, List<T> population){
        return population.stream().mapToInt(i2 -> calculateDistance(i1, i2)).average().orElse(0);
    }

    public static <T extends ActivityList> int calculateDistance(T i1, T i2) {
        Map<Activity, Integer> st1 = i1.startingTimes();
        Map<Activity, Integer> st2 = i2.startingTimes();
        return st1.equals(st2) ? 0 : st1.entrySet().stream().mapToInt(e -> Math.abs(e.getValue() - st2.get(e.getKey()))).sum();
    }

    public static <T extends ActivityList> List<T> getGlobalOptima(List<T> population, int speciesDistance) {
        int bestMakespan = population.stream().mapToInt(T::makespan).min().getAsInt();
        List<T> best = population.stream().filter(hasMakespanOf(bestMakespan)).collect(toList());
        return findSpeciesSeeds(best, speciesDistance);
    }

    public static <T extends ActivityList> List<T> findSpeciesSeeds(List<T> population, int speciesDistance) {
        Collections.sort(population, Comparator.comparing(ActivityList::makespan).reversed());
        List<T> speciesSeeds = new ArrayList<>();
        speciesSeeds.add(population.get(0));
        population.stream().filter(withinDistance(speciesSeeds, speciesDistance).negate()).forEach(speciesSeeds::add);
        return speciesSeeds;
    }

    public static <T extends ActivityList> List<T> conserveSpecies(List<T> speciesSeeds, List<T> population, int speciesDistance) {
        return speciesSeeds.stream().map(findBetterIndividualWithinDistance(population, speciesDistance)).distinct().collect(toList());
    }

    private static <T extends ActivityList> UnaryOperator<T> findBetterIndividualWithinDistance(List<T> population, int speciesDistance) {
        return speciesSeed -> population.stream().filter(withinDistance(speciesSeed, speciesDistance).and(positiveDistance(speciesSeed)))
                .min(Comparator.comparing(ActivityList::makespan)).orElse(speciesSeed);
    }

    private static <T extends ActivityList> Predicate<T> withinDistance(List<T> inds, int distance){
        return i -> inds.stream().anyMatch(withinDistance(i, distance));
    }

    private static <T extends ActivityList> Predicate<T> withinDistance(T ind, int distance){
        return i -> calculateDistance(i, ind) <= distance;
    }

    private static <T extends ActivityList> Predicate<T> positiveDistance(T ind) {
        return i -> calculateDistance(i, ind) > 0;
    }
}
