package app.main;

import app.algorithm.Algorithms;
import app.factory.BenchmarkFactory;
import app.factory.CaseStudyFactory;
import app.factory.ProjectFactory;
import app.gui.SchedulePlot;
import app.project.ActivityList;
import app.project.EventList;
import app.project.impl.BenchmarkInstance;
import app.project.impl.CaseStudyEventList;
import org.apache.commons.math3.special.Gamma;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Kirill on 16/02/2016.
 */
public class Main {
    private static final BenchmarkInstance BI_30 = BenchmarkFactory.instances30.get("J3039_3.RCP");
    private static final BenchmarkInstance BI_60 = BenchmarkFactory.instances60.get("J6039_4.RCP");
    private static final BenchmarkInstance BI_120 = BenchmarkFactory.instances120.get("J12019_4.RCP");

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        double beta = 3/2;
        // sigma=(gamma(1+beta)*sin(pi*beta/2)/(gamma((1+beta)/2)*beta*2^((beta-1)/2)))^(1/beta);
        double sigma = (Gamma.gamma(1+beta)*Math.sin(Math.PI*beta/2))/(Gamma.gamma((1+beta)/2)*beta*Math.pow(2, (beta-1)/2));
        sigma = Math.pow(sigma, 1/beta);

        System.out.println(sigma);
    }

    private static <T extends ActivityList> void plot(T al){
        new SchedulePlot<>(al);
    }

}
