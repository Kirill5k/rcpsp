package app.main;

import app.algorithm.Algorithms;
import app.factory.BenchmarkFactory;
import app.factory.ProjectFactory;
import app.gui.SchedulePlot;
import app.project.Activity;
import app.project.ActivityList;
import app.project.BenchmarkInstance;
import app.util.ActivityListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Kirill on 16/02/2016.
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static final BenchmarkInstance BI_30 = BenchmarkFactory.get("J3039_3.RCP");
    private static final BenchmarkInstance BI_60 = BenchmarkFactory.get("J6039_4.RCP");
    private static final BenchmarkInstance BI_120 = BenchmarkFactory.get("J12019_4.RCP");

    public static void main(String[] args) {
        if (args.length > 0) setParameters(args);
//        Tests.fullTestAlgorithm(Algorithms.ICS, BenchmarkFactory.j30set().values());
//        Tests.testAlgorithm(Algorithms.CS, BI_30);
    }

    private static void setParameters(String[] args){
        LOG.info("Parameters: {}", Arrays.toString(args));
        Optional<BenchmarkInstance> bi = Optional.empty();
        for (String arg : args) {
            if (arg.startsWith("J")){
                bi = Optional.ofNullable(BenchmarkFactory.get(arg));
            }
            if (arg.startsWith("--")){
                Algorithms.setParameter(arg.split("=")[0].substring(2), arg.split("=")[1]);
            }
        }
        bi.ifPresent(b -> Tests.testAlgorithm(Algorithms.SCCS, b));
    }

    private static <T extends ActivityList> void plot(T al){
        new SchedulePlot<>(al);
    }

}
