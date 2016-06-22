package app.main;

import app.algorithm.AlgorithmType;
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
        Tests.fullTestGA(Benchmarks.instances30.entrySet(), 100, 1000, 0.3, AlgorithmType.NORMAL_GA);

//        int success = 0;
//        long start = new Date().getTime();
//        for (int i = 0; i < 10000; i++) {
//            EventList el1 = Benchmarks.asRandomEventList(bi_30);
//            EventList el2 = Benchmarks.asRandomEventList(bi_30);
//            EventList el3 = CommonOperations.eventCrossover(el1, el2, 0.18);
//            if (el1.getMakespan() > el3.getMakespan() && el2.getMakespan() > el3.getMakespan())
//                success++;
//        }
//        long finish = new Date().getTime();
//        System.out.println(success);
//        System.out.println("Took " + (finish-start));


    }
}
