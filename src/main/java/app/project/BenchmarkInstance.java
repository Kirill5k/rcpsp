package app.project;

import java.util.List;
import java.util.Map;

/**
 * Created by kirillb on 10/04/2017.
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
        return "BenchmarkInstance " + name;
    }
}

