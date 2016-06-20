package app.utility;

import app.asset.AbstractProject;
import app.asset.Activity;
import app.asset.ActivityList;
import app.asset.EventList;
import app.gui.SchedulePlot;

import java.util.*;

/**
 * Created by Kirill on 16/02/2016.
 */
public class Schedules {
    private Schedules(){}


//    public static void plot(EventList el) {
//        plotOnGraph(el.getCanvas());
//        plotToConsole(el.getCanvas());
//    }

//    public static double getDeviationFromCP(EventList el) {
//        SortedMap<Activity, Integer> criticalPath = createSerialSchedule(el, ScheduleType.CRITICAL_PATH);
//        int cpMakespan = criticalPath.get(criticalPath.lastKey());
//        System.out.println("Critical path makespan: " + cpMakespan);
//
//        return (Math.abs(cpMakespan - el.getMakespan()) / (double) cpMakespan) * 100;
//    }

    /*
    --------------------------------------------- ACTIVITY LIST ----------------------------------------------------------
     */
//    public static SortedMap<Activity, Integer> createSerialSchedule(ActivityList al) {
//        return createSerialSchedule(al, ScheduleType.FORWARD);
//    }

    /*
    --------------------------------------------- EVENT LIST -------------------------------------------------------------
     */
//    public static SortedMap<Integer, Set<Activity>> createSerialSchedule(EventList el) {
//        SortedMap<Activity, Integer> forwardSchedule = createSerialSchedule(el, ScheduleType.FORWARD);
//        SortedMap<Integer, Set<Activity>> schedule = new TreeMap<>();
//
//        for (Map.Entry<Activity, Integer> e : forwardSchedule.entrySet()) {
//            if (schedule.get(e.getValue()) == null)
//                schedule.put(e.getValue(), new TreeSet<>());
//
//            schedule.get(e.getValue()).add(e.getKey());
//        }
//
//        return schedule;
//    }

    /*
    -------------------------------------------- GENERAL SCHEDULE OPERATIONS ---------------------------------------------
     */
//    private static <T extends AbstractProject> SortedMap<Activity, Integer> createSerialSchedule(T project, ScheduleType st) {
//        final SortedMap<Activity, Integer> scheduledActivities = new TreeMap<>();
//        final List<Activity> activities = new ArrayList<>(project.getActivities());
//
//        for (Activity a : activities)
//            scheduleSerialy(project.getCanvas(), a, scheduledActivities, st);
//
//        return scheduledActivities;
//    }

//    private static void scheduleSerialy(List<Map<Integer, List<Integer>>> canvases, Activity a, Map<Activity, Integer> scheduledActivities, ScheduleType st) {
//        int time = 0;
//        for (Activity predecessor : a.getPredecessors())
//            if (scheduledActivities.get(predecessor) + predecessor.getDuration() > time)
//                time = scheduledActivities.get(predecessor) + predecessor.getDuration();
//
//        while (st != ScheduleType.CRITICAL_PATH && !checkSchedulability(canvases, a, time))
//            time++;
//
//        final int finalTime = time;
//        canvases.forEach(canvas->{
//            for (int i = finalTime; i < finalTime + a.getDuration(); i++) {
//                List<Integer> l;
//                if (canvas.get(i) == null){
//                    l = new ArrayList<>();
//                    canvas.put(i,l);
//                } else {
//                    l = canvas.get(i);
//                }
//                for (int j = 0; j < a.getResourceReq()[canvases.indexOf(canvas)]; j++)
//                    l.add(a.getNumber());
//            }
//        });
//
//        scheduledActivities.put(a, time);
//    }

//    private static boolean checkSchedulability(List<Map<Integer, List<Integer>>> canvases, Activity a, int time) {
//        for (int i = 0; i < a.getDuration(); i++) {
//            for (Map<Integer, List<Integer>> canvas : canvases)
//                if (canvas.get(time+i) != null && canvas.get(time+i).size() + a.getResourceReq()[canvases.indexOf(canvas)] > canvas.get(-1).size())
//                    return false;
//        }
//
//        return true;
//    }

    /*
    -------------------------------------------- PLOT -------------------------------------------------------------------
     */
//    private static void plotToConsole(List<Map<Integer, List<Integer>>> canvases) {
//        for (Map<Integer, List<Integer>> canvas : canvases) {
//            Map<Integer, List<Integer>> c = new TreeMap<>(canvas);
//            System.out.println("Resource " + (canvases.indexOf(canvas) + 1));
//            for (Map.Entry<Integer, List<Integer>> line : c.entrySet()) {
//                System.out.print(line.getKey() + " | ");
//                System.out.print(line.getValue());
//                System.out.println();
//            }
//            System.out.println();
//        }
//    }

//    private static void plotOnGraph(List<Map<Integer, List<Integer>>> canvases) {
//        new SchedulePlot(canvases, 11);
//    }


}
