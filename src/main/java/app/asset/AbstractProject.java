package app.asset;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kirill on 16/02/2016.
 */
public abstract class AbstractProject {
    protected static long COUNTER = 0;

    protected final Map<Integer, Integer> resCapacities;
    protected final Map<Integer, Activity> activities;
    protected final List<Activity> activitySequence;
    protected final long id;

    public AbstractProject(List<Activity> activitySequence, Map<Integer, Integer> resCapacities) {
        this.id = COUNTER++;
        this.resCapacities = resCapacities;
        this.activities = activitySequence.stream().collect(Collectors.toMap(a -> a.getNumber(), a -> a));
        this.activitySequence = activitySequence;
    }

    public long getId() {
        return id;
    }

    public int size(){
        return activities.size();
    }

    public Map<Integer, Activity> getActivities() {
        return activities;
    }

    public List<Activity> getSequence() {
        return activitySequence;
    }

    public Activity getActivity(int number) {
        return activities.get(number);
    }

    public Map<Integer, Integer> getResCapacities() {
        return resCapacities;
    }
}
