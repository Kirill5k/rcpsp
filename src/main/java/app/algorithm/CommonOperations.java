package app.algorithm;

import app.asset.*;
import app.utility.Projects;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
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

    public static <T extends ActivityList> List<EventList> initialisePopulation(T projectInstance, int populationSize) {
        List<Map<Activity, Integer>> uniqueSchedules = new ArrayList<>();
        List<EventList> population = new ArrayList<>();
        while (population.size() < populationSize){
            EventList ind = Projects.asRandomEventList(projectInstance);
            if (!uniqueSchedules.contains(ind)) {
                population.add(ind);
                uniqueSchedules.add(ind.getStartingTimes());
            }
        }

        return population;
    }

    public static EventList eventMove(EventList el) {
        return IntStream.range(0, 5).boxed().parallel()
                .map(i -> relocateEvent(new ArrayList<>(el.getSequence()), new ArrayList<>(el.getRandomEvent()), el.getResCapacities()))
                .min((e1, e2) -> e1.compareTo(e2)).get();
    }

    private static EventList relocateEvent(List<Activity> activities, List<Activity> event, Map<Integer, Integer> resources) {
        event.forEach(a -> {
            activities.remove(a);
            final int minPos = a.getPredecessors().stream().map(p -> activities.indexOf(p)).max((o1, o2) -> Integer.compare(o1, o2)).get();
            final int maxPos = a.getSuccessors().stream().map(s -> activities.indexOf(s)).min((o1, o2) -> Integer.compare(o1, o2)).get();
            //final int randomPos = new Random().nextInt(maxPos - minPos) + minPos+1;
            final int randomPos = ThreadLocalRandom.current().nextInt(minPos, maxPos)+1;
            activities.add(randomPos, a);
        });
        return new SimpleEventList(activities, resources);
    }

    public static EventList eventCrossover(EventList p1, EventList p2) {
        return eventCrossover(p1, p2, 0.18);
    }

    public static EventList eventCrossover(EventList p1, EventList p2, double threshold) {
        List<List<Activity>> p1Events = p1.getEvents().values().stream()
                .sorted((e1, e2) -> Integer.compare(e2.size(), e1.size()))
                .limit(Math.round(threshold * p1.getEventsAmount()))
                .sorted((e1, e2) -> Integer.compare(p1.getStartingTimes().get(e1.get(0)), p1.getStartingTimes().get(e2.get(0))))
                .collect(Collectors.toList());

        List<Activity> p1Activities = p1Events.stream().flatMap(e -> e.stream()).collect(Collectors.toList());
        List<Activity> p2Activities = p2.getSequence().stream().filter(a -> !p1Activities.contains(a)).collect(Collectors.toList());
        List<Activity> childActivities = new ArrayList<>();
        for (List<Activity> event : p1Events) {
            Set<Activity> predecessors = new HashSet<>();
            Set<Activity> successors = new HashSet<>();
            for (Activity a : event) {
                predecessors.addAll(getAllPredecessors(predecessors, a));
                successors.addAll(getAllSuccessors(successors, a));
            }

            childActivities.forEach(p2Activities::remove);
            childActivities.forEach(predecessors::remove);

            for (Activity a : p2Activities) {
                if (!p1Activities.contains(a) && !successors.contains(a) && !childActivities.contains(a)) {
                    if (checkPredecessors(childActivities, a)) {
                        childActivities.add(a);
                        predecessors.remove(a);
                    }
                }

                if (predecessors.isEmpty())
                    break;
            }

            childActivities.addAll(event);
        }

        p2Activities.stream().filter(a -> !childActivities.contains(a)).forEach(childActivities::add);
        return new SimpleEventList(childActivities, p1.getResCapacities());
    }

    private static boolean checkPredecessors(List<Activity> as, Activity a) {
        boolean check = true;

        for (Activity predecessor : a.getPredecessors())
            if (!as.contains(predecessor))
                check = false;

        return check;
    }

    private static Set<Activity> getAllPredecessors(Set<Activity> predecessors, Activity a) {
        for (Activity predecessor : a.getPredecessors())
            if (predecessor.getPredecessors().size() > 0) {
                predecessors.add(predecessor);
                getAllPredecessors(predecessors, predecessor);
            } else
                predecessors.add(predecessor);

        return predecessors;
    }

    private static Set<Activity> getAllSuccessors(Set<Activity> successors, Activity a) {
        for (Activity successor : a.getSuccessors())
            if (successor.getSuccessors().size() > 0) {
                successors.add(successor);
                getAllSuccessors(successors, successor);
            } else
                successors.add(successor);

        return successors;
    }
}
