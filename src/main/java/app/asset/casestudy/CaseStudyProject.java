package app.asset.casestudy;

import app.asset.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 23/06/2016.
 */
public class CaseStudyProject {



    private static Map<Integer, Integer> getResourceCapacities(){
        Map<Integer, Integer> capacities = new HashMap<>();
        capacities.put(1, 10);
        capacities.put(2, 10);
        capacities.put(3, 10);
        capacities.put(4, 10);
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

        Map<Integer, Integer> resReq1 = new HashMap<>();
        resReq.put(1, 0);
        Activity a1 = new Activity(1, 0, resReq1);

        Map<Integer, Integer> resReq2 = new HashMap<>();
        resReq.put(1, 0);
        Activity a2 = new Activity(2, 0, resReq2);

        Map<Integer, Integer> resReq3 = new HashMap<>();
        resReq.put(1, 0);
        Activity a3 = new Activity(3, 0, resReq3);

        Map<Integer, Integer> resReq4 = new HashMap<>();
        resReq.put(1, 0);
        Activity a4 = new Activity(4, 0, resReq4);

        Map<Integer, Integer> resReq5 = new HashMap<>();
        resReq.put(1, 0);
        Activity a5 = new Activity(5, 0, resReq5);

        Map<Integer, Integer> resReq6 = new HashMap<>();
        resReq.put(1, 0);
        Activity a6 = new Activity(6, 0, resReq6);

        Map<Integer, Integer> resReq7 = new HashMap<>();
        resReq.put(1, 0);
        Activity a7 = new Activity(7, 0, resReq7);

        Map<Integer, Integer> resReq8 = new HashMap<>();
        resReq.put(1, 0);
        Activity a8 = new Activity(8, 0, resReq8);

        Map<Integer, Integer> resReq9 = new HashMap<>();
        resReq.put(1, 0);
        Activity a9 = new Activity(9, 0, resReq9);

        Map<Integer, Integer> resReq10 = new HashMap<>();
        resReq.put(1, 0);
        Activity a10 = new Activity(10, 0, resReq10);

        Map<Integer, Integer> resReq11 = new HashMap<>();
        resReq.put(1, 0);
        Activity a11 = new Activity(11, 0, resReq11);

        Map<Integer, Integer> resReq12 = new HashMap<>();
        resReq.put(1, 0);
        Activity a12 = new Activity(12, 0, resReq12);

        Map<Integer, Integer> resReq13 = new HashMap<>();
        resReq.put(1, 0);
        Activity a13 = new Activity(13, 0, resReq13);

        Map<Integer, Integer> resReq14 = new HashMap<>();
        resReq.put(1, 0);
        Activity a14 = new Activity(14, 0, resReq14);

        Map<Integer, Integer> resReq15 = new HashMap<>();
        resReq.put(1, 0);
        Activity a15 = new Activity(15, 0, resReq15);

        Map<Integer, Integer> resReq16 = new HashMap<>();
        resReq.put(1, 0);
        Activity a16 = new Activity(16, 0, resReq16);

        Map<Integer, Integer> resReq17 = new HashMap<>();
        resReq.put(1, 0);
        Activity a17 = new Activity(17, 0, resReq17);

        Map<Integer, Integer> resReq18 = new HashMap<>();
        resReq.put(1, 0);
        Activity a18 = new Activity(18, 0, resReq18);

        Map<Integer, Integer> resReq19 = new HashMap<>();
        resReq.put(1, 0);
        Activity a19 = new Activity(19, 0, resReq19);

        Map<Integer, Integer> resReq20 = new HashMap<>();
        resReq.put(1, 0);
        Activity a20 = new Activity(20, 0, resReq20);

        Map<Integer, Integer> resReq21 = new HashMap<>();
        resReq.put(1, 0);
        Activity a21 = new Activity(21, 0, resReq21);

        Map<Integer, Integer> resReq22 = new HashMap<>();
        resReq.put(1, 0);
        Activity a22 = new Activity(22, 0, resReq22);

        Map<Integer, Integer> resReq23 = new HashMap<>();
        resReq.put(1, 0);
        Activity a23 = new Activity(23, 0, resReq23);

        Map<Integer, Integer> resReq24 = new HashMap<>();
        resReq.put(1, 0);
        Activity a24 = new Activity(24, 0, resReq24);

        Map<Integer, Integer> resReq25 = new HashMap<>();
        resReq.put(1, 0);
        Activity a25 = new Activity(25, 0, resReq25);

        Map<Integer, Integer> resReq26 = new HashMap<>();
        resReq.put(1, 0);
        Activity a26 = new Activity(26, 0, resReq26);

        Map<Integer, Integer> resReq27 = new HashMap<>();
        resReq.put(1, 0);
        Activity a27 = new Activity(27, 0, resReq27);

        Map<Integer, Integer> resReq28 = new HashMap<>();
        resReq.put(1, 0);
        Activity a28 = new Activity(28, 0, resReq28);

        Map<Integer, Integer> resReq29 = new HashMap<>();
        resReq.put(1, 0);
        Activity a29 = new Activity(29, 0, resReq29);

        Map<Integer, Integer> resReq30 = new HashMap<>();
        resReq.put(1, 0);
        Activity a30 = new Activity(30, 0, resReq30);

        Map<Integer, Integer> resReq31 = new HashMap<>();
        resReq.put(1, 0);
        Activity a31 = new Activity(31, 0, resReq31);

        Map<Integer, Integer> resReq32 = new HashMap<>();
        resReq.put(1, 0);
        Activity a32 = new Activity(32, 0, resReq32);

        Map<Integer, Integer> resReq33 = new HashMap<>();
        resReq.put(1, 0);
        Activity a33 = new Activity(33, 0, resReq33);

        Map<Integer, Integer> resReq34 = new HashMap<>();
        resReq.put(1, 0);
        Activity a34 = new Activity(34, 0, resReq34);

        Map<Integer, Integer> resReq35 = new HashMap<>();
        resReq.put(1, 0);
        Activity a35 = new Activity(35, 0, resReq35);

        Map<Integer, Integer> resReq36 = new HashMap<>();
        resReq.put(1, 0);
        Activity a36 = new Activity(36, 0, resReq36);

        Map<Integer, Integer> resReq37 = new HashMap<>();
        resReq.put(1, 0);
        Activity a37 = new Activity(37, 0, resReq37);

        Map<Integer, Integer> resReq38 = new HashMap<>();
        resReq.put(1, 0);
        Activity a38 = new Activity(38, 0, resReq38);

        Map<Integer, Integer> resReq39 = new HashMap<>();
        resReq.put(1, 0);
        Activity a39 = new Activity(39, 0, resReq39);

        Map<Integer, Integer> resReq40 = new HashMap<>();
        resReq.put(1, 0);
        Activity a40 = new Activity(40, 0, resReq40);

        Map<Integer, Integer> resReq41 = new HashMap<>();
        resReq.put(1, 0);
        Activity a41 = new Activity(41, 0, resReq41);

        Map<Integer, Integer> resReq42 = new HashMap<>();
        resReq.put(1, 0);
        Activity a42 = new Activity(42, 0, resReq42);

        Map<Integer, Integer> resReq43 = new HashMap<>();
        resReq.put(1, 0);
        Activity a43 = new Activity(43, 0, resReq43);

        Map<Integer, Integer> resReq44 = new HashMap<>();
        resReq.put(1, 0);
        Activity a44 = new Activity(44, 0, resReq44);

        Map<Integer, Integer> resReq45 = new HashMap<>();
        resReq.put(1, 0);
        Activity a45 = new Activity(45, 0, resReq45);

        Map<Integer, Integer> resReq46 = new HashMap<>();
        resReq.put(1, 0);
        Activity a46 = new Activity(46, 0, resReq46);

        Map<Integer, Integer> resReq47 = new HashMap<>();
        resReq.put(1, 0);
        Activity a47 = new Activity(47, 0, resReq47);

        Map<Integer, Integer> resReq48 = new HashMap<>();
        resReq.put(1, 0);
        Activity a48 = new Activity(48, 0, resReq48);

        Map<Integer, Integer> resReq49 = new HashMap<>();
        resReq.put(1, 0);
        Activity a49 = new Activity(49, 0, resReq49);

        Map<Integer, Integer> resReq50 = new HashMap<>();
        resReq.put(1, 0);
        Activity a50 = new Activity(50, 0, resReq50);

        Map<Integer, Integer> resReq51 = new HashMap<>();
        resReq.put(1, 0);
        Activity a51 = new Activity(51, 0, resReq51);

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
        activities.add(a9);
        activities.add(a10);
        activities.add(a11);
        activities.add(a12);
        activities.add(a13);
        activities.add(a14);
        activities.add(a15);
        activities.add(a16);
        activities.add(a17);
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
        a0.getSuccessors().add(a3);
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

        /**
         * STAGE 3 & 4
         */

        a15.getSuccessors().add(a18);
        a15.getSuccessors().add(a31);

        a18.getSuccessors().add(a20);
        a18.getSuccessors().add(a19);
        a18.getSuccessors().add(a21);
        a18.getSuccessors().add(a22);
        a18.getSuccessors().add(a23);
        a31.getSuccessors().add(a32);
        a31.getSuccessors().add(a34);
        a31.getSuccessors().add(a35);
        a31.getSuccessors().add(a36);

        a20.getSuccessors().add(a26);
        a19.getSuccessors().add(a28);
        a21.getSuccessors().add(a27);
        a22.getSuccessors().add(a24);
        a23.getSuccessors().add(a24);
        a32.getSuccessors().add(a33);
        a34.getSuccessors().add(a33);
        a35.getSuccessors().add(a33);
        a36.getSuccessors().add(a33);

        a26.getSuccessors().add(a37);
        a28.getSuccessors().add(a29);
        a27.getSuccessors().add(a29);
        a24.getSuccessors().add(a25);
        a33.getSuccessors().add(a38);

        a29.getSuccessors().add(a37);
        a25.getSuccessors().add(a37);

        a37.getSuccessors().add(a39);
        a38.getSuccessors().add(a39);

        a39.getSuccessors().add(a45);
        a39.getSuccessors().add(a41);

        a45.getSuccessors().add(a44);
        a45.getSuccessors().add(a46);
        a45.getSuccessors().add(a47);
        a41.getSuccessors().add(a40);
        a41.getSuccessors().add(a42);
        a41.getSuccessors().add(a43);

        a44.getSuccessors().add(a48);
        a46.getSuccessors().add(a48);
        a47.getSuccessors().add(a48);
        a40.getSuccessors().add(a49);
        a42.getSuccessors().add(a49);
        a43.getSuccessors().add(a49);

        a49.getSuccessors().add(a48);

        a48.getSuccessors().add(a50);
        a48.getSuccessors().add(a51);

        a50.getSuccessors().add(a52);
        a51.getSuccessors().add(a52);

        return activities;
    }
}

