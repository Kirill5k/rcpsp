package app.asset.casestudy;

import app.asset.Activity;
import app.asset.BenchmarkInstance;
import app.asset.EventList;
import app.utility.Benchmarks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 23/06/2016.
 */
public class CaseStudyProject {

    public static BenchmarkInstance asBenchmarkInstance(){
        return new BenchmarkInstance(getActivities(), getResourceCapacities(), "Case study");
    }

    public static EventList asEventList() {
        return new EventList(getActivities(), getResourceCapacities());
    }

    public static EventList asRandomEventList() {
        return Benchmarks.asRandomEventList(asEventList());
    }

    private static Map<Integer, Integer> getResourceCapacities(){
        Map<Integer, Integer> capacities = new HashMap<>();
        capacities.put(1, 14);
        capacities.put(2, 17);
        capacities.put(3, 14);
        capacities.put(4, 16);
        return capacities;
    }

    private static Map<Integer, Double> getResourceEfficiencies() {
        Map<Integer, Double> efficiencies = new HashMap<>();
        efficiencies.put(1, 10.);
        efficiencies.put(2, 10.);
        efficiencies.put(3, 10.);
        efficiencies.put(4, 10.);
        return efficiencies;
    }

    private static Map<Integer, Double> getResourceLearnabilities(){
        Map<Integer, Double> learnabilities = new HashMap<>();
        learnabilities.put(1, 1.);
        learnabilities.put(2, 1.);
        learnabilities.put(3, 1.);
        learnabilities.put(4, 1.);
        return learnabilities;
    }

