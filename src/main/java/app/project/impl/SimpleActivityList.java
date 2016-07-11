package app.project.impl;

import app.project.ActivityList;
import app.project.Activity;
import app.utility.ScheduleGenerationScheme;

import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 16/02/2016.
 */
public class SimpleActivityList extends ActivityList implements Comparable<ActivityList> {

    public SimpleActivityList(List<Activity> activitySequence, Map<Integer, Integer> resourceCapacities, ScheduleGenerationScheme type) {
        super(activitySequence, resourceCapacities);

        ScheduleGenerationScheme.createSerialSchedule(this, type);
        makespan = startingTimes.get(getDummyEndActivity());
    }

    public SimpleActivityList(List<Activity> activitySequence, Map<Integer, Integer> resourceCapacities) {
        super(activitySequence, resourceCapacities);

        ScheduleGenerationScheme.createSerialSchedule(this, ScheduleGenerationScheme.FORWARD);
        makespan = startingTimes.get(getDummyEndActivity());
    }

    @Override
    public String toString() {
        return startingTimes.toString();
    }

}
