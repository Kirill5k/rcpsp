package app.algorithm;

import app.asset.EventList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kirill on 20/05/2016.
 */
public class NewSpeciesConservation {
    private NewSpeciesConservation() {
    }

    public static List<EventList> conserveSpeciesSeeds(Set<Set<EventList>> species) {
        List<EventList> seeds = new ArrayList<>();

        for (Set<EventList> s : species) {
            List<EventList> l = new ArrayList<>(s);
            Collections.sort(l);
            seeds.add(l.get(0));
        }

        return seeds;
    }

//    public static Set<Set<EventList>> establishSpecies(List<EventList> population, int listSize){
//        return establishSpecies(population, listSize, false);
//    }

//    public static Set<Set<EventList>> establishSpecies(List<EventList> population, int listSize, boolean test) {
//        List<EventList> refPop = new ArrayList<>(population);
//        Set<Set<EventList>> matingLists = population.stream().map(ind -> createMatingList(ind, refPop, listSize)).collect(Collectors.toSet());
//
//        Set<Set<EventList>> species = combineMatingListsIntoSpecies(matingLists, listSize);
//
//
//        if (test) {
//            System.out.println("All mating lists");
//            for (Set<EventList> matingList : matingLists) {
//                for (EventList el : matingList)
//                    System.out.print(el.getId() + " ");
//
//                System.out.println();
//            }
//
//            int counter = 0;
//            for (Set<EventList> s : species) {
//                counter++;
//                System.out.print("Species " + counter + ": ");
//                for (EventList el : s) {
//                    System.out.print(el.getId() + " ");
//                }
//                System.out.println();
//            }
//        }
//
//
//        return species;
//    }

//    private static Set<Set<EventList>> combineMatingListsIntoSpecies(Set<Set<EventList>> matingLists, int speciesSize) {
//        Map<Long, Integer> popularity = new HashMap<>();
//        for (Set<EventList> ml : matingLists) {
//            for (EventList el : ml) {
//                if (popularity.get(el.getId()) == null)
//                    popularity.put(el.getId(), 0);
//                popularity.put(el.getId(), popularity.get(el.getId())+1);
//            }
//        }
//        List<Set<EventList>> mls = sortMatingLists(matingLists, popularity);
//        Set<Set<EventList>> species = new HashSet<>();
//
//        for (Set<EventList> ml : mls) {
//            if (species.isEmpty()) {
//                species.add(new HashSet<>(ml));
//                continue;
//            }
//
//            boolean found = false;
//            for (Set<EventList> s : species)
//                if (countIdenticalSpecies(s, ml) >= 2) {
//                //if (countIdenticalSpecies(s, ml) >= speciesSize) {
//                    s.addAll(ml);
//                    found = true;
//                    break;
//                }
//
//
//            if (!found)
//                species.add(new HashSet<>(ml));
//        }
//
//
//        return species;
//    }

//    private static List<Set<EventList>> sortMatingLists(Set<Set<EventList>> mls, Map<Long, Integer> popularity) {
//        List<Set<EventList>> newMls = new ArrayList<>(mls);
//        Collections.sort(newMls, new Comparator<Set<EventList>>() {
//            @Override
//            public int compare(Set<EventList> o1, Set<EventList> o2) {
//                return Integer.compare(getRank(o2, popularity), getRank(o1, popularity));
//            }
//        });
//
//        return newMls;
//    }

//    private static int getRank(Set<EventList> ml, Map<Long, Integer> popularity) {
//        int rank = 0;
//        for (EventList el : ml) {
//            rank += popularity.get(el.getId());
//        }
//
//        return rank;
//    }

//    private static int countIdenticalSpecies(Set<EventList> l1, Set<EventList> l2) {
//        int count = 0;
//        for (EventList el1 : l1) {
//            for (EventList el2 : l2) {
//                if (el1.getId() == el2.getId())
//                    count++;
//            }
//        }
//
//        return count;
//    }

//    private static Set<EventList> createMatingList(EventList individual, List<EventList> population, int listSize) {
//        Set<EventList> matingList = new HashSet<>();
//        matingList.add(individual);
//        sortPopulationByDistanceToIndividual(individual, population);
//
//        for (EventList neighbour : population) {
//            if (CommonOperations.calculateDistance(individual, neighbour) > 0)
//                matingList.add(neighbour);
//
//            if (matingList.size() == listSize + 1)
//                break;
//        }
//
//        return matingList;
//    }
//
//    private static List<EventList> sortPopulationByDistanceToIndividual(EventList individual, List<EventList> population) {
//        Comparator<EventList> sorter = (o1, o2) -> Integer.compare(CommonOperations.calculateDistance(individual, o1), CommonOperations.calculateDistance(individual, o2));
//        Collections.sort(population, sorter);
//        return population;
//    }
}
