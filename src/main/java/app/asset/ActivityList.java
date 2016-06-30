package app.asset;

import app.utility.ScheduleType;
import app.utility.Schedules;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Kirill on 16/02/2016.
 */
public class ActivityList extends AbstractProject implements Comparable<ActivityList> {

    private final SortedMap<Activity, Integer> schedule;

    public ActivityList(List<Activity> activitySequence, Map<Integer, Integer> resourceCapacities) {
        super(activitySequence, resourceCapacities);
        schedule = Schedules.createSerialSchedule(this, ScheduleType.FORWARD);
        makespan = schedule.get(schedule.lastKey());
    }

    public SortedMap<Activity, Integer> getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        String s = "[";

        for (Activity a : activitySequence)
            s += a.getNumber() + " ";

        return s.trim() + "]";
    }

    @Override
    public int compareTo(ActivityList o) {
        return Integer.compare(this.makespan, o.makespan);
    }
}
