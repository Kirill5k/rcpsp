package app.project.impl;

import app.project.ActivityList;
import app.project.EventList;
import app.project.Activity;
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
public class CaseStudyEventList extends EventList implements Comparable<ActivityList> {

    private final Map<Integer, Double> resEfficiencies;
    private final Map<Integer, Integer> resLearnabilities;
    private final Map<Activity, Integer> optimisedDurations = new HashMap<>();

    public CaseStudyEventList(List<Activity> activitySequence, Map<Integer, Integer> resCapacities, Map<Integer, Double> resEfficiencies, Map<Integer, Integer> resLearnabilities) {
        super(activitySequence, resCapacities);
        this.resEfficiencies = resEfficiencies;
        this.resLearnabilities = resLearnabilities;

        Schedules.createSerialSchedule(this, ScheduleType.CASE_STUDY);
        Schedules.mergeIntoEvents(this);
        makespan = startingTimes.get(getDummyEndActivity());
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
        System.out.println(events);
        System.out.println("Original: ");
        Map<Activity, Integer> m = activitySequence.stream().collect(Collectors.toMap(a -> a, Activity::getDuration));
        System.out.println(new TreeMap<>(m));
        System.out.println("Optimised: ");
        System.out.println(new TreeMap<>(optimisedDurations));
    }
}
