package app.utility;

import app.asset.Activity;
import app.asset.EventList;

import java.util.*;

/**
 * Created by Kirill on 20/06/2016.
 */
public class Schedules2 {
    private Schedules2(){}

    public static SortedMap<Integer, Set<Activity>> createSerialSchedule(EventList el) {
        Map<Activity, Integer> finishTimes = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> resourceConsumptions = new HashMap<>();
        el.getResources().forEach((k, v) -> resourceConsumptions.computeIfAbsent(k, HashMap::new).put(-1, v));
        el.getActivities().stream().forEach(a -> scheduleActivity(a, finishTimes, resourceConsumptions));

        SortedMap<Integer, Set<Activity>> schedule = new TreeMap<>();
        finishTimes.forEach((a, ft) -> schedule.computeIfAbsent(ft-a.getDuration(), HashSet::new).add(a));
        return schedule;
    }

    private static void scheduleActivity(Activity activity, Map<Activity, Integer> finishTimes, Map<Integer, Map<Integer, Integer>> resourceConsumptions) {
        if (activity.getNumber() == 0){
            finishTimes.put(activity, 0);
            return;
        }

        int earliestStart = getEarliestPossibleStartingTime(activity, finishTimes);
        while (!checkSchedulability(earliestStart, activity, resourceConsumptions))
            earliestStart++;

        final int st = earliestStart;
        finishTimes.put(activity, earliestStart+activity.getDuration());
        resourceConsumptions.forEach((n, r) -> {
            for (int i = st; i < st + activity.getDuration(); i++) {
                r.put(i, r.getOrDefault(i, 0) + activity.getResourceReq().get(n));
            }
        });
    }

    private static int getEarliestPossibleStartingTime(Activity activity, Map<Activity, Integer> finishTimes) {
        return finishTimes.get(
                activity.getPredecessors().stream()
                .max((a1, a2) -> Integer.compare(finishTimes.get(a1), finishTimes.get(a2))).get()
        );
    }

    private static boolean checkSchedulability(int time, Activity activity, Map<Integer, Map<Integer, Integer>> resourceConsumptions) {
        for (Map.Entry<Integer, Map<Integer, Integer>> e : resourceConsumptions.entrySet()) {
            Map<Integer, Integer> r = e.getValue();
            int n = e.getKey();

            if (r.getOrDefault(time, 0) + activity.getResourceReq().get(n) >= r.get(-1))
                return false;
        }

        return true;
    }
}
