package app.algorithm;

import app.asset.Activity;
import app.asset.EventList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 18/04/2016.
 */
public class SpeciesConservation {
    private SpeciesConservation() {
    }

    public static int getAverageSpeciesDistance(List<EventList> population) {
        int distance = 0;
        int count = 0;

        for (EventList el1 : population)
            for (EventList el2 : population)
                if (el1.getId() != el2.getId()) {
                    count++;
                    distance += calculateDistance(el1, el2);
                }

        return distance/count;
    }

    public static int calculateDistance(EventList el1, EventList el2) {
        Map<Activity, Integer> stEl1 = el1.getStartingTimes();
        Map<Activity, Integer> stEl2 = el2.getStartingTimes();
        return stEl1.entrySet().stream().mapToInt(e -> Math.abs(e.getValue() - stEl2.get(e.getKey()))).sum();
    }

    public static List<EventList> getGlobalOptima(List<EventList> population, int speciesDistance) {
        List<EventList> globalOptima = new ArrayList<>();
        Collections.sort(population);
        int bestMakespan = population.get(0).getMakespan();

        for (EventList ind : population) {
            boolean ok = false;
            if (globalOptima.isEmpty())
                ok = true;

            if (ind.getMakespan() == bestMakespan && !globalOptima.isEmpty()) {
                ok = true;
                for (EventList go : globalOptima)
                    if (calculateDistance(go, ind) < speciesDistance) {
                        ok = false;
                        break;
                    }
            }

            if (ok)globalOptima.add(ind);
        }

        return globalOptima;
    }

    public static List<EventList> findSpeciesSeeds(List<EventList> individuals, int speciesDistance) {
        Collections.sort(individuals);
        List<EventList> speciesSeeds = new ArrayList<>();

        for (EventList ind : individuals) {
            if (speciesSeeds.isEmpty()) {
                speciesSeeds.add(ind);
                continue;
            }

            boolean mark = false;
            for (EventList ss : speciesSeeds)
                if (calculateDistance(ind, ss) <= speciesDistance/2)
                    mark = true;

            if (!mark)speciesSeeds.add(ind);
        }

        return speciesSeeds;
    }

    public static List<EventList> conserveSpecies(List<EventList> individuals, List<EventList> speciesSeeds, int speciesDistance) {
        List<List<EventList>> species = new ArrayList<>();
        for (EventList ss : speciesSeeds) {
            List<EventList> s = new ArrayList<>();
            s.add(ss);
            species.add(s);
        }


        for (EventList ind : individuals) {
            if (speciesSeeds.contains(ind))
                continue;

            boolean mark = false;
            for (List<EventList> s : species) {
                if (calculateDistance(ind, s.get(0)) <= speciesDistance/2) {
                    s.add(ind);
                    mark = true;
                }
            }

            if (!mark) {
                List<EventList> s = new ArrayList<>();
                s.add(ind);
                species.add(s);
            }
        }


        List<EventList> newSpeciesSeeds = new ArrayList<>();
        for (List<EventList> s : species) {
            Collections.sort(s);
            newSpeciesSeeds.add(s.get(0));
        }

        return newSpeciesSeeds;
    }
}