    private static List<Activity> getActivities(){
        List<Activity> activities = new ArrayList<>();

        Map<Integer, Integer> resReq = new HashMap<>();
        Activity a0 = new Activity(0, 0, resReq);

        /*

        Map<Integer, Integer> resReq1 = new HashMap<>();
        resReq.put(1, 3);
        Activity a1 = new Activity(1, 9, resReq1);

        Map<Integer, Integer> resReq2 = new HashMap<>();
        resReq.put(3, 9);
        Activity a2 = new Activity(2, 8, resReq2);

        Map<Integer, Integer> resReq3 = new HashMap<>();
        resReq.put(2, 6);
        Activity a3 = new Activity(3, 7, resReq3);

        Map<Integer, Integer> resReq4 = new HashMap<>();
        resReq.put(2, 10);
        Activity a4 = new Activity(4, 4, resReq4);

        Map<Integer, Integer> resReq5 = new HashMap<>();
        resReq.put(4, 7);
        Activity a5 = new Activity(5, 2, resReq5);

        Map<Integer, Integer> resReq6 = new HashMap<>();
        resReq.put(4, 6);
        Activity a6 = new Activity(6, 7, resReq6);

        Map<Integer, Integer> resReq7 = new HashMap<>();
        resReq.put(1, 4);
        Activity a7 = new Activity(7, 6, resReq7);

        Map<Integer, Integer> resReq8 = new HashMap<>();
        resReq.put(3, 6);
        Activity a8 = new Activity(8, 8, resReq8);

        Map<Integer, Integer> resReq9 = new HashMap<>();
        resReq.put(3, 3);
        Activity a9 = new Activity(9, 10, resReq9);

        Map<Integer, Integer> resReq10 = new HashMap<>();
        resReq.put(4, 8);
        Activity a10 = new Activity(10, 5, resReq10);

        Map<Integer, Integer> resReq11 = new HashMap<>();
        resReq.put(1, 6);
        Activity a11 = new Activity(11, 4, resReq11);

        Map<Integer, Integer> resReq12 = new HashMap<>();
        resReq.put(1, 6);
        Activity a12 = new Activity(12, 8, resReq12);

        Map<Integer, Integer> resReq13 = new HashMap<>();
        resReq.put(2, 10);
        Activity a13 = new Activity(13, 2, resReq13);

        Map<Integer, Integer> resReq14 = new HashMap<>();
        resReq.put(4, 5);
        Activity a14 = new Activity(14, 10, resReq14);

        Map<Integer, Integer> resReq15 = new HashMap<>();
        resReq.put(1, 3);
        Activity a15 = new Activity(15, 8, resReq15);

        Map<Integer, Integer> resReq16 = new HashMap<>();
        resReq.put(4, 9);
        Activity a16 = new Activity(16, 3, resReq16);

        Map<Integer, Integer> resReq17 = new HashMap<>();
        resReq.put(3, 6);
        Activity a17 = new Activity(17, 1, resReq17);

        */

        Map<Integer, Integer> resReq18 = new HashMap<>();
        resReq18.put(1, 5);
        resReq18.put(2, 4);
        resReq18.put(3, 7);
        resReq18.put(4, 7);
        Activity a18 = new Activity(18, 5, resReq18);

        Map<Integer, Integer> resReq19 = new HashMap<>();
        resReq19.put(3, 6);
        resReq19.put(4, 2);
        Activity a19 = new Activity(19, 2, resReq19);

        Map<Integer, Integer> resReq20 = new HashMap<>();
        resReq20.put(1, 5);
        resReq20.put(2, 8);
        resReq20.put(3, 6);
        resReq20.put(4, 9);
        Activity a20 = new Activity(20, 6, resReq20);

        Map<Integer, Integer> resReq21 = new HashMap<>();
        resReq21.put(1, 4);
        resReq21.put(2, 10);
        resReq21.put(3, 2);
        resReq21.put(4, 4);
        Activity a21 = new Activity(21, 6, resReq21);

        Map<Integer, Integer> resReq22 = new HashMap<>();
        resReq22.put(1, 10);
        resReq22.put(3, 9);
        resReq22.put(4, 8);
        Activity a22 = new Activity(22, 10, resReq22);

        Map<Integer, Integer> resReq23 = new HashMap<>();
        resReq23.put(2, 9);
        resReq23.put(3, 4);
        resReq23.put(4, 2);
        Activity a23 = new Activity(23, 10, resReq23);

        Map<Integer, Integer> resReq24 = new HashMap<>();
        resReq24.put(1, 10);
        resReq24.put(4, 3);
        Activity a24 = new Activity(24, 9, resReq24);

        Map<Integer, Integer> resReq25 = new HashMap<>();
        resReq25.put(1, 3);
        resReq25.put(2, 10);
        resReq25.put(3, 2);
        Activity a25 = new Activity(25, 2, resReq25);

        Map<Integer, Integer> resReq26 = new HashMap<>();
        resReq26.put(1, 6);
        resReq26.put(2, 2);
        resReq26.put(4, 4);
        Activity a26 = new Activity(26, 1, resReq26);

        Map<Integer, Integer> resReq27 = new HashMap<>();
        resReq27.put(1, 9);
        resReq27.put(2, 2);
        resReq27.put(3, 8);
        resReq27.put(4, 5);
        Activity a27 = new Activity(27, 10, resReq27);

        Map<Integer, Integer> resReq28 = new HashMap<>();
        resReq28.put(1, 6);
        resReq28.put(3, 2);
        resReq28.put(4, 8);
        Activity a28 = new Activity(28, 5, resReq28);

        Map<Integer, Integer> resReq29 = new HashMap<>();
        resReq29.put(1, 4);
        resReq29.put(2, 3);
        resReq29.put(3, 5);
        Activity a29 = new Activity(29, 6, resReq29);

        Map<Integer, Integer> resReq30 = new HashMap<>();
        resReq30.put(1, 1);
        resReq30.put(4, 3);
        Activity a30 = new Activity(30, 10, resReq30);

        Map<Integer, Integer> resReq31 = new HashMap<>();
        resReq31.put(4, 2);
        Activity a31 = new Activity(31, 1, resReq31);

        Map<Integer, Integer> resReq32 = new HashMap<>();
        resReq32.put(1, 4);
        resReq32.put(2, 8);
        resReq32.put(4, 3);
        Activity a32 = new Activity(32, 7, resReq32);

        Map<Integer, Integer> resReq33 = new HashMap<>();
        resReq33.put(1, 4);
        resReq33.put(2, 9);
        resReq33.put(3, 1);
        resReq33.put(4, 10);
        Activity a33 = new Activity(33, 9, resReq33);

        Map<Integer, Integer> resReq34 = new HashMap<>();
        resReq34.put(2, 7);
        resReq34.put(3, 10);
        Activity a34 = new Activity(34, 5, resReq34);

        Map<Integer, Integer> resReq35 = new HashMap<>();
        resReq35.put(1, 5);
        resReq35.put(2, 6);
        resReq35.put(3, 10);
        resReq35.put(4, 7);
        Activity a35 = new Activity(35, 7, resReq35);

        Map<Integer, Integer> resReq36 = new HashMap<>();
        resReq36.put(1, 9);
        resReq36.put(2, 9);
        resReq36.put(3, 1);
        resReq36.put(4, 3);
        Activity a36 = new Activity(36, 5, resReq36);

        Map<Integer, Integer> resReq37 = new HashMap<>();
        resReq37.put(1, 8);
        resReq37.put(3, 7);
        resReq37.put(4, 4);
        Activity a37 = new Activity(37, 3, resReq37);

        Map<Integer, Integer> resReq38 = new HashMap<>();
        resReq38.put(1, 2);
        resReq38.put(2, 4);
        resReq38.put(3, 1);
        Activity a38 = new Activity(38, 1, resReq38);

        Map<Integer, Integer> resReq39 = new HashMap<>();
        resReq39.put(1, 4);
        resReq39.put(2, 4);
        resReq39.put(3, 2);
        resReq39.put(4, 2);
        Activity a39 = new Activity(39, 9, resReq39);

        Map<Integer, Integer> resReq40 = new HashMap<>();
        resReq40.put(1, 9);
        resReq40.put(2, 10);
        Activity a40 = new Activity(40, 7, resReq40);

        Map<Integer, Integer> resReq41 = new HashMap<>();
        resReq41.put(1, 2);
        resReq41.put(3, 3);
        resReq41.put(4, 7);
        Activity a41 = new Activity(41, 3, resReq41);

        Map<Integer, Integer> resReq42 = new HashMap<>();
        resReq42.put(1, 1);
        resReq42.put(2, 7);
        resReq42.put(3, 9);
        Activity a42 = new Activity(42, 2, resReq42);

        Map<Integer, Integer> resReq43 = new HashMap<>();
        resReq43.put(1, 4);
        resReq43.put(2, 10);
        resReq43.put(4, 9);
        Activity a43 = new Activity(43, 8, resReq43);

        Map<Integer, Integer> resReq44 = new HashMap<>();
        resReq44.put(1, 5);
        resReq44.put(3, 7);
        resReq44.put(4, 10);
        Activity a44 = new Activity(44, 9, resReq44);

        Map<Integer, Integer> resReq45 = new HashMap<>();
        resReq45.put(1, 5);
        resReq45.put(3, 1);
        resReq45.put(4, 1);
        Activity a45 = new Activity(45, 8, resReq45);

        Map<Integer, Integer> resReq46 = new HashMap<>();
        resReq46.put(2, 7);
        resReq46.put(3, 3);
        resReq46.put(4, 2);
        Activity a46 = new Activity(46, 2, resReq46);

        Map<Integer, Integer> resReq47 = new HashMap<>();
        resReq47.put(2, 5);
        resReq47.put(3, 3);
        resReq47.put(4, 9);
        Activity a47 = new Activity(47, 9, resReq47);

        Map<Integer, Integer> resReq48 = new HashMap<>();
        resReq48.put(1, 5);
        resReq48.put(2, 1);
        resReq48.put(3, 9);
        resReq48.put(4, 1);
        Activity a48 = new Activity(48, 1, resReq48);

        Map<Integer, Integer> resReq49 = new HashMap<>();
        resReq49.put(1, 10);
        resReq49.put(2, 5);
        resReq49.put(4, 9);
        Activity a49 = new Activity(49, 1, resReq49);

        Map<Integer, Integer> resReq50 = new HashMap<>();
        resReq50.put(1, 3);
        resReq50.put(2, 3);
        resReq50.put(3, 2);
        resReq50.put(4, 2);
        Activity a50 = new Activity(50, 7, resReq50);

        Map<Integer, Integer> resReq51 = new HashMap<>();
        resReq51.put(1, 3);
        resReq51.put(2, 3);
        resReq51.put(3, 3);
        resReq51.put(4, 3);
        Activity a51 = new Activity(51, 4, resReq51);

        Map<Integer, Integer> resReq52 = new HashMap<>();
        Activity a52 = new Activity(52, 0, resReq52);

        activities.add(a0);

        /*
        activities.add(a1);
        activities.add(a2);
        activities.add(a3);
        activities.add(a4);
        activities.add(a5);
        activities.add(a6);
        activities.add(a7);
        activities.add(a8);
        activities.add(a13);
        activities.add(a14);
        activities.add(a9);
        activities.add(a10);
        activities.add(a11);
        activities.add(a12);
        activities.add(a16);
        activities.add(a17);
        activities.add(a15);
        */

        activities.add(a18);
        activities.add(a19);
        activities.add(a20);
        activities.add(a21);
        activities.add(a22);
        activities.add(a23);
        activities.add(a24);
        activities.add(a25);
        activities.add(a26);
        activities.add(a27);
        activities.add(a28);
        activities.add(a29);
        activities.add(a30);
        activities.add(a31);
        activities.add(a32);
        activities.add(a33);
        activities.add(a34);
        activities.add(a35);
        activities.add(a36);
        activities.add(a37);
        activities.add(a38);
        activities.add(a39);
        activities.add(a40);
        activities.add(a41);
        activities.add(a42);
        activities.add(a43);
        activities.add(a44);
        activities.add(a45);
        activities.add(a46);
        activities.add(a47);
        activities.add(a48);
        activities.add(a49);
        activities.add(a50);
        activities.add(a51);
        activities.add(a52);

        /**
         * STAGE 1 & 2
         */

        a0.getSuccessors().add(a31);
        a0.getSuccessors().add(a24);
        a0.getSuccessors().add(a18);

        /*

        a1.getSuccessors().add(a3);
        a2.getSuccessors().add(a3);
        a4.getSuccessors().add(a6);
        a5.getSuccessors().add(a6);

        a3.getSuccessors().add(a7);
        a6.getSuccessors().add(a7);

        a7.getSuccessors().add(a8);
        a7.getSuccessors().add(a9);

        a8.getSuccessors().add(a13);
        a8.getSuccessors().add(a14);
        a9.getSuccessors().add(a10);
        a9.getSuccessors().add(a11);
        a9.getSuccessors().add(a12);

        a13.getSuccessors().add(a16);
        a14.getSuccessors().add(a16);
        a10.getSuccessors().add(a17);
        a11.getSuccessors().add(a17);
        a12.getSuccessors().add(a17);

        a16.getSuccessors().add(a15);
        a17.getSuccessors().add(a15);

        a15.getSuccessors().add(a18);
        a15.getSuccessors().add(a31);

        */

        a31.getSuccessors().add(a33);
        a31.getSuccessors().add(a35);
        a31.getSuccessors().add(a32);

        a24.getSuccessors().add(a30);
        a24.getSuccessors().add(a23);
        a24.getSuccessors().add(a22);

        a18.getSuccessors().add(a19);
        a18.getSuccessors().add(a20);

        a32.getSuccessors().add(a34);
        a32.getSuccessors().add(a28);

        a30.getSuccessors().add(a28);

        a23.getSuccessors().add(a41);
        a23.getSuccessors().add(a42);

        a22.getSuccessors().add(a25);
        a22.getSuccessors().add(a40);
        a22.getSuccessors().add(a47);

        a19.getSuccessors().add(a47);
        a19.getSuccessors().add(a21);

        a20.getSuccessors().add(a26);

        a21.getSuccessors().add(a27);

        a34.getSuccessors().add(a29);

        a28.getSuccessors().add(a29);

        a27.getSuccessors().add(a26);

        a35.getSuccessors().add(a36);
        a35.getSuccessors().add(a46);

        a29.getSuccessors().add(a46);

        a41.getSuccessors().add(a49);

        a25.getSuccessors().add(a42);
        a25.getSuccessors().add(a44);

        a26.getSuccessors().add(a40);

        a33.getSuccessors().add(a38);

        a36.getSuccessors().add(a38);

        a46.getSuccessors().add(a38);

        a42.getSuccessors().add(a37);

        a44.getSuccessors().add(a37);

        a47.getSuccessors().add(a43);
        a40.getSuccessors().add(a48);

        a38.getSuccessors().add(a45);
        a38.getSuccessors().add(a49);

        a37.getSuccessors().add(a39);

        a43.getSuccessors().add(a48);

        a45.getSuccessors().add(a39);

        a49.getSuccessors().add(a39);

        a48.getSuccessors().add(a51);
        a48.getSuccessors().add(a50);
        a48.getSuccessors().add(a39);

        a39.getSuccessors().add(a52);

        a50.getSuccessors().add(a52);

        a51.getSuccessors().add(a52);

        activities.forEach(a -> a.getSuccessors().forEach(s -> s.getPredecessors().add(a)));
        return activities;
    }
}

