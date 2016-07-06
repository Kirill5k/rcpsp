package app.utility;

import app.project.*;
import app.project.impl.CaseStudyEventList;
import app.exception.InfeasibleActivitySequenceException;
import app.exception.StartingActivityNotFoundException;

import java.util.*;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static app.utility.CommonOperations.*;

/**
 * Created by Kirill on 20/06/2016.
 */
public enum Schedules {
    FORWARD, BACKWARD, CRITICAL_PATH, CASE_STUDY;

    public static <T extends Project> boolean checkFeasibility(T project){
        List<Activity> checkedActivities = new ArrayList<>();
        for (Activity a : project.getSequence()) {
            if (a.getPredecessors().stream().filter(notContains(checkedActivities)).count()>0)
                throw new InfeasibleActivitySequenceException("Could not find predecessors of activity " + a.getNumber());

            checkedActivities.add(a);
        }

        return true;
    }

    public static void mergeIntoEvents(EventList el) {
        el.getStartingTimes().forEach((a, st) -> el.getEvents().computeIfAbsent(st, ArrayList::new).add(a));
        el.getEvents().entrySet().stream().parallel().forEach(e -> Collections.sort(e.getValue()));
    }

    public static <T extends ActivityList> void createSerialSchedule(T al, Schedules type) {
        checkFeasibility(al);
        al.getSequence().stream().forEach(a -> scheduleActivity(a, al, type));
    }

    private static <T extends ActivityList> void scheduleActivity(Activity a, T al, Schedules type) {
        if (al.getFinishTimes().isEmpty()){
            if (a.getNumber() != 0)
                throw new StartingActivityNotFoundException("Project started with activity " + a.getNumber());

            al.getFinishTimes().put(a, 0);
            return;
        }

        final int st = getStartingTime(a, al, type);
        assignStartingTime(st, a, al, type);
    }

    private static <T extends ActivityList> int getStartingTime(Activity a, T al, Schedules type) {
        int earliestStart = getEarliestPossibleStartingTime(a, al);
        if (type == Schedules.CRITICAL_PATH)
            return earliestStart;

        while (!checkSchedulability(earliestStart, a, al, type))
            earliestStart++;

        return earliestStart;
    }

    private static int getEarliestPossibleStartingTime(Activity a, ActivityList al) {
        return a.getPredecessors().stream().mapToInt(p -> al.getFinishTimes().get(p)).max().getAsInt();
    }

    private static <T extends ActivityList> boolean checkSchedulability(int t, Activity a, T al, Schedules st) {
        final int d = getActivityDuration(t, a, al, st);
        return IntStream.range(t, t+d).allMatch(checkResourceAvailabilities(a, al));
    }

    private static <T extends ActivityList> IntPredicate checkResourceAvailabilities(Activity a, T al) {
        return t -> a.getResReq().entrySet().stream().allMatch(e ->
                al.getResConsumptions().get(e.getKey()).getOrDefault(t, 0) + e.getValue() <= al.getResCapacities().get(e.getKey()));
    }

    private static void assignStartingTime(int t, Activity a, ActivityList al, Schedules st){
        final int d = getActivityDuration(t, a, al, st);
        al.getStartingTimes().put(a, t);
        al.getFinishTimes().put(a, t+d);

        IntStream.range(t, t+d).forEach(i ->
                a.getResReq().forEach((num, req) ->
                        al.getResConsumptions().get(num).put(i, al.getResConsumptions().get(num).getOrDefault(i, 0)+ req)));
    }

    /*
     * CASE STUDY METHODS
     */

    private static <T extends ActivityList> int getActivityDuration(int t, Activity a, T al, Schedules type) {
        return type == Schedules.CASE_STUDY && t > 0 && al instanceof CaseStudyEventList
                ? getOptimisedDuration(t, a, (CaseStudyEventList) al) : a.getDuration();
    }

    private static int getOptimisedDuration(int t, Activity a, CaseStudyEventList csal){
        Map<Integer, Double> resWork = a.getResReq().keySet().stream()
                .collect(Collectors.toMap(Integer::valueOf, k -> calculateResWork(t, csal.getResConsumptions().get(k), csal.getResCapacities().get(k))));

        Map<Integer, Double> resEffectiveness = resWork.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> calculateResEffectiveness(e.getValue(), csal.getResEfficiencies().get(e.getKey()), csal.getResLearnabilities().get(e.getKey()))));

        return calculateNewDuration(resEffectiveness, a, csal);
    }

    private static double calculateResWork(int t, Map<Integer, Integer> resConsumption, int resCapacity) {
        double work = 0;
        for (int i = 0; i < t; i++) {
            work += resConsumption.getOrDefault(i, 0);
        }
        return work / resCapacity / CaseStudyProject.DAYS;
    }

    private static double calculateResEffectiveness(double work, double efficiency, double learnability) {
        return 1.0 + efficiency / Math.exp(learnability / work);
    }

    private static int calculateNewDuration(Map<Integer, Double> resEffectiveness, Activity a, CaseStudyEventList csal){
        double meanEff = a.getResReq().keySet().stream()
                .mapToDouble(resEffectiveness::get).sum()/a.getResReq().size();


        final int d = roundUp(a.getDuration() / meanEff);
        csal.getOptimisedDurations().put(a, d);
        return d;
    }

    private static int roundUp(double d) {
        return (int)Math.round(d);
    }
}
