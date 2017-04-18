package app.util;

import app.project.Activity;
import app.project.ActivityList;
import app.project.EventList;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static app.factory.ProjectFactory.asRandomEventList;
import static app.util.CommonUtils.hasScheduledPredecessors;
import static app.util.CommonUtils.notContainedIn;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by Kirill on 26/08/2016.
 */
public class EventListUtils {
    private static final double EVENT_CROSSOVER_THRESHOLD = 0.18;

    private EventListUtils(){}

    public static <T extends ActivityList> List<EventList> initialisePopulation(T project, int popSize) {
        return IntStream.range(0, popSize).mapToObj(i -> asRandomEventList(project)).collect(toList());
    }

    public static EventList eventMove(EventList el) {
        return relocateEvent(el);
    }

    public static EventList eventMove(EventList el, int amountOfEventsToRelocate) {
        EventList result = el;
        for (int i = 0; i < amountOfEventsToRelocate; i++) {
            result = relocateEvent(result);
        }

        return result;
    }

    private static EventList relocateEvent(EventList el) {
        List<Activity> newSequence = new ArrayList<>(el.activities());
        el.randomEvent().stream().forEach(a -> {
            newSequence.remove(a);
            int minPos = a.getPredecessors().stream().mapToInt(newSequence::indexOf).max().getAsInt();
            int maxPos = a.getSuccessors().stream().mapToInt(newSequence::indexOf).min().getAsInt();
            int randomPos = ThreadLocalRandom.current().nextInt(minPos, maxPos)+1;
            newSequence.add(randomPos, a);
        });

        return EventList.of(newSequence, el.resources());
    }

    public static EventList eventCrossover(EventList p1, EventList p2) {
        return eventCrossover(p1, p2, EVENT_CROSSOVER_THRESHOLD);
    }

    public static EventList eventCrossover(EventList p1, EventList p2, double threshold) {
        List<List<Activity>> p1Events = getBestEvents(p1, threshold);
        List<Activity> p1Activities = p1Events.stream().flatMap(List::stream).collect(toList());
        List<Activity> p2Activities = p2.activities().stream().filter(notContainedIn(p1Activities)).collect(toList());
        List<Activity> childActivities = new ArrayList<>();

        for (List<Activity> event : p1Events) {
            Set<Activity> predecessors = getAllPredecessors(event);
            Set<Activity> successors = getAllSuccessors(event);

            predecessors.removeAll(childActivities);

            for (Activity a : p2Activities) {
                if (notContainedIn(successors).and(hasScheduledPredecessors(childActivities)).test(a)) {
                    childActivities.add(a);
                    predecessors.remove(a);
                }

                if (predecessors.isEmpty())
                    break;
            }

            childActivities.addAll(event);
            p2Activities.removeAll(childActivities);
        }

        childActivities.addAll(p2Activities);
        return EventList.of(childActivities, p1.resources());
    }

    private static List<List<Activity>> getBestEvents(EventList eventList, double threshold){
        return eventList.events().values().stream().sorted((e1, e2) -> Integer.compare(e2.size(), e1.size())).limit(Math.round(threshold * eventList.eventsAmount()))
                .sorted((e1, e2) -> Integer.compare(eventList.startingTimes().get(e1.get(0)), eventList.startingTimes().get(e2.get(0))))
                .collect(toList());
    }

    private static Set<Activity> getAllPredecessors(List<Activity> event) {
        Set<Activity> predecessors = new HashSet<>();
        event.stream().forEach(getPredecessors(predecessors));
        return predecessors;
    }

    private static Consumer<Activity> getPredecessors(Set<Activity> predecessors) {
        return activity -> {
            if (!activity.getPredecessors().isEmpty()){
                predecessors.addAll(activity.getPredecessors());
                activity.getPredecessors().stream().forEach(getPredecessors(predecessors));
            }
        };
    }

    private static Set<Activity> getAllSuccessors(List<Activity> event) {
        Set<Activity> successors = new HashSet<>();
        event.stream().forEach(getSuccessors(successors));
        return successors;
    }

    private static Consumer<Activity> getSuccessors(Set<Activity> successors) {
        return activity -> {
            if (!activity.getSuccessors().isEmpty()) {
                successors.addAll(activity.getSuccessors());
                activity.getSuccessors().stream().forEach(getSuccessors(successors));
            }
        };
    }
}
