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
public class SimpleActivityList extends ActivityList implements Comparable<ActivityList> {

    public SimpleActivityList(List<Activity> activitySequence, Map<Integer, Integer> resourceCapacities) {
        super(activitySequence, resourceCapacities);

        Schedules.createSerialSchedule(this, ScheduleType.FORWARD);
        makespan = startingTimes.get(getDummyEndActivity());
    }

    @Override
    public String toString() {
        return startingTimes.toString();
    }

}
