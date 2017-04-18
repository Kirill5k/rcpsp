package app.util;

import app.exception.InfeasibleActivitySequence;
import app.exception.StartingActivityNotFound;
import app.project.Activity;

import java.util.ArrayList;
import java.util.List;

import static app.util.CommonUtils.notContainedIn;

/**
 * Created by kirillb on 10/04/2017.
 */
public class ValidationUtils {

    private ValidationUtils(){}

    public static void hasCorrectStartingActivity(List<Activity> activities){
        if (activities == null || activities.isEmpty() || activities.get(0).getNumber() != 0){
            throw new StartingActivityNotFound("Project started with activity " + activities.get(0).getNumber());
        }
    }

    public static void hasFeasibleActivitySequence(List<Activity> activities){
        List<Activity> checkedActivities = new ArrayList<>();
        activities.stream().forEach(a -> {
            if (a.getPredecessors().stream().anyMatch(notContainedIn(checkedActivities))) {
                throw new InfeasibleActivitySequence("Could not find predecessors of activity " + a.getNumber());
            }
            checkedActivities.add(a);
        });
    }
}
