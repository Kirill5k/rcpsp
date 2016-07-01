package app.asset;

import app.utility.ScheduleType;
import app.utility.Schedules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Kirill on 16/02/2016.
 */
public class ActivityList extends AbstractProject implements Comparable<ActivityList> {

    protected final SortedMap<Activity, Integer> startingTimes;
    protected final int makespan;

    protected final Map<Integer, Map<Integer, Integer>> resConsumptions = new HashMap<>();
    protected final Map<Activity, Integer> finishTimes = new HashMap<>();

    public ActivityList(List<Activity> activitySequence, Map<Integer, Integer> resourceCapacities, ScheduleType type) {
        super(activitySequence, resourceCapacities);
        resCapacities.keySet().forEach(k -> resConsumptions.put(k, new HashMap<>()));
        startingTimes = Schedules.createSerialSchedule(this, type);
        makespan = startingTimes.get(startingTimes.lastKey());
    }

    public ActivityList(List<Activity> activitySequence, Map<Integer, Integer> resourceCapacities) {
        this(activitySequence, resourceCapacities, ScheduleType.FORWARD);
    }

    public SortedMap<Activity, Integer> getStartingTimes() {
        return startingTimes;
    }

    public Map<Integer, Map<Integer, Integer>> getResConsumptions() {
        return resConsumptions;
    }

    public Map<Activity, Integer> getFinishTimes() {
        return finishTimes;
    }

    public int getMakespan() {
        return makespan;
    }

    @Override
    public String toString() {
        return startingTimes.toString();
    }

    @Override
    public int compareTo(ActivityList o) {
        return Integer.compare(this.makespan, o.makespan);
    }
}
