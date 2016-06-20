package app.algorithm;

import app.asset.Activity;
import app.asset.BenchmarkInstance;
import app.asset.EventList;
import app.utility.Benchmarks;

import java.util.*;

/**
 * Created by Kirill on 18/02/2016.
 */
public class CommonOperations {
    private CommonOperations(){}

    public static int getBestMakespan(List<EventList> population) {
        return getBestSolution(population).getMakespan();
    }

    public static EventList getBestSolution(List<EventList> population) {
        Collections.sort(population);
        return population.get(0);
    }

    public static List<EventList> initialisePopulation(BenchmarkInstance bi, int populationSize) {
        Map<Map<Integer, Set<Activity>>, Boolean> uniqueSchedules = new HashMap<>();

        List<EventList> population = new ArrayList<>();
        while (population.size() < populationSize){
            EventList ind = Benchmarks.asRandomEventList(bi);
            if (uniqueSchedules.get(ind.getSchedule())== null || !uniqueSchedules.get(ind.getSchedule())) {
                population.add(ind);
                uniqueSchedules.put(ind.getSchedule(), true);
            }
        }

        return population;
    }

//    public static int calculateDistance(EventList el1, EventList el2) {
//        Map<Integer, Integer> startingTimeEl1 = el1.getStartingTimes();
//        Map<Integer, Integer> startingTimeEl2 = el2.getStartingTimes();
//
//        int sum = 0;
//
//        for (Map.Entry<Integer, Integer> e : startingTimeEl1.entrySet())
//            sum += Math.abs(e.getValue() - startingTimeEl2.get(e.getKey()));
//
//        return sum;
//    }

    public static EventList eventMove(EventList el) {
        EventList result = el;
        Set<Activity> event = el.getRandomEvent();

        for (int i = 0; i < 3; i++) {
            List<Activity> activities = new ArrayList<>(el.getActivities());
            for (Activity a : event) {
                activities.remove(a);

                int minPos = 0;
                for (Activity predecessor : a.getPredecessors()) {
                    int newPos = activities.indexOf(predecessor);
                    if (newPos > minPos)
                        minPos = newPos;
                }
                a.getPredecessors().stream().map(p -> activities.indexOf(p)).min((o1, o2) -> Integer.compare(o1, o2)).get();

                int maxPos = activities.size();
                for (Activity successor : a.getSuccessors()) {
                    int newPos = activities.indexOf(successor);
                    if (newPos < maxPos)
                        maxPos = newPos;
                }

                minPos++;
                int randomNum = new Random().nextInt((maxPos - minPos) + 1) + minPos;
                activities.add(randomNum, a);
                EventList temp = new EventList(el.getResources(), activities);
                if (temp.getMakespan() < result.getMakespan())
                    result = temp;
            }
        }

        return result;
    }

//    public static EventList eventCrossover(EventList p1, EventList p2) {
//        return eventCrossover(p1, p2, 0.4);
//    }

//    public static EventList eventCrossover(EventList p1, EventList p2, double threshold) {
//        int activityAmount = (int)(threshold * p1.getActivities().size());
//        int activityCount = 0;
//        int eventCount = 0;
//
//        /*
//        ---------------------------------------------------------- GET RIGHT EVENTS -------------------------------------
//         */
//
//        Map<Set<Activity>, Integer> orderedEvents = new HashMap<>();
//        Map<Integer, Set<Set<Activity>>> sortedEvents = new TreeMap<>(Comparator.reverseOrder());
//        List<Set<Activity>> selectedEvents = new ArrayList<>();
//
//        for (Map.Entry<Integer, Set<Activity>> e : p1.getSchedule().entrySet()) {
//            Set<Activity> ev = e.getValue();
//            int eventSize = ev.size();
//            orderedEvents.put(ev, eventCount);
//            eventCount++;
//
//            if (sortedEvents.get(eventSize)==null)
//                sortedEvents.put(eventSize, new HashSet<>());
//
//            sortedEvents.get(eventSize).add(ev);
//        }
//
//        for (Map.Entry<Integer, Set<Set<Activity>>> e : sortedEvents.entrySet()) {
//            if (activityCount > activityAmount)
//                break;
//
//            for (Set<Activity> event : e.getValue())
//                if (activityAmount > activityCount) {
//                    selectedEvents.add(event);
//                    activityCount = activityCount + e.getKey();
//                } else
//                    break;
//        }
//
//        Collections.sort(selectedEvents, (o1, o2) -> Integer.compare(orderedEvents.get(o1), orderedEvents.get(o2)));
//
//
//        /*
//        ----------------------------------------------------------- ADD MISSING ACTIVITIES FROM PARENT2 ----------------------
//         */
//
//        Set<Activity> eventActivities = new HashSet<>();
//        for (Set<Activity> event : selectedEvents) {
//            eventActivities.addAll(event);
////            System.out.println("Selected event: " + event);
//        }
//
//        List<Activity> p2Activities = new ArrayList<>(p2.getActivities());
//        List<Activity> childActivities = new ArrayList<>();
//        for (Set<Activity> event : selectedEvents) {
//            Set<Activity> predecessors = new HashSet<>();
//            Set<Activity> successors = new HashSet<>();
//            for (Activity a : event) {
//                predecessors.addAll(getAllPredecessors(predecessors, a));
//                successors.addAll(getAllSuccessors(successors, a));
//            }
//
//            childActivities.forEach(p2Activities::remove);
//            childActivities.forEach(predecessors::remove);
//
//            for (Activity a : p2Activities) {
//                if (!eventActivities.contains(a) && !successors.contains(a) && !childActivities.contains(a)) {
//                    if (checkPredecessors(childActivities, a)) {
//                        childActivities.add(a);
//                        predecessors.remove(a);
//                    }
//                }
//
//                if (predecessors.isEmpty())
//                    break;
//            }
//
//            childActivities.addAll(event);
//        }
//
//        for (Activity a : p2Activities)
//            if (!childActivities.contains(a))
//                childActivities.add(a);
//
//        //System.out.println(childActivities);
//
//        return new EventList(p1.getResources(), childActivities);
//    }


    /*
    --------------------------------------------------------------------- LOCAL FUNCTIONS
     */
//    private static boolean checkPredecessors(List<Activity> as, Activity a) {
//        boolean check = true;
//
//        for (Activity predecessor : a.getPredecessors())
//            if (!as.contains(predecessor))
//                check = false;
//
//
//        return check;
//    }

//    private static Set<Activity> getAllPredecessors(Set<Activity> predecessors, Activity a) {
//        for (Activity predecessor : a.getPredecessors())
//            if (predecessor.getPredecessors().size() > 0) {
//                predecessors.add(predecessor);
//                getAllPredecessors(predecessors, predecessor);
//            } else
//                predecessors.add(predecessor);
//
//        return predecessors;
//    }

//    private static Set<Activity> getAllSuccessors(Set<Activity> successors, Activity a) {
//        for (Activity successor : a.getSuccessors())
//            if (successor.getSuccessors().size() > 0) {
//                successors.add(successor);
//                getAllSuccessors(successors, successor);
//            } else
//                successors.add(successor);
//
//        return successors;
//    }
}
