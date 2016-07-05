package app.project.impl;

import app.project.Project;
import app.project.Activity;

import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 16/02/2016.
 */
public class BenchmarkInstance extends Project {
    private final String name;

    public BenchmarkInstance(List<Activity> activitySequence, Map<Integer, Integer> resourceCapacities, String name) {
        super(activitySequence, resourceCapacities);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String s = "BenchmarkInstance " + name + ":\r\n\tResources = "+ resCapacities;

        s += "\r\n\t" + activities.toString();

        return s;
    }
}
