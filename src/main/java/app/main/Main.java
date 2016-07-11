package app.main;

import app.algorithm.Algorithms;
import app.factory.BenchmarkFactory;
import app.factory.CaseStudyFactory;
import app.factory.ProjectFactory;
import app.gui.SchedulePlot;
import app.project.ActivityList;
import app.project.EventList;
import app.project.impl.BenchmarkInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Kirill on 16/02/2016.
 */
public class Main {
    private static final BenchmarkInstance BI_30 = BenchmarkFactory.instances30.get("J3039_3.RCP");
    private static final BenchmarkInstance BI_60 = BenchmarkFactory.instances60.get("J6039_4.RCP");
    private static final BenchmarkInstance BI_120 = BenchmarkFactory.instances120.get("J12019_4.RCP");

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
//        Tests.fullTestAlgorithm(Algorithms.NORMAL_SCGA, BenchmarkFactory.instances30.values());

//        List<EventList> pop = CommonOperations.initialisePopulation(CaseStudyFactory.asSimpleEventList(), 1000);
//        System.out.println("Min ms " + (pop.stream().mapToInt(EventList::getMakespan).min().getAsInt()/5));
//        System.out.println("Average ms " + (pop.stream().mapToInt(EventList::getMakespan).average().getAsDouble()/5));
//        System.out.println("Max ms " + (pop.stream().mapToInt(EventList::getMakespan).max().getAsInt()/5));
//        System.out.println("Average distance " + (SpeciesConservation.getAverageSpeciesDistance(pop)/5));

//        List<EventList> pop = CommonOperations.initialisePopulation(CaseStudyFactory.asCaseStudyEventList(), 10000);
//        System.out.println("Min ms " + (pop.stream().mapToInt(EventList::getMakespan).min().getAsInt()/5));
//        System.out.println("Average ms " + (pop.stream().mapToInt(EventList::getMakespan).average().getAsDouble()/5));
//        System.out.println("Max ms " + (pop.stream().mapToInt(EventList::getMakespan).max().getAsInt()/5));
//        System.out.println("Average distance " + (SpeciesConservation.getAverageSpeciesDistance(pop)/5));
//

//        List<EventList> finalPop = Algorithms.NORMAL_SCGA.run(CaseStudyFactory.asCaseStudyEventList());
//        finalPop.stream().forEach(System.out::println);

//        CaseStudyEventList el = (CaseStudyEventList)CaseStudyFactory.asCaseStudyEventList();
//        el.compareMeanVsOptimisedDurations();

        EventList el = CaseStudyFactory.asSimpleEventList();
        plot(el);
    }

    private static <T extends ActivityList> void plot(T al){
        new SchedulePlot<>(al);
    }

}
