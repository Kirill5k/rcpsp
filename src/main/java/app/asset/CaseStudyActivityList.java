package app.asset;

import app.asset.AbstractProject;
import app.asset.Activity;
import app.casestudy.CaseStudyScheduleUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 23/06/2016.
 */
public class CaseStudyActivityList extends AbstractProject {

    private final Map<Integer, Double> resEfficiencies;
    private final Map<Integer, Integer> resLearnabilities;

    private final Map<Activity, Double> startingTimes;
    private final double makespan;

    private final Map<Activity, Double> finishTimes = new HashMap<>();
    private final Map<Integer, Map<Double, Integer>> resConsumptions = new HashMap<>();
    private final Map<Activity, Double> optimisedDurations = new HashMap<>();

    public CaseStudyActivityList(List<Activity> activitySequence, Map<Integer, Integer> resCapacities, Map<Integer, Double> resEfficiencies, Map<Integer, Integer> resLearnabilities) {
        super(activitySequence, resCapacities);
        this.resEfficiencies = resEfficiencies;
        this.resLearnabilities = resLearnabilities;
        resCapacities.keySet().forEach(k -> resConsumptions.put(k, new HashMap<>()));

        startingTimes = CaseStudyScheduleUtil.createSerialSchedule(this);
        makespan = startingTimes.get(activities.get(activities.size()-1));
    }

    public double getMakespan() {
        return makespan;
    }

    public Map<Activity, Double> getFinishTimes() {
        return finishTimes;
    }

    public Map<Integer, Map<Double, Integer>> getResConsumptions() {
        return resConsumptions;
    }

    public Map<Integer, Double> getResEfficiencies() {
        return resEfficiencies;
    }

    public Map<Integer, Integer> getResLearnabilities() {
        return resLearnabilities;
    }

    public Map<Activity, Double> getStartingTimes() {
        return startingTimes;
    }

    public Map<Activity, Double> getOptimisedDurations() {
        return optimisedDurations;
    }
}
