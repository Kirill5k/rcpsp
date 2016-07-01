package app.casestudy;

import app.asset.Activity;
import app.asset.BenchmarkInstance;
import app.asset.CaseStudyActivityList;
import app.asset.EventList;
import app.utility.Projects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 23/06/2016.
 */
public class CaseStudyProject {

    public static CaseStudyActivityList asCaseStudyActivityList(){
        return new CaseStudyActivityList(getActivities(), getResCapacities(), getResEfficiencies(), getResLearnabilities());
    }

    public static BenchmarkInstance asBenchmarkInstance(){
        return new BenchmarkInstance(getActivities(), getResCapacities(), "Case study");
    }

    public static EventList asEventList() {
        return new EventList(getActivities(), getResCapacities());
    }

    public static EventList asRandomEventList() {
        return Projects.asRandomEventList(asEventList());
    }

    private static Map<Integer, Integer> getResCapacities(){
        Map<Integer, Integer> capacities = new HashMap<>();
        capacities.put(1, 4);
        capacities.put(2, 9);
        capacities.put(3, 5);
        capacities.put(4, 5);
        return capacities;
    }

    private static Map<Integer, Double> getResEfficiencies() {
        Map<Integer, Double> efficiencies = new HashMap<>();
        efficiencies.put(1, 0.25);
        efficiencies.put(2, 0.35);
        efficiencies.put(3, 0.40);
        efficiencies.put(4, 0.55);
        return efficiencies;
    }

    private static Map<Integer, Integer> getResLearnabilities(){
        Map<Integer, Integer> learnabilities = new HashMap<>();
        learnabilities.put(1, 15);
        learnabilities.put(2, 15);
        learnabilities.put(3, 20);
        learnabilities.put(4, 25);
        return learnabilities;
    }

