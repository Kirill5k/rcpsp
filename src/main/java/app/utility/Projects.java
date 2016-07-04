package app.utility;

import app.asset.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Kirill on 29/06/2016.
 */
public class Projects {
    private Projects(){}

    public static <T extends Project> SimpleActivityList asActivityList(T project) {
        return new SimpleActivityList(new ArrayList<>(project.getSequence()), project.getResCapacities());
    }

    public static <T extends Project> EventList asEventList(T project) {
        return new SimpleEventList(new ArrayList<>(project.getSequence()), project.getResCapacities());
    }

    public static <T extends Project> SimpleActivityList asRandomActivityList(T project) {
        return new SimpleActivityList(randomiseActivitySequence(project.getSequence()), project.getResCapacities());
    }

    public static <T extends Project> EventList asRandomEventList(T project) {
        return new SimpleEventList(randomiseActivitySequence(project.getSequence()), project.getResCapacities());
    }

    public static List<Activity> randomiseActivitySequence(List<Activity> activitySequence) {
        List<Activity> activities = new ArrayList<>(activitySequence);
        List<Activity> result = new ArrayList<>();

        Activity a0 = activities.stream().filter(a -> a.getNumber() == 0).findAny().get();
        result.add(a0);
        activities.remove(a0);

        while (!activities.isEmpty()){
            Activity a = activities.get(ThreadLocalRandom.current().nextInt(activities.size()));
            if (checkPredecessors(a, result)){
                result.add(a);
                activities.remove(a);
            }
        }

        return result;
    }

    private static boolean checkPredecessors(Activity activity, List<Activity> activitySequence){
        return !activity.getPredecessors().stream().filter(a -> !activitySequence.contains(a)).findAny().isPresent();
    }
}
