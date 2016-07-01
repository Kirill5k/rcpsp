package app.asset;

import app.utility.ScheduleType;
import app.utility.Schedules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by Kirill on 23/06/2016.
 */
public class CaseStudyActivityList extends ActivityList implements Comparable<ActivityList> {

    private final Map<Integer, Double> resEfficiencies;
    private final Map<Integer, Integer> resLearnabilities;
    private final Map<Activity, Integer> optimisedDurations = new HashMap<>();

    public CaseStudyActivityList(List<Activity> activitySequence, Map<Integer, Integer> resCapacities, Map<Integer, Double> resEfficiencies, Map<Integer, Integer> resLearnabilities) {
        super(activitySequence, resCapacities);
        this.resEfficiencies = resEfficiencies;
        this.resLearnabilities = resLearnabilities;
        optimisedDurations.put(activities.get(0), 0);
        Schedules.createSerialSchedule(this, ScheduleType.CASE_STUDY);
    }


    public Map<Integer, Double> getResEfficiencies() {
        return resEfficiencies;
    }

    public Map<Integer, Integer> getResLearnabilities() {
        return resLearnabilities;
    }

    public Map<Activity, Integer> getOptimisedDurations() {
        return optimisedDurations;
    }

    public void compareMeanVsOptimisedDurations(){
        System.out.println("Schedule: ");
        System.out.println(startingTimes);
        System.out.println("Original: ");
        Map<Activity, Integer> m = activitySequence.stream().collect(Collectors.toMap(a -> a, Activity::getDuration));
        System.out.println(new TreeMap<>(m));
        System.out.println("Optimised: ");
        System.out.println(new TreeMap<>(optimisedDurations));
    }
}
