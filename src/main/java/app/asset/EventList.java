package app.asset;

import app.utility.ScheduleType;
import app.utility.Schedules;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Kirill on 16/02/2016.
 */
public class EventList extends ActivityList implements Comparable<ActivityList> {

    private Map<Integer, List<Activity>> events;

    public EventList(List<Activity> activitySequence, Map<Integer, Integer> resourceCapacities) {
        super(activitySequence, resourceCapacities);
        Schedules.createSerialSchedule(this, ScheduleType.FORWARD);
        makespan = startingTimes.get(activities.get(activities.size()-1));
        events = Schedules.mergeIntoEvents(this);
    }

    public Map<Integer, List<Activity>> getEvents() {
        return events;
    }

    public List<Activity> getRandomEvent() {
        int randNum = ThreadLocalRandom.current().nextInt(1, events.size()-1);
        int randKey = new ArrayList<>(events.keySet()).get(randNum);
        return events.get(randKey);
    }

    public int getEventsAmount() {
        return events.size();
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
