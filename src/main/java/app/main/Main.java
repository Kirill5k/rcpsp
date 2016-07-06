package app.main;

import app.utility.*;
import app.algorithm.Algorithms;
import app.project.impl.BenchmarkInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kirill on 16/02/2016.
 */
public class Main {
    private static final BenchmarkInstance BI_30 = Benchmarks.instances30.get("J3039_3.RCP");
    private static final BenchmarkInstance BI_60 = Benchmarks.instances60.get("J6039_4.RCP");
    private static final BenchmarkInstance BI_120 = Benchmarks.instances120.get("J12019_4.RCP");

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
//        Tests.testGA(AlgorithmType.NORMAL_SCGA, CaseStudyProject.asBenchmarkInstance());
        Tests.fullTestGA(Algorithms.NORMAL_SCGA, Benchmarks.instances30.values());


    }

}
