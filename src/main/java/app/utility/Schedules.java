package app.utility;

import app.asset.AbstractProject;
import app.asset.Activity;
import app.asset.ActivityList;
import app.asset.EventList;
import app.exceptions.InfeasibleScheduleException;
import app.exceptions.StartingActivityNotFoundException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Kirill on 20/06/2016.
 */
public class Schedules {
    private Schedules(){}

    public static SortedMap<Integer, List<Activity>> mergeIntoEvents(EventList el) {
        SortedMap<Integer, List<Activity>> events = new TreeMap<>();
        el.getFinishTimes().forEach((a, ft) -> events.computeIfAbsent(ft-a.getDuration(), ArrayList::new).add(a));
        events.entrySet().stream().parallel().forEach(e -> Collections.sort(e.getValue()));
        return events;
    }

    public static SortedMap<Activity, Integer> createSerialSchedule(ActivityList al, ScheduleType type) {
        assignFinishTimes(al, type);

        return new TreeMap<>(al.getFinishTimes().entrySet().stream().collect(
                Collectors.toMap(e -> e.getKey(), e -> e.getValue()-e.getKey().getDuration())));
    }

    private static void assignFinishTimes(ActivityList al, ScheduleType type) {
        checkFeasability(al);
        al.getSequence().stream().forEach(a -> scheduleActivity(a, al, type));
    }

    private static void scheduleActivity(Activity a, ActivityList al, ScheduleType type) {
        if (al.getFinishTimes().isEmpty()){
            if (a.getNumber() != 0)
                throw new StartingActivityNotFoundException("Project started with activity " + a.getNumber());

            al.getFinishTimes().put(a, 0);
            return;
        }

        int earliestStart = getEarliestPossibleStartingTime(a, al);
        while (type != ScheduleType.CRITICAL_PATH && checkSchedulability(earliestStart, a, al))
            earliestStart++;

        final int st = earliestStart;
        al.getFinishTimes().put(a, earliestStart+a.getDuration());

        IntStream.range(st, st+a.getDuration()).forEach(t ->
                a.getResReq().forEach((num, req) ->
                        al.getResConsumptions().get(num).put(t, al.getResConsumptions().get(num).getOrDefault(t, 0)+ req)));
    }

    private static int getEarliestPossibleStartingTime(Activity a, ActivityList al) {
        return al.getFinishTimes().get(
                a.getPredecessors().stream()
                .max((a1, a2) -> Integer.compare(al.getFinishTimes().get(a1), al.getFinishTimes().get(a2))).get()
        );
    }

    private static boolean checkSchedulability(int time, Activity a, ActivityList al) {
        return IntStream.range(time, time+a.getDuration()).anyMatch(i ->
            a.getResReq().entrySet().stream().anyMatch(e ->
                    al.getResConsumptions().get(e.getKey()).getOrDefault(i, 0) + e.getValue() > al.getResCapacities().get(e.getKey())));
    }

    private static <T extends AbstractProject> void  checkFeasability(T project){
        List<Activity> checkedActivities = new ArrayList<>();
        for (Activity a : project.getSequence()) {
            if (a.getPredecessors().stream().filter(p -> !checkedActivities.contains(p)).count()>0)
                throw new InfeasibleScheduleException(a.getNumber() + "");

            checkedActivities.add(a);
        }
    }
}
