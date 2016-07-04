package app.main;

import app.algorithm.AlgorithmType;
import app.algorithm.CommonOperations;
import app.algorithm.implementations.Algorithms;
import app.asset.BenchmarkInstance;
import app.asset.CaseStudyEventList;
import app.asset.EventList;
import app.asset.SimpleEventList;
import app.utility.CaseStudyProject;
import app.utility.Benchmarks;

import java.util.List;

/**
 * Created by Kirill on 16/02/2016.
 */
public class Main {
    private static final BenchmarkInstance BI_30 = Benchmarks.instances30.get("J3039_3.RCP");
    private static final BenchmarkInstance BI_60 = Benchmarks.instances60.get("J6039_4.RCP");
    private static final BenchmarkInstance BI_120 = Benchmarks.instances120.get("J12019_4.RCP");

    public static void main(String[] args) {
//        Tests.testGA(AlgorithmType.NORMAL_SCGA, CaseStudyProject.asBenchmarkInstance());
//        Tests.fullTestGA(AlgorithmType.NORMAL_SCGA, Benchmarks.instances30.entrySet());

        List<EventList> population = Algorithms.GA(AlgorithmType.NORMAL_GA, BI_30, 150, 1000, 0.3);
//        List<EventList> population = Algorithms.GA(AlgorithmType.NORMAL_SCGA, CaseStudyProject.asCaseStudyEventList(), 150, 1000, 0.3);
        System.out.println(CommonOperations.getBestSolution(population));
        System.out.println();
        CommonOperations.getBestSolutions(population).forEach(System.out::println);
    }

}
