package app.main;

import app.algorithm.AlgorithmType;
import app.project.EventList;
import app.utility.*;
import app.algorithm.impl.Algorithms;
import app.project.impl.BenchmarkInstance;

import java.util.List;
import java.util.stream.Collectors;

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

//        List<EventList> population = Algorithms.GA(AlgorithmType.NORMAL_SCGA, CaseStudyProject.asSimpleEventList(), 150, 5000, 0.3);
//        System.out.println(CommonOperations.getBestSolution(population));
//        System.out.println();
//        CommonOperations.getBestSolutions(population).forEach(System.out::println);

        System.out.println("SIMPLE EVENT LIST");
        List<EventList> pop = Algorithms.GA(AlgorithmType.NORMAL_SCGA, CaseStudyProject.asSimpleEventList(), 100, 1000, 0.3).stream().distinct().collect(Collectors.toList());
        CommonOperations.getBestSolutions(pop).forEach(System.out::println);
        System.out.println();

//        System.out.println("CASE STUDY EVENT LIST");
//        pop = Algorithms.GA(AlgorithmType.NORMAL_SCGA, CaseStudyProject.asCaseStudyEventList(), 100, 1000, 0.3).stream().distinct().collect(Collectors.toList());
//        CommonOperations.getBestSolutions(pop).forEach(System.out::println);
//        System.out.println();
//
//        pop = Algorithms.GA(AlgorithmType.NORMAL_SCGA, CaseStudyProject.asCaseStudyEventList(), 150, 1000, 0.3).stream().distinct().collect(Collectors.toList());
//        CommonOperations.getBestSolutions(pop).forEach(System.out::println);
//        System.out.println();
//
//        pop = Algorithms.GA(AlgorithmType.NORMAL_SCGA, CaseStudyProject.asCaseStudyEventList(), 250, 1000, 0.3).stream().distinct().collect(Collectors.toList());
//        CommonOperations.getBestSolutions(pop).forEach(System.out::println);
//        System.out.println();
//
//        pop = Algorithms.GA(AlgorithmType.NORMAL_SCGA, CaseStudyProject.asCaseStudyEventList(), 100, 5000, 0.3).stream().distinct().collect(Collectors.toList());
//        CommonOperations.getBestSolutions(pop).forEach(System.out::println);
//        System.out.println();
//
//        pop = Algorithms.GA(AlgorithmType.NORMAL_SCGA, CaseStudyProject.asCaseStudyEventList(), 150, 5000, 0.3).stream().distinct().collect(Collectors.toList());
//        CommonOperations.getBestSolutions(pop).forEach(System.out::println);
//        System.out.println();
//
//        pop = Algorithms.GA(AlgorithmType.NORMAL_SCGA, CaseStudyProject.asCaseStudyEventList(), 250, 5000, 0.3).stream().distinct().collect(Collectors.toList());
//        CommonOperations.getBestSolutions(pop).forEach(System.out::println);
//        System.out.println();

    }

}
