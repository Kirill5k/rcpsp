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

    public static int getBestMakespan(List<EventList> population) {
        return getBestSolution(population).getMakespan();
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

    public static int calculateDistance(EventList el1, EventList el2) {
        Map<Activity, Integer> stEl1 = el1.getStartingTimes();
        Map<Activity, Integer> stEl2 = el2.getStartingTimes();
        return stEl1.entrySet().stream().mapToInt(e -> Math.abs(e.getValue() - stEl2.get(e.getKey()))).sum();
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
        return eventCrossover(p1, p2, 0.4);
    }

    public static EventList eventCrossover(EventList p1, EventList p2, double threshold) {
        int activityAmount = (int)(threshold * p1.getActivities().size());
        int activityCount = 0;
        int eventCount = 0;

        /*
        ---------------------------------------------------------- GET RIGHT EVENTS -------------------------------------
         */

        Map<List<Activity>, Integer> orderedEvents = new HashMap<>();
        Map<Integer, List<List<Activity>>> sortedEvents = new TreeMap<>(Comparator.reverseOrder());
        List<List<Activity>> selectedEvents = new ArrayList<>();

        for (Map.Entry<Integer, List<Activity>> e : p1.getSchedule().entrySet()) {
            List<Activity> ev = e.getValue();
            int eventSize = ev.size();
            orderedEvents.put(ev, eventCount);
            eventCount++;

            if (sortedEvents.get(eventSize)==null)
                sortedEvents.put(eventSize, new ArrayList<>());

            sortedEvents.get(eventSize).add(ev);
        }

        for (Map.Entry<Integer, List<List<Activity>>> e : sortedEvents.entrySet()) {
            if (activityCount > activityAmount)
                break;

            for (List<Activity> event : e.getValue())
                if (activityAmount > activityCount) {
                    selectedEvents.add(event);
                    activityCount = activityCount + e.getKey();
                } else
                    break;
        }

        Collections.sort(selectedEvents, (o1, o2) -> Integer.compare(orderedEvents.get(o1), orderedEvents.get(o2)));


        /*
        ----------------------------------------------------------- ADD MISSING ACTIVITIES FROM PARENT2 ----------------------
         */

        Set<Activity> eventActivities = new HashSet<>();
        for (List<Activity> event : selectedEvents) {
            eventActivities.addAll(event);
//            System.out.println("Selected event: " + event);
        }

        List<Activity> p2Activities = new ArrayList<>(p2.getActivities());
        List<Activity> childActivities = new ArrayList<>();
        for (List<Activity> event : selectedEvents) {
            Set<Activity> predecessors = new HashSet<>();
            Set<Activity> successors = new HashSet<>();
            for (Activity a : event) {
                predecessors.addAll(getAllPredecessors(predecessors, a));
                successors.addAll(getAllSuccessors(successors, a));
            }

            childActivities.forEach(p2Activities::remove);
            childActivities.forEach(predecessors::remove);

            for (Activity a : p2Activities) {
                if (!eventActivities.contains(a) && !successors.contains(a) && !childActivities.contains(a)) {
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

        for (Activity a : p2Activities)
            if (!childActivities.contains(a))
                childActivities.add(a);

        //System.out.println(childActivities);

        return new EventList(p1.getResources(), childActivities);
    }


    /*
    --------------------------------------------------------------------- LOCAL FUNCTIONS
     */
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
