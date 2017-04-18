package app.util;

import app.factory.BenchmarkFactory;
import app.factory.ProjectFactory;
import app.project.BenchmarkInstance;
import app.project.EventList;
import org.junit.Test;

import static app.factory.BenchmarkFactory.TEST_BI;
import static app.factory.ProjectFactory.asRandomEventList;
import static org.junit.Assert.*;

/**
 * Created by kirillb on 18/04/2017.
 */
public class EventListUtilsTest {

    @Test
    public void testEventMove() throws Exception {
        EventList result = EventListUtils.eventMove(asRandomEventList(TEST_BI));
        assertNotNull(result);
    }

    @Test
    public void testEventCrossover() throws Exception {
        EventList result = EventListUtils.eventCrossover(asRandomEventList(TEST_BI), asRandomEventList(TEST_BI));
        assertNotNull(result);
    }

}