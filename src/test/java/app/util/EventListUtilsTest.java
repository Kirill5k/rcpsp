package app.util;

import app.factory.BenchmarkFactory;
import app.factory.ProjectFactory;
import app.project.BenchmarkInstance;
import app.project.EventList;
import org.junit.Test;

import static app.factory.ProjectFactory.asRandomEventList;
import static org.junit.Assert.*;

/**
 * Created by kirillb on 18/04/2017.
 */
public class EventListUtilsTest {

    private BenchmarkInstance testBI = BenchmarkFactory.TEST_BI;

    @Test
    public void testEventMove() throws Exception {
        EventList result = EventListUtils.eventMove(asRandomEventList(testBI));
        assertNotNull(result);
    }

    @Test
    public void testEventCrossover() throws Exception {
        EventList result = EventListUtils.eventCrossover(asRandomEventList(testBI), asRandomEventList(testBI));
        assertNotNull(result);
    }

}