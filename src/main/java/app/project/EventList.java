package app.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by kirillb on 10/04/2017.
 */
public class EventList extends ActivityList {
    private final Map<Integer, List<Activity>> events = new TreeMap<>();

    public static EventList of(List<Activity> activities, Map<Integer, Integer> resources){
        return new EventList(activities, resources);
    }

    private EventList(List<Activity> activities, Map<Integer, Integer> resources) {
        super(activities, resources);
        events.putAll(activities.stream().collect(groupingBy(startingTimes::get)));
    }

    public Map<Integer, List<Activity>> events() {
        return events;
    }

    public List<Activity> randomEvent() {
        int randNum = ThreadLocalRandom.current().nextInt(1, events.size()-1);
        int randKey = new ArrayList<>(events.keySet()).get(randNum);
        return events.get(randKey);
    }

    public int eventsAmount() {
        return events.size();
    }

    @Override
    public String toString() {
        return events.toString();
    }
}
