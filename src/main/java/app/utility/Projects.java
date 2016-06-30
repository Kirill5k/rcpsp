package app.utility;

import app.asset.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirill on 29/06/2016.
 */
public class Projects {
    private Projects(){}

    public static <T extends AbstractProject> ActivityList asActivityList(T project) {
        return new ActivityList(new ArrayList<>(project.getSequence()), project.getResCapacities());
    }

    public static <T extends AbstractProject> EventList asEventList(T project) {
        return new EventList(new ArrayList<>(project.getSequence()), project.getResCapacities());
    }

    public static <T extends AbstractProject> ActivityList asRandomActivityList(T project) {
        return new ActivityList(randomise(project.getSequence()), project.getResCapacities());
    }

    public static <T extends AbstractProject> EventList asRandomEventList(T project) {
        return new EventList(randomise(project.getSequence()), project.getResCapacities());
    }

    private static List<Activity> randomise(List<Activity> activitySequence) {
        List<Activity> result = new ArrayList<>();

        List<Activity> dependencies = new ArrayList<>();
        for (Activity a : activitySequence)
            if (a.getNumber()==0)
                result.add(a);
            else
                dependencies.addAll(a.getSuccessors());

        while (activitySequence.size() != result.size()) {
            Activity a = activitySequence.get((int)(Math.random() * activitySequence.size()));
            if (!result.contains(a)) {
                if (!dependencies.contains(a)) {
                    result.add(a);
                    a.getSuccessors().forEach(dependencies::remove);
                }
            }
        }

        return result;
    }
}
