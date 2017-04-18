package app.util;

import app.project.Activity;
import app.project.ActivityList;
import app.project.Project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by Kirill on 18/02/2016.
 */
public class CommonUtils {
    private CommonUtils(){}

    public static <T extends ActivityList> Predicate<T> hasMakespanOf(int makespan){
        return project -> project.makespan() == makespan;
    }

    public static <T> Predicate<T> notContainedIn(Collection<T> activitySequence) {
        return a -> !activitySequence.contains(a);
    }

    public static <T extends ActivityList> double getDeviationFromCriticalPath(T result) {
        return getDeviationFromOptima(result.makespan(), getCriticalPathLength(result));
    }

    public static double getDeviationFromOptima(int result, int optima) {
        return (double) (result - optima) / (double) optima * 100;
    }

    public static <T extends ActivityList> T getBestSolution(List<T> population) {
        return population.stream().min(comparing(ActivityList::makespan)).get();
    }

    public static <T extends ActivityList> List<T> getBestSolutions(List<T> population) {
        int min = getBestSolution(population).makespan();
        return population.stream().filter(hasMakespanOf(min)).distinct().collect(toList());
    }

    public static Predicate<Activity> hasScheduledPredecessors(List<Activity> activitySequence) {
        return a -> activitySequence.containsAll(a.getPredecessors());
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
            if (hasScheduledPredecessors(result).test(a)){
                result.add(a);
                activities.remove(a);
            }
        }

        result.add(aEnd);
        return result;
    }

    public static <T extends Project> int getCriticalPathLength(T project) {
        Map<Activity, Integer> finishTimes = new HashMap<>();
        project.activities().stream().forEach(scheduleActivity(finishTimes));
        return finishTimes.get(project.endActivity());
    }

    private static <T extends Project> Consumer<Activity> scheduleActivity(Map<Activity, Integer> finishTimes) {
        return activity -> {
            int startingTime = activity.getNumber() == 0 ? 0 : getEarliestPossibleStartingTime(activity, finishTimes);
            finishTimes.put(activity, startingTime + activity.getDuration());
        };
    }

    private static int getEarliestPossibleStartingTime(Activity a, Map<Activity, Integer> finishTimes) {
        return a.getPredecessors().stream().mapToInt(finishTimes::get).max().getAsInt();
    }
}
