package app.casestudy;

import app.asset.Activity;
import app.exceptions.StartingActivityNotFoundException;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Created by Kirill on 30/06/2016.
 */
public class CaseStudyScheduleUtil {
    private CaseStudyScheduleUtil() {
    }

    public static Map<Activity, Double> createSerialSchedule(CaseStudyActivityList csal) {
        csal.getSequence().stream().forEach(a -> scheduleActivity(a, csal));
        return new TreeMap<>(csal.getFinishTimes().entrySet().stream().collect(
                Collectors.toMap(e -> e.getKey(), e -> e.getValue()-e.getKey().getDuration())));
    }

    private static void scheduleActivity(Activity activity, CaseStudyActivityList csal) {
        if (csal.getFinishTimes().isEmpty()){
            if (activity.getNumber() != 0)
                throw new StartingActivityNotFoundException("Project started with activity " + activity.getNumber());

            csal.getFinishTimes().put(activity, 0.);
            return;
        }

        double earliestStart = getEarliestPossibleStartingTime(activity, csal);
        while (checkSchedulability(earliestStart, activity, csal))
            earliestStart += 0.2;

        final double startingTime = earliestStart;
        final double optimisedDuration = getOptimisedDuration(startingTime, activity, csal);
        csal.getFinishTimes().put(activity, earliestStart+optimisedDuration);

        // TODO: update resource consumption

        int limit = (int)(startingTime*5) + (int)(optimisedDuration* 5);
        DoubleStream.iterate(startingTime, d -> d + 0.2).limit(limit).forEach(t ->
                activity.getResReq().forEach((num, req) ->
                        csal.getResConsumptions().get(num).put(t, csal.getResConsumptions().get(num).getOrDefault(t, 0)+ req)));
    }

    private static double getEarliestPossibleStartingTime(Activity activity, CaseStudyActivityList csal) {
        return csal.getFinishTimes().get(
                activity.getPredecessors().stream()
                        .max((a1, a2) -> Double.compare(csal.getFinishTimes().get(a1), csal.getFinishTimes().get(a2))).get()
        );
    }

    private static boolean checkSchedulability(double time, Activity activity, CaseStudyActivityList csal) {
        double duration = time > 0 ? getOptimisedDuration(time, activity, csal) : activity.getDuration();
        int limit = (int)(time*5) + (int)(duration* 5);
        return DoubleStream.iterate(time, d -> d + 0.2).limit(limit).anyMatch(d ->
                activity.getResReq().entrySet().stream().anyMatch(e ->
                        csal.getResConsumptions().get(e.getKey()).getOrDefault(d, 0) + e.getValue() > csal.getResCapacities().get(e.getKey())));
    }

    private static double getOptimisedDuration(double time, Activity activity, CaseStudyActivityList csal){
        Map<Integer, Double> resWork = activity.getResReq().keySet().stream()
                .collect(Collectors.toMap(Integer::valueOf, k -> calculateResWork(time, csal.getResConsumptions().get(k), csal.getResCapacities().get(k))));

        Map<Integer, Double> resEffectiveness = resWork.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> calculateResEffectiveness(e.getValue(), csal.getResEfficiencies().get(e.getKey()), csal.getResLearnabilities().get(e.getKey()))));

        double meanEfficiency = resEffectiveness.entrySet().stream().mapToDouble(e -> e.getValue() * csal.getResCapacities().get(e.getKey()) / activity.getResReq().get(e.getKey())).sum();
        return activity.getDuration() / meanEfficiency;
    }

    private static double calculateResWork(double time, Map<Double, Integer> resourceConsumption, int resourceCapacity) {
        double work = 0;
        for (double t = 0; t < time; t+=0.2) {
            work += resourceConsumption.getOrDefault(t, 0) * 100. / resourceCapacity;
        }
        return work / 5 / time;
    }

    private static double calculateResEffectiveness(double work, double efficiency, double learnability) {
        return 1.0 + efficiency / (learnability * work);
    }

}
