package app.utility;

import app.project.*;
import app.project.impl.SimpleActivityList;
import app.project.impl.SimpleEventList;

import java.util.ArrayList;

import static app.utility.CommonOperations.*;

/**
 * Created by Kirill on 29/06/2016.
 */
public class Projects {
    private Projects(){}

    public static <T extends Project> SimpleActivityList asActivityList(T project) {
        return new SimpleActivityList(new ArrayList<>(project.getSequence()), project.getResCapacities());
    }

    public static <T extends Project> EventList asEventList(T project) {
        return new SimpleEventList(new ArrayList<>(project.getSequence()), project.getResCapacities());
    }

    public static <T extends Project> SimpleActivityList asRandomActivityList(T project) {
        return new SimpleActivityList(randomiseActivitySequence(project.getSequence()), project.getResCapacities());
    }

    public static <T extends Project> EventList asRandomEventList(T project) {
        return new SimpleEventList(randomiseActivitySequence(project.getSequence()), project.getResCapacities());
    }
}
