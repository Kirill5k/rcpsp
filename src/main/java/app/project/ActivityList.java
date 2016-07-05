package app.project;

import java.util.*;

/**
 * Created by Kirill on 01/07/2016.
 */
public abstract class ActivityList extends Project implements Comparable<ActivityList> {


    protected int makespan;
    protected Map<Activity, Integer> startingTimes = new TreeMap<>();
    protected Map<Integer, Map<Integer, Integer>> resConsumptions = new HashMap<>();
    protected Map<Activity, Integer> finishTimes = new HashMap<>();

    public ActivityList(List<Activity> activitySequence, Map<Integer, Integer> resCapacities) {
        super(activitySequence, resCapacities);
        resCapacities.keySet().forEach(k -> resConsumptions.put(k, new HashMap<>()));
    }

    public Map<Activity, Integer> getStartingTimes() {
        return startingTimes;
    }

    public Map<Integer, Map<Integer, Integer>> getResConsumptions() {
        return resConsumptions;
    }

    public Map<Activity, Integer> getFinishTimes() {
        return finishTimes;
    }

    public int getMakespan() {
        return makespan;
    }

    @Override
    public String toString() {
        return startingTimes.toString();
    }

    @Override
    public int compareTo(ActivityList o) {
        return Integer.compare(this.makespan, o.makespan);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityList)) return false;
        ActivityList that = (ActivityList) o;
        return Objects.equals(getStartingTimes(), that.getStartingTimes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartingTimes());
    }
}
