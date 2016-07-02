package app.asset;

import app.utility.ScheduleType;
import app.utility.Schedules;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Kirill on 16/02/2016.
 */
public class SimpleEventList extends EventList implements Comparable<ActivityList> {

    public SimpleEventList(List<Activity> activitySequence, Map<Integer, Integer> resourceCapacities) {
        super(activitySequence, resourceCapacities);

        Schedules.createSerialSchedule(this, ScheduleType.FORWARD);
        makespan = startingTimes.get(getDummyEndActivity());
        events = Schedules.mergeIntoEvents(this);
    }

    @Override
    public String toString() {
        return events.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventList)) return false;

        EventList eventList = (EventList) o;
        if (this.getId() == eventList.getId()) return true;

        return getEvents().equals(eventList.getEvents());
    }

    @Override
    public int hashCode() {
        return getEvents().hashCode();
    }
}
