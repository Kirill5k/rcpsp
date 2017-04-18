package app.algorithm;

import app.factory.BenchmarkFactory;
import app.project.BenchmarkInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by kirillb on 18/04/2017.
 */
@RunWith(Parameterized.class)
public class AlgorithmsTest {

    @Parameterized.Parameters
    public static Collection<String> parameters() {
        return Arrays.asList("--pa=0.5", "--pc=0.5", "--ps=0.5", "--s=1", "--m=100");
    }

    @Parameterized.Parameter
    public String parameter;

    private BenchmarkInstance testBI = BenchmarkFactory.TEST_BI;

    @Test
    public void testCuckooSearch() throws Exception {
        assertFalse(Algorithms.CS.run(testBI).isEmpty());
    }

    @Test
    public void testImprovedCuckooSearch() throws Exception {
        assertFalse(Algorithms.ICS.run(testBI).isEmpty());
    }

    @Test
    public void testSpeciesConservingCuckooSearch() throws Exception {
        assertFalse(Algorithms.SCCS.run(testBI).isEmpty());
    }

    @Test
    public void testFlowerPollinationAlgorithm() throws Exception {
        assertFalse(Algorithms.FPA.run(testBI).isEmpty());
    }

    @Test
    public void testGeneticAlgorithm() throws Exception {
        assertFalse(Algorithms.GA.run(testBI).isEmpty());
    }

    @Test
    public void testSetParameter() throws Exception {
        Algorithms.setParameter(parameter.split("=")[0].substring(2), parameter.split("=")[1]);
    }
}