    private static List<Activity> getActivities(){
        List<Activity> activities = new ArrayList<>();

        Map<Integer, Integer> resReq = new HashMap<>();
        Activity a0 = new Activity(0, 0, resReq);

        Map<Integer, Integer> resReq1 = new HashMap<>();
        resReq1.put(1, 3);
        resReq1.put(2, 3);
        resReq1.put(3, 1);
        resReq1.put(4, 1);
        Activity a1 = new Activity(1, 4, resReq1);

        Map<Integer, Integer> resReq2 = new HashMap<>();
        resReq2.put(1, 1);
        resReq2.put(2, 1);
        resReq2.put(3, 3);
        resReq2.put(4, 3);
        Activity a2 = new Activity(2, 4, resReq2);

        Map<Integer, Integer> resReq3 = new HashMap<>();
        resReq3.put(1, 2);
        resReq3.put(2, 2);
        resReq3.put(3, 2);
        resReq3.put(4, 2);
        Activity a3 = new Activity(3, 2, resReq3);

        Map<Integer, Integer> resReq4 = new HashMap<>();
        resReq4.put(3, 2);
        resReq4.put(4, 3);
        Activity a4 = new Activity(4, 3, resReq4);

        Map<Integer, Integer> resReq5 = new HashMap<>();
        resReq5.put(1, 1);
        resReq5.put(2, 1);
        resReq5.put(3, 2);
        resReq5.put(4, 2);
        Activity a5 = new Activity(5, 4, resReq5);

        Map<Integer, Integer> resReq6 = new HashMap<>();
        resReq6.put(3, 2);
        resReq6.put(4, 4);
        Activity a6 = new Activity(6, 2, resReq6);

        Map<Integer, Integer> resReq7 = new HashMap<>();
        resReq7.put(1, 2);
        resReq7.put(2, 2);
        resReq7.put(3, 1);
        resReq7.put(4, 1);
        Activity a7 = new Activity(7, 6, resReq7);

        Map<Integer, Integer> resReq8 = new HashMap<>();
        resReq8.put(3, 2);
        resReq8.put(4, 2);
        Activity a8 = new Activity(8, 2, resReq8);

        Map<Integer, Integer> resReq9 = new HashMap<>();
        resReq9.put(2, 1);
        resReq9.put(3, 1);
        resReq9.put(4, 3);
        Activity a9 = new Activity(9, 2, resReq9);

        Map<Integer, Integer> resReq10 = new HashMap<>();
        resReq10.put(1, 1);
        resReq10.put(2, 3);
        resReq10.put(3, 3);
        resReq10.put(4, 1);
        Activity a10 = new Activity(10, 7, resReq10);

        Map<Integer, Integer> resReq11 = new HashMap<>();
        resReq11.put(2, 1);
        resReq11.put(3, 2);
        resReq11.put(4, 3);
        Activity a11 = new Activity(11, 6, resReq11);

        Map<Integer, Integer> resReq12 = new HashMap<>();
        resReq12.put(1, 4);
        resReq12.put(3, 2);
        Activity a12 = new Activity(12, 7, resReq12);

        Map<Integer, Integer> resReq13 = new HashMap<>();
        resReq13.put(2, 2);
        resReq13.put(3, 2);
        resReq13.put(4, 2);
        Activity a13 = new Activity(13, 5, resReq13);

        Map<Integer, Integer> resReq14 = new HashMap<>();
        resReq14.put(2, 2);
        resReq14.put(3, 2);
        Activity a14 = new Activity(14, 3, resReq14);

        Map<Integer, Integer> resReq15 = new HashMap<>();
        resReq15.put(1, 3);
        resReq15.put(2, 3);
        Activity a15 = new Activity(15, 5, resReq15);

        Map<Integer, Integer> resReq16 = new HashMap<>();
        resReq16.put(3, 2);
        resReq16.put(4, 3);
        Activity a16 = new Activity(16, 3, resReq16);

        Map<Integer, Integer> resReq17 = new HashMap<>();
        resReq17.put(1, 3);
        resReq17.put(2, 3);
        Activity a17 = new Activity(17, 9, resReq17);

        Map<Integer, Integer> resReq18 = new HashMap<>();
        resReq18.put(2, 4);
        resReq18.put(3, 2);
        resReq18.put(4, 1);
        Activity a18 = new Activity(18, 8, resReq18);

        Map<Integer, Integer> resReq19 = new HashMap<>();
        resReq19.put(2, 1);
        resReq19.put(3, 1);
        resReq19.put(4, 3);
        Activity a19 = new Activity(19, 2, resReq19);

        Map<Integer, Integer> resReq20 = new HashMap<>();
        resReq20.put(2, 2);
        resReq20.put(3, 2);
        Activity a20 = new Activity(20, 2, resReq20);

        Map<Integer, Integer> resReq21 = new HashMap<>();
        resReq21.put(2, 3);
        resReq21.put(3, 1);
        resReq21.put(4, 2);
        Activity a21 = new Activity(21, 4, resReq21);

        Map<Integer, Integer> resReq22 = new HashMap<>();
        resReq22.put(2, 3);
        resReq22.put(3, 3);
        resReq22.put(4, 1);
        Activity a22 = new Activity(22, 6, resReq22);

        Map<Integer, Integer> resReq23 = new HashMap<>();
        resReq23.put(2, 4);
        resReq23.put(3, 2);
        Activity a23 = new Activity(23, 10, resReq23);

        Map<Integer, Integer> resReq24 = new HashMap<>();
        resReq24.put(2, 4);
        resReq24.put(3, 2);
        resReq24.put(4, 2);
        Activity a24 = new Activity(24, 9, resReq24);

        Map<Integer, Integer> resReq25 = new HashMap<>();
        resReq25.put(1, 1);
        resReq25.put(2, 2);
        resReq25.put(3, 1);
        Activity a25 = new Activity(25, 2, resReq25);

        Map<Integer, Integer> resReq26 = new HashMap<>();
        resReq26.put(2, 1);
        resReq26.put(3, 1);
        resReq26.put(4, 3);
        Activity a26 = new Activity(26, 2, resReq26);

        Map<Integer, Integer> resReq27 = new HashMap<>();
        resReq27.put(2, 2);
        resReq27.put(3, 2);
        resReq27.put(4, 3);
        Activity a27 = new Activity(27, 5, resReq27);

        Map<Integer, Integer> resReq28 = new HashMap<>();
        resReq28.put(1, 1);
        resReq28.put(2, 1);
        resReq28.put(4, 3);
        Activity a28 = new Activity(28, 3, resReq28);

        Map<Integer, Integer> resReq29 = new HashMap<>();
        resReq29.put(2, 2);
        resReq29.put(3, 2);
        resReq29.put(4, 1);
        Activity a29 = new Activity(29, 2, resReq29);

        Map<Integer, Integer> resReq30 = new HashMap<>();
        resReq30.put(3, 2);
        resReq30.put(4, 4);
        Activity a30 = new Activity(30, 4, resReq30);

        Map<Integer, Integer> resReq31 = new HashMap<>();
        resReq31.put(1, 4);
        resReq31.put(3, 3);
        Activity a31 = new Activity(31, 6, resReq31);

        Map<Integer, Integer> resReq32 = new HashMap<>();
        resReq32.put(1, 3);
        resReq32.put(2, 1);
        resReq32.put(3, 3);
        Activity a32 = new Activity(32, 6, resReq32);

        Map<Integer, Integer> resReq33 = new HashMap<>();
        resReq33.put(1, 3);
        resReq33.put(3, 1);
        resReq33.put(4, 1);
        Activity a33 = new Activity(33, 2, resReq33);

        Map<Integer, Integer> resReq34 = new HashMap<>();
        resReq34.put(1, 2);
        resReq34.put(3, 1);
        resReq34.put(4, 2);
        Activity a34 = new Activity(34, 3, resReq34);

        Map<Integer, Integer> resReq35 = new HashMap<>();
        resReq35.put(1, 2);
        resReq35.put(3, 1);
        resReq35.put(4, 3);
        Activity a35 = new Activity(35, 3, resReq35);

        Map<Integer, Integer> resReq36 = new HashMap<>();
        resReq36.put(1, 3);
        resReq36.put(3, 1);
        Activity a36 = new Activity(36, 2, resReq36);

        Map<Integer, Integer> resReq37 = new HashMap<>();
        resReq37.put(2, 4);
        resReq37.put(3, 1);
        resReq37.put(4, 2);
        Activity a37 = new Activity(37, 6, resReq37);

        Map<Integer, Integer> resReq38 = new HashMap<>();
        resReq38.put(1, 4);
        resReq38.put(3, 1);
        resReq38.put(4, 2);
        Activity a38 = new Activity(38, 5, resReq38);

        Map<Integer, Integer> resReq39 = new HashMap<>();
        resReq39.put(1, 3);
        resReq39.put(2, 3);
        resReq39.put(3, 2);
        resReq39.put(4, 2);
        Activity a39 = new Activity(39, 7, resReq39);

        Map<Integer, Integer> resReq40 = new HashMap<>();
        resReq40.put(2, 2);
        resReq40.put(4, 3);
        Activity a40 = new Activity(40, 5, resReq40);

        Map<Integer, Integer> resReq41 = new HashMap<>();
        resReq41.put(1, 1);
        resReq41.put(2, 3);
        resReq41.put(3, 1);
        resReq41.put(4, 3);
        Activity a41 = new Activity(41, 4, resReq41);

        Map<Integer, Integer> resReq42 = new HashMap<>();
        resReq42.put(1, 2);
        resReq42.put(3, 3);
        resReq42.put(4, 2);
        Activity a42 = new Activity(42, 3, resReq42);

        Map<Integer, Integer> resReq43 = new HashMap<>();
        resReq43.put(1, 2);
        resReq43.put(3, 3);
        resReq43.put(4, 2);
        Activity a43 = new Activity(43, 3, resReq43);

        Map<Integer, Integer> resReq44 = new HashMap<>();
        resReq44.put(3, 3);
        resReq44.put(4, 3);
        Activity a44 = new Activity(44, 5, resReq44);

        Map<Integer, Integer> resReq45 = new HashMap<>();
        resReq45.put(1, 4);
        resReq45.put(3, 1);
        resReq45.put(4, 2);
        Activity a45 = new Activity(45, 5, resReq45);

        Map<Integer, Integer> resReq46 = new HashMap<>();
        resReq46.put(1, 2);
        resReq46.put(2, 1);
        resReq46.put(3, 2);
        resReq46.put(4, 1);
        Activity a46 = new Activity(46, 3, resReq46);

        Map<Integer, Integer> resReq47 = new HashMap<>();
        resReq47.put(2, 1);
        resReq47.put(3, 2);
        resReq47.put(4, 2);
        Activity a47 = new Activity(47, 3, resReq47);

        Map<Integer, Integer> resReq48 = new HashMap<>();
        resReq48.put(1, 2);
        resReq48.put(2, 2);
        resReq48.put(3, 2);
        resReq48.put(4, 1);
        Activity a48 = new Activity(48, 3, resReq48);

        Map<Integer, Integer> resReq49 = new HashMap<>();
        resReq49.put(1, 2);
        resReq49.put(2, 2);
        resReq49.put(4, 3);
        Activity a49 = new Activity(49, 2, resReq49);

        Map<Integer, Integer> resReq50 = new HashMap<>();
        resReq50.put(1, 3);
        resReq50.put(2, 3);
        resReq50.put(3, 2);
        resReq50.put(4, 2);
        Activity a50 = new Activity(50, 7, resReq50);

        Map<Integer, Integer> resReq51 = new HashMap<>();
        resReq51.put(1, 2);
        resReq51.put(2, 2);
        resReq51.put(3, 3);
        resReq51.put(4, 3);
        Activity a51 = new Activity(51, 4, resReq51);

        Map<Integer, Integer> resReq52 = new HashMap<>();
        Activity a52 = new Activity(52, 0, resReq52);

        activities.add(a0);

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

        a0.getSuccessors().add(a1);
        a0.getSuccessors().add(a2);
        a0.getSuccessors().add(a5);
        a0.getSuccessors().add(a4);

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
        a15.getSuccessors().add(a24);

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

