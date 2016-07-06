package app.project.impl;

import app.project.ActivityList;
import app.project.Activity;
import app.utility.Schedules;

import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 16/02/2016.
 */
public class SimpleActivityList extends ActivityList implements Comparable<ActivityList> {

    public SimpleActivityList(List<Activity> activitySequence, Map<Integer, Integer> resourceCapacities, Schedules type) {
        super(activitySequence, resourceCapacities);

        Schedules.createSerialSchedule(this, type);
        makespan = startingTimes.get(getDummyEndActivity());
    }

    public SimpleActivityList(List<Activity> activitySequence, Map<Integer, Integer> resourceCapacities) {
        super(activitySequence, resourceCapacities);

        Schedules.createSerialSchedule(this, Schedules.FORWARD);
        makespan = startingTimes.get(getDummyEndActivity());
    }

    @Override
    public String toString() {
        return startingTimes.toString();
    }

}
