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

    public BenchmarkInstance(String name, Map<Integer, Integer> resources, List<Activity> activities) {
        super(resources, activities);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String s = "BenchmarkInstance " + name + ":\r\n\tResources = "+ resources;

        for (Activity a : this.activities)
            s += "\r\n\t" + a.toString();

        return s;
    }
}
