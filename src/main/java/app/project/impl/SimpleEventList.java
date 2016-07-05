package app.project.impl;

import app.project.ActivityList;
import app.project.EventList;
import app.project.Activity;
import app.utility.ScheduleType;
import app.utility.Schedules;

import java.util.*;

/**
 * Created by Kirill on 16/02/2016.
 */
public class SimpleEventList extends EventList implements Comparable<ActivityList> {

    public SimpleEventList(List<Activity> activitySequence, Map<Integer, Integer> resourceCapacities) {
        super(activitySequence, resourceCapacities);

        Schedules.createSerialSchedule(this, ScheduleType.FORWARD);
        makespan = startingTimes.get(getDummyEndActivity());
        Schedules.mergeIntoEvents(this);
    }

    @Override
    public String toString() {
        return events.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventList)) return false;
        if (!super.equals(o)) return false;
        EventList eventList = (EventList) o;
        return Objects.equals(getStartingTimes(), eventList.getStartingTimes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStartingTimes());
    }

}
