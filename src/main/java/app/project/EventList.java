package app.project;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Kirill on 01/07/2016.
 */
public abstract class EventList extends ActivityList {

    protected Map<Integer, List<Activity>> events = new TreeMap<>();

    public EventList(List<Activity> activitySequence, Map<Integer, Integer> resCapacities) {
        super(activitySequence, resCapacities);
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
}
