package app.algorithm;

import app.project.EventList;
import app.project.impl.BenchmarkInstance;
import app.exception.IncorrectAlgorithmTypeException;
import app.utility.Benchmarks;
import app.utility.CommonOperations;
import app.utility.Projects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Kirill on 23/02/2016.
 */
public enum Algorithms {
    NORMAL_GA("Genetic Algorithm"),
    NORMAL_SCGA("Species Conserving Genetic Algorithm"),
    PARALLEL_GA("Parallel Genetic Algorithm"),
    NORMAL_CS("Cuckoo Search");

    Algorithms(String name) {
        this.name = name;
    }

    private final String name;
    private static final Logger LOG = LoggerFactory.getLogger(Algorithms.class);

    public static final int POPULATION_SIZE = 100;
    public static final double MUTATION_RATE = 0.3;
    public static final int STOP_CRITERION = 1000;

    public static List<EventList> run(Algorithms type, BenchmarkInstance bi) {
        LOG.info("Running benchmark instance {} on {}. Best known solution {}. Critical path length {}", bi.getName(), type.toString(), Benchmarks.solutions.get(bi.getName()), CommonOperations.getCriticalPathLength(bi));
        return run(type, Projects.asRandomEventList(bi));
    }

    public static List<EventList> run(Algorithms type, EventList el) {
        Algorithm alg;
        switch (type){
            case NORMAL_GA: alg = new GeneticAlgorithm(el, POPULATION_SIZE, STOP_CRITERION, MUTATION_RATE); break;
            case NORMAL_SCGA: alg = new SpeciesConservingGeneticAlgorithm(el, POPULATION_SIZE, STOP_CRITERION, MUTATION_RATE); break;
            case PARALLEL_GA: alg = new ParallelGeneticAlgorithm(el, POPULATION_SIZE, STOP_CRITERION, MUTATION_RATE); break;
            default: throw new IncorrectAlgorithmTypeException(type + " is not supported");
        }

        return solve(alg);
    }

    private static List<EventList> solve(Algorithm alg) {
        final long start = System.currentTimeMillis();
        List<EventList> finalPopulation = alg.findSolution();
        final long end = System.currentTimeMillis();
        LOG.info("Operation complete. Took {} ms", end-start);
        return finalPopulation;
    }

    /*
    public static EventList cuckooSearch(BenchmarkInstance benchmark, int populationSize, int stopCriterion, double pa, double pc) {
        final long start = System.nanoTime();
        CuckooSearch cs = new CuckooSearch(benchmark, populationSize, stopCriterion, pa, pc);
        //scga.printBestSpecies();

        final long end = System.nanoTime();
        System.out.println("Took: " + ((end - start) / 1000000) + "ms");
        return cs.getBestSolution();
    }
    */

    @Override
    public String toString() {
        return name;
    }
}
