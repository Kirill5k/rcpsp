package app.util;

import app.factory.BenchmarkFactory;
import app.factory.ProjectFactory;
import app.project.ActivityList;
import app.project.BenchmarkInstance;
import org.junit.Test;

import static app.factory.ProjectFactory.asRandomActivityList;
import static org.junit.Assert.*;

/**
 * Created by kirillb on 18/04/2017.
 */
public class ActivityListUtilsTest {

    private BenchmarkInstance testBI = BenchmarkFactory.TEST_BI;

    @Test
    public void testRandShiftMove() throws Exception {
        ActivityList result = ActivityListUtils.randShiftMove(asRandomActivityList(testBI));
        assertNotNull(result);
    }

    @Test
    public void testTwoPointCrossover() throws Exception {
        ActivityList result = ActivityListUtils.twoPointCrossover(asRandomActivityList(testBI), asRandomActivityList(testBI));
        assertNotNull(result);
    }

}