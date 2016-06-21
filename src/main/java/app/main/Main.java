package app.main;

import app.algorithm.CommonOperations;
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
        //Tests.fullTestNormalGA(Benchmarks.instances30.entrySet());

        EventList el1 = Benchmarks.asRandomEventList(bi_30);
        EventList el2 = Benchmarks.asRandomEventList(bi_30);
        EventList el3 = CommonOperations.newEventCrossover(el1, el2, 0.35);
    }
}
