package app.asset;

import java.util.*;

/**
 * Created by Kirill on 16/02/2016.
 */
public abstract class AbstractProject {
    protected static long COUNTER = 0;
    protected final List<Activity> activities;
    protected final Map<Integer, Integer> resourceCapacities;
    protected final long id;
    protected int makespan;

    public AbstractProject(List<Activity> activities, Map<Integer, Integer> resourceCapacities) {
        this.resourceCapacities = resourceCapacities;
        this.activities = activities;
        this.id = COUNTER++;
    }

    protected void initialise(){}

    public long getId() {
        return id;
    }

    public int getMakespan() {
        return makespan;
    }

    public int size(){
        return activities.size();
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public Activity getActivityByNumber(int number) {
        for (Activity a : activities)
            if (a.getNumber() == number)
                return a;

        return null;
    }

    public Map<Integer, Integer> getResources() {
        return resourceCapacities;
    }
}
