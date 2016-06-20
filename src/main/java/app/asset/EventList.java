package app.asset;

import app.utility.Schedules;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.*;

/**
 * Created by Kirill on 16/02/2016.
 */
public class EventList extends AbstractProject implements Comparable<EventList> {

//    final private SortedMap<Integer, Set<Activity>> schedule;

    public EventList(Map<Integer, Integer> resources, List<Activity> activities) {
        super(resources, activities);
//        schedule = Schedules.createSerialSchedule(this);
//        makespan = schedule.lastKey();
    }

//    public SortedMap<Integer, Set<Activity>> getSchedule() {
//        return schedule;
//    }

//    public Set<Activity> getRandomEvent() {
//        List<Integer> keys = new ArrayList<>(schedule.keySet());
//        int randNum = new Random().nextInt(keys.size()-2) +1;
//        int randKey = keys.get(randNum);
//        return schedule.get(randKey);
//    }

//    public int getEventsAmount() {
//        return schedule.size();
//    }

//    public Map<Integer, Integer> getStartingTimes() {
//        Map<Integer, Integer> startingTimes = new HashMap<>();
//
//        for (Map.Entry<Integer, Set<Activity>> e : getSchedule().entrySet())
//            for (Activity a : e.getValue())
//                startingTimes.put(a.getNumber(), e.getKey());
//
//        return startingTimes;
//    }

    @Override
    public String toString() {
        String s = "[";

        for (Activity a : activities)
            s += a.getNumber() + " ";

        return s.trim() + "]";
    }

    @Override
    public int compareTo(EventList o) {
        return Integer.compare(this.makespan, o.makespan);
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof EventList)) return false;
//
//        EventList eventList = (EventList) o;
//        if (this.getId() == eventList.getId()) return true;
//
//        return getSchedule().equals(eventList.getSchedule());
//
//    }
//
//    @Override
//    public int hashCode() {
//        return getSchedule().hashCode();
//    }
}
