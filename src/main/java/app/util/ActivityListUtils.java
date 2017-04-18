package app.util;

import app.project.Activity;
import app.project.ActivityList;
import app.project.EventList;
import app.project.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static app.factory.ProjectFactory.asRandomActivityList;
import static app.factory.ProjectFactory.asRandomEventList;
import static app.util.CommonUtils.hasScheduledPredecessors;
import static app.util.CommonUtils.notContainedIn;
import static java.util.Comparator.comparing;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toList;

/**
 * Created by Kirill on 15/04/2017.
 */
public class ActivityListUtils {
    private ActivityListUtils(){}

    public static <T extends Project> List<ActivityList> initialisePopulation(T project, int popSize) {
        return IntStream.range(0, popSize).mapToObj(i -> asRandomActivityList(project)).collect(toList());
    }

    public static ActivityList randShiftMove(ActivityList el, int amountOfActivitiesToMove) {
        ActivityList result = el;
        for (int i = 0; i < amountOfActivitiesToMove; i++) {
            result = randShiftMove(result);
        }
        return result;
    }

    public static ActivityList randShiftMove(ActivityList al) {
        List<Activity> newSequence = new ArrayList<>(al.activities());
        Activity randActivity = al.randomActivity();
        newSequence.remove(randActivity);
        int minPos = randActivity.getPredecessors().stream().mapToInt(newSequence::indexOf).max().getAsInt();
        int maxPos = randActivity.getSuccessors().stream().mapToInt(newSequence::indexOf).min().getAsInt();
        newSequence.add(current().nextInt(minPos, maxPos)+1, randActivity);
        return ActivityList.of(newSequence, al.resources());
    }

    public static ActivityList twoPointCrossover(ActivityList al1, ActivityList al2) {
        int p1 = current().nextInt(1, al1.size()/2);
        int p2 = current().nextInt(1, al1.size()/2) + p1;
        return twoPointCrossover(al1, al2, p1, p2);
    }

    public static ActivityList twoPointCrossover(ActivityList parent1, ActivityList parent2, int point1, int point2) {
        List<Activity> p1Activities = IntStream.range(point1, point2).mapToObj(i -> parent1.activities().get(i)).collect(toList());
        List<Activity> p2Activities = parent2.activities().stream().filter(notContainedIn(p1Activities)).collect(toList());
        List<Activity> childActivities = new ArrayList<>();
        p2Activities.stream().forEachOrdered(a -> {
            if (a.getNumber() == 0 || hasScheduledPredecessors(childActivities).test(a)){
                childActivities.add(a);
            }
        });
        p1Activities.stream().forEachOrdered(childActivities::add);
        p2Activities.stream().filter(notContainedIn(childActivities)).forEachOrdered(childActivities::add);
        return ActivityList.of(childActivities, parent1.resources());
    }
}
