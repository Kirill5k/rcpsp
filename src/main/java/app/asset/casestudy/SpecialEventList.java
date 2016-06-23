package app.asset.casestudy;

import app.asset.AbstractProject;
import app.asset.Activity;
import app.utility.ScheduleType;
import app.utility.Schedules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Kirill on 23/06/2016.
 */
public class SpecialEventList extends AbstractProject {

    private final Map<Integer, Double> resourceEfficiencies;
    private final Map<Integer, Double> resourceLearnabilities;

    private final SortedMap<Double, List<Activity>> schedule;
    private final Map<Activity, Double> startingTimes = new HashMap<>();

    public SpecialEventList(List<Activity> activities, Map<Integer, Integer> resourceCapacities, Map<Integer, Double> resourceEfficiencies, Map<Integer, Double> resourceLearnabilities) {
        super(activities, resourceCapacities);
        this.resourceEfficiencies = resourceEfficiencies;
        this.resourceLearnabilities = resourceLearnabilities;

        schedule = Schedules.createSerialSchedule(this, ScheduleType.FORWARD);
        schedule.entrySet().stream().forEach(e -> e.getValue().forEach(a -> startingTimes.put(a, e.getKey())));
        makespan = schedule.lastKey().intValue() + 1;
    }

    public Map<Integer, Double> getResourceEfficiencies() {
        return resourceEfficiencies;
    }

    public Map<Integer, Double> getResourceLearnabilities() {
        return resourceLearnabilities;
    }

    public SortedMap<Double, List<Activity>> getSchedule() {
        return schedule;
    }

    public Map<Activity, Double> getStartingTimes() {
        return startingTimes;
    }
}
