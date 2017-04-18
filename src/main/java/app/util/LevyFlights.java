package app.util;

import org.apache.commons.math3.special.Gamma;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Kirill on 26/08/2016.
 */
public class LevyFlights {
    private static final double BETA = 3./2.;
    private static final double SIGMA_PART_1 = Gamma.gamma(1+BETA)*Math.sin(Math.PI*BETA/2);
    private static final double SIGMA_PART_2 = Gamma.gamma((1+BETA)/2)*BETA*Math.pow(2, (BETA-1)/2);
    private static final double SIGMA = Math.pow(SIGMA_PART_1/SIGMA_PART_2, 1/BETA);

    private LevyFlights(){}

    public static double levyNumber() {
        double u = ThreadLocalRandom.current().nextDouble(SIGMA);
        double v = ThreadLocalRandom.current().nextDouble(1);
        return u / Math.pow(Math.abs(v), 1/BETA);
    }
}
