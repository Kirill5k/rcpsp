package app.project.impl;

import app.project.ActivityList;
import app.project.EventList;
import app.project.Activity;
import app.utility.ScheduleGenerationScheme;

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

        ScheduleGenerationScheme.createSerialSchedule(this, ScheduleGenerationScheme.CASE_STUDY);
        makespan = startingTimes.get(getDummyEndActivity());
        ScheduleGenerationScheme.mergeIntoEvents(this);

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
        getEvents().entrySet().stream().flatMap(e -> e.getValue().stream()).filter(a -> a.getDuration() > 0)
                .forEach(a -> System.out.println(getStartingTimes().getOrDefault(a, 0)/5. + " - " + a.getNumber() + " - " + a.getDuration() + " - " + getOptimisedDurations().getOrDefault(a, a.getDuration()) + " - " + (100-getOptimisedDurations().getOrDefault(a, a.getDuration()) * 100 / a.getDuration())));

//        getEvents().entrySet().stream().flatMap(e -> e.getValue().stream()).filter(a -> a.getDuration() > 0)
//                .forEach(a -> System.out.print(getStartingTimes().getOrDefault(a, 0)/5. + ", "));

//        getEvents().entrySet().stream().flatMap(e -> e.getValue().stream()).filter(a -> a.getDuration() > 0)
//                .forEach(a -> System.out.print(100-getOptimisedDurations().getOrDefault(a, a.getDuration()) * 100 / a.getDuration() + ", "));
    }
}
