package app.algorithm;

import app.exception.UnsupportedAlgorithmType;
import app.factory.BenchmarkFactory;
import app.project.ActivityList;
import app.project.BenchmarkInstance;
import app.project.EventList;
import app.factory.ProjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static app.util.CommonUtils.getCriticalPathLength;

/**
 * Created by Kirill on 23/02/2016.
 */
public enum Algorithms {
    SCCS("Species Conserving Cuckoo Search"),
    ICS("Improved Cuckoo Search"),
    CS("Cuckoo Search"),
    FPA("Flower Pollination Algorithm"),
    GA("Genetic Algorithm");

    Algorithms(String name) {
        this.name = name;
    }

    private final String name;
    private static final Logger LOG = LoggerFactory.getLogger(Algorithms.class);

    public static int POPULATION_SIZE = 150;
    public static int STOP_CRITERION = 1000;
    public static int MAX_AMOUNT_OF_STEPS = 4;

    public static double ABANDONMENT_RATE = 0.25;
    public static double PORTION_OF_SMART_CUCKOOS = 0;
    public static double SWITCHING_PROBABILITY = 0.5;
    public static double MUTATION_RATE = 0.1;

    public static double SPECIES_DISTANCE_DIVIDER = 2.5;

    public List<ActivityList> run(BenchmarkInstance bi) {
        LOG.info("Running benchmark instance {} on {}. Best known solution {}. Critical path length {}", bi.getName(), this, BenchmarkFactory.solutions().get(bi.getName()), getCriticalPathLength(bi));
        return run(ProjectFactory.asRandomEventList(bi));
    }

    public List<ActivityList> run(EventList el) {
        Algorithm alg;
        switch (this){
            case SCCS: alg = new SpeciesConservingCuckooSearch(el, POPULATION_SIZE, STOP_CRITERION, ABANDONMENT_RATE, PORTION_OF_SMART_CUCKOOS, MAX_AMOUNT_OF_STEPS); break;
            case ICS: alg = new ImprovedCuckooSearch(el, POPULATION_SIZE, STOP_CRITERION, ABANDONMENT_RATE, PORTION_OF_SMART_CUCKOOS, MAX_AMOUNT_OF_STEPS); break;
            case CS: alg = new CuckooSearch(el, POPULATION_SIZE, STOP_CRITERION, ABANDONMENT_RATE, MAX_AMOUNT_OF_STEPS); break;
            case GA: alg = new GeneticAlgorithm(el, POPULATION_SIZE, STOP_CRITERION, MUTATION_RATE); break;
            case FPA: alg = new FlowerPollinationAlgorithm(el, POPULATION_SIZE, STOP_CRITERION, SWITCHING_PROBABILITY, MAX_AMOUNT_OF_STEPS); break;
            default: throw new UnsupportedAlgorithmType(this + " is not supported");
        }

        return solve(alg);
    }

    private List<ActivityList> solve(Algorithm alg) {
        final long start = System.currentTimeMillis();
        List<ActivityList> finalPopulation = alg.findSolution();
        final long end = System.currentTimeMillis();
        LOG.info("Operation complete. Took {}s", (end-start)/1000.);
        return finalPopulation;
    }

    public static void setParameter(String name, String value) {
        switch (name){
            case "m": case "s": case "stop": setParameter(name, Integer.parseInt(value)); break;
            case "pa": case "pm": case "ps": case "pc": setParameter(name, Double.parseDouble(value)); break;
        }
    }

    public static void setParameter(String name, int parameter){
        switch (name){
            case "m": POPULATION_SIZE = parameter; break;
            case "stop": STOP_CRITERION = parameter; break;
            case "s": MAX_AMOUNT_OF_STEPS = parameter; break;
        }
    }

    public static void setParameter(String name, double parameter){
        switch (name){
            case "ps": SWITCHING_PROBABILITY = parameter; break;
            case "pa": ABANDONMENT_RATE = parameter; break;
            case "pm": MUTATION_RATE = parameter; break;
            case "pc": PORTION_OF_SMART_CUCKOOS = parameter; break;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
