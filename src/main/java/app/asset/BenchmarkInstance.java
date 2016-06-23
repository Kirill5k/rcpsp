package app.asset;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 16/02/2016.
 */
public class BenchmarkInstance extends AbstractProject {
    private final String name;

    public BenchmarkInstance(List<Activity> activities, Map<Integer, Integer> resourceCapacities, String name) {
        super(activities, resourceCapacities);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String s = "BenchmarkInstance " + name + ":\r\n\tResources = "+ resourceCapacities;

        for (Activity a : this.activities)
            s += "\r\n\t" + a.toString();

        return s;
    }
}
