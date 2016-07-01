package app.utility;

import app.asset.*;
import app.exceptions.InfeasibleActivitySequenceException;
import app.exceptions.StartingActivityNotFoundException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Kirill on 20/06/2016.
 */
public class Schedules {
    private Schedules(){}

    public static <T extends Project> boolean checkFeasibility(T project){
        List<Activity> checkedActivities = new ArrayList<>();
        for (Activity a : project.getSequence()) {
            if (a.getPredecessors().stream().filter(p -> !checkedActivities.contains(p)).count()>0)
                throw new InfeasibleActivitySequenceException("Could not find predecessors of activity " + a.getNumber());

            checkedActivities.add(a);
        }

        return true;
    }

    public static Map<Integer, List<Activity>> mergeIntoEvents(EventList el) {
        Map<Integer, List<Activity>> events = new TreeMap<>();
        el.getStartingTimes().forEach((a, st) -> events.computeIfAbsent(st, ArrayList::new).add(a));
        events.entrySet().stream().parallel().forEach(e -> Collections.sort(e.getValue()));
        return events;
    }

    public static <AL extends ActivityList> void createSerialSchedule(AL al, ScheduleType type) {
        checkFeasibility(al);
        al.getSequence().stream().forEach(a -> scheduleActivity(a, al, type));
    }

    private static <AL extends ActivityList> void scheduleActivity(Activity a, AL al, ScheduleType type) {
        if (al.getFinishTimes().isEmpty()){
            if (a.getNumber() != 0)
                throw new StartingActivityNotFoundException("Project started with activity " + a.getNumber());

            al.getFinishTimes().put(a, 0);
            return;
        }

        final int st = getStartingTime(a, al, type);
        assignStartingTime(st, a, al, type);
    }

    private static <AL extends ActivityList> int getStartingTime(Activity a, AL al, ScheduleType type) {
        int earliestStart = getEarliestPossibleStartingTime(a, al);
        if (type == ScheduleType.CRITICAL_PATH)
            return earliestStart;

        while (checkSchedulability(earliestStart, a, al, type))
            earliestStart++;

        return earliestStart;
    }

    private static int getEarliestPossibleStartingTime(Activity a, ActivityList al) {
        return al.getFinishTimes().get(
                a.getPredecessors().stream()
                .max((a1, a2) -> Integer.compare(al.getFinishTimes().get(a1), al.getFinishTimes().get(a2))).get()
        );
    }

    private static <AL extends ActivityList> boolean checkSchedulability(int t, Activity a, AL al, ScheduleType st) {
        final int d = getActivityDuration(t, a, al, st);
        return IntStream.range(t, t+d).anyMatch(i ->
            a.getResReq().entrySet().stream().anyMatch(e ->
                    al.getResConsumptions().get(e.getKey()).getOrDefault(i, 0) + e.getValue() > al.getResCapacities().get(e.getKey())));
    }

    private static void assignStartingTime(int t, Activity a, ActivityList al, ScheduleType st){
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

    private static <AL extends ActivityList> int getActivityDuration(int t, Activity a, AL al, ScheduleType type) {
        return type == ScheduleType.CASE_STUDY && t > 0 && al instanceof CaseStudyActivityList
                ? getOptimisedDuration(t, a, (CaseStudyActivityList) al) : a.getDuration();
    }

    private static int getOptimisedDuration(int t, Activity a, CaseStudyActivityList csal){
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

    private static int calculateNewDuration(Map<Integer, Double> resEffectiveness, Activity a, CaseStudyActivityList csal){
        double meanEff = a.getResReq().entrySet().stream()
                .mapToDouble(e -> resEffectiveness.get(e.getKey()) ).sum()/a.getResReq().size();


        final int d = roundUp(a.getDuration() / meanEff);
        csal.getOptimisedDurations().put(a, d);
        return d;
    }

    private static int roundUp(double d) {
        return (int)Math.round(d);
    }
}
