package app.algorithm;

import app.asset.Activity;
import app.asset.BenchmarkInstance;
import app.asset.EventList;
import app.utility.Benchmarks;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Kirill on 18/02/2016.
 */
public class CommonOperations {
    private CommonOperations(){}

    public static double getDeviationFromOptima(int result, int optima) {
        return (double) (result - optima) / (double) optima * 100;
    }

    public static EventList getBestSolution(List<EventList> population) {
        Collections.sort(population);
        return population.get(0);
    }

    public static List<EventList> initialisePopulation(BenchmarkInstance bi, int populationSize) {
        List<EventList> population = new ArrayList<>();
        while (population.size() < populationSize){
            EventList ind = Benchmarks.asRandomEventList(bi);
            population.add(ind);
        }

        return population;
    }

    public static EventList eventMove(EventList el) {
        return IntStream.range(0, 5).boxed().parallel()
                .map(i -> relocateEvent(new ArrayList<>(el.getActivities()), new ArrayList<>(el.getRandomEvent()), el.getResources()))
                .min((e1, e2) -> e1.compareTo(e2)).get();
    }

    private static EventList relocateEvent(List<Activity> activities, List<Activity> event, Map<Integer, Integer> resources) {
        event.forEach(a -> {
            activities.remove(a);
            final int minPos = a.getPredecessors().stream().map(p -> activities.indexOf(p)).max((o1, o2) -> Integer.compare(o1, o2)).get();
            final int maxPos = a.getSuccessors().stream().map(s -> activities.indexOf(s)).min((o1, o2) -> Integer.compare(o1, o2)).get();
            final int randomPos = new Random().nextInt(maxPos - minPos) + minPos+1;
            activities.add(randomPos, a);
        });
        return new EventList(resources, activities);
    }

    public static EventList eventCrossover(EventList p1, EventList p2) {
        return eventCrossover(p1, p2, 0.15);
    }

    public static EventList eventCrossover(EventList p1, EventList p2, double threshold) {
        List<List<Activity>> selectedEvents = p1.getSchedule().values().stream()
                .sorted((e1, e2) -> Integer.compare(e2.size(), e1.size()))
                .limit(Math.round(threshold * p1.getEventsAmount()))
                .sorted((e1, e2) -> Integer.compare(p1.getStartingTimes().get(e1.get(0)), p1.getStartingTimes().get(e2.get(0))))
                .collect(Collectors.toList());

        List<Activity> childActivities = selectedEvents.stream().flatMap(e -> e.stream()).collect(Collectors.toList());
        List<Activity> p2Activities = p2.getActivities().stream().filter(a -> !childActivities.contains(a)).collect(Collectors.toList());

        p2Activities.stream().forEach(a ->{
            int pos = a.getPredecessors().stream().map(p -> childActivities.indexOf(p)).max((o1, o2) -> Integer.compare(o1, o2)).get();
            childActivities.add(pos+1, a);
        });

        return new EventList(p1.getResources(), childActivities);
    }
}
