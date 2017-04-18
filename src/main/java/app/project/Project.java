package app.project;

import app.project.Activity;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toMap;

/**
 * Created by Kirill on 16/02/2016.
 */
public abstract class Project {
    protected static long COUNTER = 0;

    protected final Map<Integer, Integer> resources;
    protected final Map<Integer, Activity> activityMap;
    protected final List<Activity> activities;
    protected final long id;

    Project(List<Activity> activities, Map<Integer, Integer> resources) {
        this.id = COUNTER++;
        this.resources = resources;
        this.activities = activities;
        this.activityMap = activities.stream().collect(toMap(Activity::getNumber, Function.identity()));
    }

    public long getId() {
        return id;
    }

    public int size(){
        return activities.size();
    }

    public Activity startActivity(){
        return activityMap.get(0);
    }

    public Activity endActivity(){
        return activityMap.get(activities.size()-1);
    }

    public Activity randomActivity() {
        return activityMap.get(current().nextInt(1, activities.size()-2));
    }

    public List<Activity> activities() {
        return activities;
    }

    public Map<Integer, Integer> resources() {
        return resources;
    }
}
