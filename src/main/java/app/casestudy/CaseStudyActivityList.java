package app.casestudy;

import app.asset.AbstractProject;
import app.asset.Activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 23/06/2016.
 */
public class CaseStudyActivityList extends AbstractProject {

    private final Map<Integer, Double> resEfficiencies;
    private final Map<Integer, Integer> resLearnabilities;

    private final Map<Activity, Double> schedule;

    private Map<Activity, Double> finishTimes = new HashMap<>();
    Map<Integer, Map<Double, Integer>> resConsumptions = new HashMap<>();

    public CaseStudyActivityList(List<Activity> activitySequence, Map<Integer, Integer> resourceCapacities, Map<Integer, Double> resEfficiencies, Map<Integer, Integer> resLearnabilities) {
        super(activitySequence, resourceCapacities);
        this.resEfficiencies = resEfficiencies;
        this.resLearnabilities = resLearnabilities;

        schedule = CaseStudyScheduleUtil.createSerialSchedule(this);
        makespan = schedule.get(activities.get(activities.size()-1)).intValue()+1;
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

    public Map<Activity, Double> getSchedule() {
        return schedule;
    }
}
