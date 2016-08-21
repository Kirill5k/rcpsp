package app.utility;

import app.factory.CaseStudyFactory;
import app.factory.ProjectFactory;
import app.project.*;
import app.project.impl.CaseStudyEventList;
import app.project.impl.SimpleActivityList;
import app.project.impl.SimpleEventList;
import org.apache.commons.math3.special.Gamma;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Kirill on 18/02/2016.
 */
public class CommonOperations {
    private static final double BETA = 3./2.;
    private static final double SIGMA_PART_1 = Gamma.gamma(1+BETA)*Math.sin(Math.PI*BETA/2);
    private static final double SIGMA_PART_2 = Gamma.gamma((1+BETA)/2)*BETA*Math.pow(2, (BETA-1)/2);
    private static final double SIGMA = Math.pow(SIGMA_PART_1/SIGMA_PART_2, 1/BETA);

    private static final double EVENT_CROSSOVER_THRESHOLD = 0.18;
    private static final int POPULATION_INITIALISATION_BREAK = 10000;

    private CommonOperations(){}

    public static <T extends ActivityList> double getDeviationFromCriticalPath(T result) {
        int ms = result.getMakespan();
        int cp = getCriticalPathLength(result);
        return getDeviationFromOptima(ms, cp);
    }

    public static double getDeviationFromOptima(int result, int optima) {
        return (double) (result - optima) / (double) optima * 100;
    }

    public static <T extends Project> int getCriticalPathLength(T project) {
        ActivityList cp = new SimpleActivityList(CommonOperations.randomiseActivitySequence(project.getSequence()), project.getResCapacities(), ScheduleGenerationScheme.CRITICAL_PATH);
        return cp.getMakespan();
    }

    public static EventList getBestSolution(List<EventList> population) {
        return population.stream().min(EventList::compareTo).get();
    }

    public static List<EventList> getBestSolutions(List<EventList> population) {
        int min = getBestSolution(population).getMakespan();
        return population.stream().filter(e -> e.getMakespan() == min).distinct().collect(Collectors.toList());
    }

    public static <T extends Project> List<EventList> initialisePopulation(T project, int popSize) {
        Set<Map<Activity, Integer>> uniqueSchedules = new HashSet<>();
        List<EventList> population = new ArrayList<>();

        for (int stop = 0; population.size() < popSize; stop++) {
            EventList ind = project instanceof CaseStudyEventList
                    ? CaseStudyFactory.asCaseStudyEventList() : ProjectFactory.asRandomEventList(project);

            if (notContainedIn(uniqueSchedules).test(ind.getStartingTimes()) || stop > POPULATION_INITIALISATION_BREAK) {
                population.add(ind);
                uniqueSchedules.add(ind.getStartingTimes());
            }
        }

        return population;
    }

    public static EventList eventMove(EventList el) {
        return IntStream.range(0, 10).boxed().parallel()
                .map(i -> relocateEvent(el))
                .min(ActivityList::compareTo).get();
    }

    private static EventList relocateEvent(EventList el) {
        List<Activity> newSequence = new ArrayList<>(el.getSequence());
        el.getRandomEvent().stream().forEach(a -> {
            newSequence.remove(a);
            final int minPos = a.getPredecessors().stream().mapToInt(newSequence::indexOf).max().getAsInt();
            final int maxPos = a.getSuccessors().stream().mapToInt(newSequence::indexOf).min().getAsInt();
            final int randomPos = ThreadLocalRandom.current().nextInt(minPos, maxPos)+1;
            newSequence.add(randomPos, a);
        });

        return el instanceof CaseStudyEventList ? CaseStudyFactory.asCaseStudyEventList(newSequence) : new SimpleEventList(newSequence, el.getResCapacities());
    }

    public static EventList eventCrossover(EventList p1, EventList p2) {
        return eventCrossover(p1, p2, EVENT_CROSSOVER_THRESHOLD);
    }

    public static EventList eventCrossover(EventList p1, EventList p2, double threshold) {
        List<List<Activity>> p1Events = p1.getEvents().values().stream()
                .sorted((e1, e2) -> Integer.compare(e2.size(), e1.size()))
                .limit(Math.round(threshold * p1.getEventsAmount()))
                .sorted((e1, e2) -> Integer.compare(p1.getStartingTimes().get(e1.get(0)), p1.getStartingTimes().get(e2.get(0))))
                .collect(Collectors.toList());

        List<Activity> p1Activities = p1Events.stream().flatMap(List::stream).collect(Collectors.toList());
        List<Activity> p2Activities = p2.getSequence().stream().filter(notContainedIn(p1Activities)).collect(Collectors.toList());
        List<Activity> childActivities = new ArrayList<>();

        for (List<Activity> event : p1Events) {
            Set<Activity> predecessors = getAllPredecessors(new HashSet<>(), event);
            Set<Activity> successors = getAllSuccessors(new HashSet<>(), event);

            predecessors.removeAll(childActivities);

            for (Activity a : p2Activities) {
                if (notContainedIn(successors).and(checkPredecessors(childActivities)).test(a)) {
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
        try {
            return p1 instanceof CaseStudyEventList ? CaseStudyFactory.asCaseStudyEventList(childActivities) : new SimpleEventList(childActivities, p1.getResCapacities());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return new SimpleEventList(childActivities, p1.getResCapacities());
    }

    private static Set<Activity> getAllPredecessors(Set<Activity> predecessors, List<Activity> event) {
        event.stream().forEach(a -> getAllPredecessors(predecessors, a));
        return predecessors;
    }

    private static Set<Activity> getAllPredecessors(Set<Activity> predecessors, Activity a) {
        if (a.getPredecessors().isEmpty())
            return predecessors;

        predecessors.addAll(a.getPredecessors());
        a.getPredecessors().stream().forEach(p -> getAllPredecessors(predecessors, p));
        return predecessors;
    }

    private static Set<Activity> getAllSuccessors(Set<Activity> successors, List<Activity> event) {
        event.stream().forEach(a -> getAllSuccessors(successors, a));
        return successors;
    }

    private static Set<Activity> getAllSuccessors(Set<Activity> successors, Activity a) {
        if (a.getSuccessors().isEmpty())
            return successors;

        successors.addAll(a.getSuccessors());
        a.getSuccessors().stream().forEach(s -> getAllSuccessors(successors, s));
        return successors;
    }

    public static Predicate<Activity> checkPredecessors(List<Activity> activitySequence) {
        return a -> activitySequence.containsAll(a.getPredecessors());
    }

    public static <T> Predicate<T> notContainedIn(Collection<T> activitySequence) {
        return a -> !activitySequence.contains(a);
    }

    public static List<Activity> randomiseActivitySequence(List<Activity> activitySequence) {
        List<Activity> activities = new ArrayList<>(activitySequence);
        List<Activity> result = new ArrayList<>();

        Activity aStart = activities.stream().min(Activity::compareTo).get();
        Activity aEnd = activities.stream().max(Activity::compareTo).get();

        activities.remove(aStart);
        activities.remove(aEnd);

        result.add(aStart);

        while (!activities.isEmpty()){
            Activity a = activities.get(ThreadLocalRandom.current().nextInt(activities.size()));
            if (checkPredecessors(result).test(a)){
                result.add(a);
                activities.remove(a);
            }
        }

        result.add(aEnd);
        return result;
    }

    public static double getLevyNumber() {
        double u = ThreadLocalRandom.current().nextDouble(SIGMA);
        double v = ThreadLocalRandom.current().nextDouble(1);
        return u / Math.pow(Math.abs(v), 1/BETA);
    }
}
