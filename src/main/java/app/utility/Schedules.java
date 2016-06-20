package app.utility;

import app.asset.Activity;
import app.asset.EventList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Kirill on 20/06/2016.
 */
public class Schedules {
    private Schedules(){}

    public static SortedMap<Integer, Set<Activity>> createSerialSchedule(EventList el, ScheduleType type) {
        Map<Activity, Integer> finishTimes = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> resourceConsumptions = new HashMap<>();
        el.getResources().forEach((k, v) -> resourceConsumptions.computeIfAbsent(k, HashMap::new).put(-1, v));
        el.getActivities().stream().forEach(a -> scheduleActivity(a, finishTimes, resourceConsumptions, type));

        SortedMap<Integer, Set<Activity>> schedule = new TreeMap<>();
        finishTimes.forEach((a, ft) -> schedule.computeIfAbsent(ft-a.getDuration(), HashSet::new).add(a));
        return schedule;
    }

    private static void scheduleActivity(Activity activity, Map<Activity, Integer> finishTimes, Map<Integer, Map<Integer, Integer>> resourceConsumptions, ScheduleType type) {
        if (activity.getNumber() == 0){
            finishTimes.put(activity, 0);
            return;
        }

        int earliestStart = getEarliestPossibleStartingTime(activity, finishTimes);
        while (type != ScheduleType.CRITICAL_PATH && checkSchedulability(earliestStart, activity, resourceConsumptions))
            earliestStart++;

        final int st = earliestStart;
        finishTimes.put(activity, earliestStart+activity.getDuration());

        IntStream.range(st, st+activity.getDuration()).forEach(t ->
                activity.getResourceReq().forEach((num, req) ->
                        resourceConsumptions.get(num).put(t, resourceConsumptions.get(num).getOrDefault(t, 0)+ req)));
    }

    private static int getEarliestPossibleStartingTime(Activity activity, Map<Activity, Integer> finishTimes) {
        return finishTimes.get(
                activity.getPredecessors().stream()
                .max((a1, a2) -> Integer.compare(finishTimes.get(a1), finishTimes.get(a2))).get()
        );
    }

    private static boolean checkSchedulability(int time, Activity activity, Map<Integer, Map<Integer, Integer>> resourceConsumptions) {
        return IntStream.range(time, time+activity.getDuration()).anyMatch(i ->
            activity.getResourceReq().entrySet().stream().anyMatch(e ->
                    resourceConsumptions.get(e.getKey()).getOrDefault(i, 0) + e.getValue() > resourceConsumptions.get(e.getKey()).get(-1)));
    }
}
