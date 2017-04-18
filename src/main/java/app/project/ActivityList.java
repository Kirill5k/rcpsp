package app.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import static app.util.ValidationUtils.hasCorrectStartingActivity;
import static app.util.ValidationUtils.hasFeasibleActivitySequence;


/**
 * Created by kirillb on 07/04/2017.
 */
public class ActivityList extends Project {

    protected final Map<Activity, Integer> startingTimes = new HashMap<>();
    protected final Map<Activity, Integer> finishTimes = new HashMap<>();
    protected final Map<Integer, Map<Integer, Integer>> resourceConsumptions = new HashMap<>();
    protected int makespan;

    public static ActivityList of(List<Activity> activities, Map<Integer, Integer> resources){
        return new ActivityList(activities, resources);
    }

    protected ActivityList(List<Activity> activities, Map<Integer, Integer> resources) {
        super(activities, resources);
        resources.keySet().forEach(k -> resourceConsumptions.put(k, new HashMap<>()));
        validate();
        calculateMakespan();
    }

    public int makespan(){
        return makespan;
    }

    public Map<Activity, Integer> startingTimes(){
        return startingTimes;
    }

    public Map<Activity, Integer> finishTimes(){
        return finishTimes;
    }

    private void validate(){
        hasCorrectStartingActivity(activities);
        hasFeasibleActivitySequence(activities);
    }

    private void calculateMakespan(){
        activities.stream().forEach(assignStartingTime);
        makespan = startingTimes.get(endActivity());
    }

    private Consumer<Activity> assignStartingTime = activity -> {
        int startingTime = getStartingTime(activity);
        startingTimes.put(activity, startingTime);
        finishTimes.put(activity, startingTime + activity.getDuration());
        IntStream.range(startingTime, startingTime + activity.getDuration()).forEach(markResourceConsumption(activity));
    };

    private IntConsumer markResourceConsumption(Activity activity){
        return t -> activity.getResReq().forEach((num, req) -> resourceConsumptions.get(num).put(t, resourceConsumptions.get(num).getOrDefault(t, 0) + req));
    }

    private int getStartingTime(Activity activity){
        if (activity.getNumber() == 0){
            return 0;
        } else {
            int startingTime = getEarliestPossibleStartingTime(activity);
            while (!schedulable(startingTime, activity)){
                startingTime++;
            }
            return startingTime;
        }
    }

    private int getEarliestPossibleStartingTime(Activity activity) {
        return activity.getPredecessors().stream().mapToInt(finishTimes::get).max().getAsInt();
    }

    private boolean schedulable(int startingTime, Activity activity) {
        return IntStream.range(startingTime, startingTime + activity.getDuration()).allMatch(checkResourceAvailabilities(activity));
    }

    private IntPredicate checkResourceAvailabilities(Activity activity) {
        return t -> activity.getResReq().entrySet().stream().allMatch(e -> resourceConsumptions.get(e.getKey()).getOrDefault(t, 0) + e.getValue() <= resources.get(e.getKey()));
    }

    @Override
    public String toString() {
        return startingTimes.toString();
    }
}
