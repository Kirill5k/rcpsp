package app.factory;


import app.project.ActivityList;
import app.project.EventList;
import app.project.Project;

import java.util.List;
import java.util.stream.IntStream;

import static app.util.CommonUtils.*;
import static java.util.stream.Collectors.toList;

/**
 * Created by Kirill on 29/06/2016.
 */
public class ProjectFactory {
    private ProjectFactory(){}

    public static <T extends Project> ActivityList asActivityList(T project) {
        return  ActivityList.of(project.activities(), project.resources());
    }

    public static <T extends Project> EventList asEventList(T project) {
        return EventList.of(project.activities(), project.resources());
    }

    public static <T extends Project> ActivityList asRandomActivityList(T project) {
        return ActivityList.of(randomiseActivitySequence(project.activities()), project.resources());
    }

    public static <T extends Project> EventList asRandomEventList(T project) {
        return EventList.of(randomiseActivitySequence(project.activities()), project.resources());
    }

    public static <T extends Project> List<ActivityList> asPopulationOfActivityLists(T project, int popSize) {
        return IntStream.range(0, popSize).mapToObj(i -> asRandomActivityList(project)).collect(toList());
    }

    public static <T extends ActivityList> List<EventList> asPopulationOfEventLists(T project, int popSize) {
        return IntStream.range(0, popSize).mapToObj(i -> asRandomEventList(project)).collect(toList());
    }
}
