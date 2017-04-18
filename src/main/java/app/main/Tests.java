package app.main;

import app.algorithm.Algorithms;
import app.project.ActivityList;
import app.project.BenchmarkInstance;
import app.project.EventList;
import app.util.CommonUtils;
import app.project.Activity;
import app.factory.BenchmarkFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by Kirill on 20/06/2016.
 */
public class Tests {
    private static final Logger LOG = LoggerFactory.getLogger(Tests.class);

    public static void testAlgorithm(Algorithms type, BenchmarkInstance instance){
        ActivityList result = CommonUtils.getBestSolution(type.run(instance));
//        int optima = BenchmarkFactory.solutions().get(instance.getName());
//        double devOpt = CommonUtils.getDeviationFromOptima(result.makespan(), optima);
        double devCP = CommonUtils.getDeviationFromCriticalPath(result);
//        LOG.info("Complete. Received solution {}. Deviation from optima {}, deviation from CP {}", result.makespan(), devOpt, devCP);
        LOG.info("Complete. Received solution {}. Deviation from CP {}", result.makespan(), devCP);
        System.out.println(devCP);
    }

    public static void fullTestAlgorithm(Algorithms type, Collection<BenchmarkInstance> instances) {
        LOG.info("Initiating full testing of {}", type);
        int solved = 0;
        int count = 0;
        double devOptSum = 0;
        double devCPSum = 0;

        for (BenchmarkInstance bi : instances) {
            LOG.info("Test {}", ++count);

            ActivityList result = CommonUtils.getBestSolution(type.run(bi));

            int optima = BenchmarkFactory.solutions().get(bi.getName());
            double devOpt = CommonUtils.getDeviationFromOptima(result.makespan(), optima);
            double devCP = CommonUtils.getDeviationFromCriticalPath(result);

            devOptSum += devOpt;
            devCPSum += devCP;
            if (result.makespan() == optima) solved++;

            LOG.info("Test {} complete. Received solution {}. Deviation from optima {}, deviation from CP {}", count, result.makespan(), devOpt, devCP);
        }

        LOG.info("Testing complete. Solved {} out of {} benchmark instances", solved, instances.size());
        LOG.info("Average deviation from optima {}", devOptSum/instances.size());
        LOG.info("Average deviation from critical path {}", devCPSum/instances.size());
    }
}
