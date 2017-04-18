package app.util;

import app.factory.BenchmarkFactory;
import app.factory.ProjectFactory;
import app.project.ActivityList;
import app.project.BenchmarkInstance;
import org.junit.Test;

import static app.factory.BenchmarkFactory.TEST_BI;
import static app.factory.ProjectFactory.asRandomActivityList;
import static org.junit.Assert.*;

/**
 * Created by kirillb on 18/04/2017.
 */
public class ActivityListUtilsTest {

    @Test
    public void testRandShiftMove() throws Exception {
        ActivityList result = ActivityListUtils.randShiftMove(asRandomActivityList(TEST_BI));
        assertNotNull(result);
    }

    @Test
    public void testTwoPointCrossover() throws Exception {
        ActivityList result = ActivityListUtils.twoPointCrossover(asRandomActivityList(TEST_BI), asRandomActivityList(TEST_BI));
        assertNotNull(result);
    }

}