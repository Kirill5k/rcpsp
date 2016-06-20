package app.main;

import app.asset.Activity;
import app.asset.BenchmarkInstance;
import app.asset.EventList;
import app.utility.Benchmarks;
import app.utility.ScheduleType;
import app.utility.Schedules;

import java.util.*;

/**
 * Created by Kirill on 16/02/2016.
 */
public class Main {
    private static List<EventList> population;
    private static List<EventList> globalOptima;
    private static BenchmarkInstance bi_30 = Benchmarks.instances30.get("J3039_3.RCP");
    private static BenchmarkInstance bi_60 = Benchmarks.instances60.get("J6039_4.RCP");
    private static BenchmarkInstance bi_120 = Benchmarks.instances120.get("J12019_4.RCP");

    public static void main(String[] args) {

        Benchmarks.asRandomEventList(bi_30).getActivities().forEach(a -> System.out.println(a.getResourceReq()));

        for (int i = 0; i < 100; i++) {
            EventList el = Benchmarks.asRandomEventList(bi_30);
            SortedMap<Integer, Set<Activity>> schedule = Schedules.createSerialSchedule(el, ScheduleType.FORWARD);
            System.out.println(schedule);
        }

    }



}
