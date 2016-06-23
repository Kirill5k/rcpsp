package app.asset.casestudy;

import app.asset.Activity;
import app.asset.EventList;

import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 23/06/2016.
 */
public class SpecialEventList extends EventList {

//    private final Map<Integer, Double> resourceEfficiencies;
//    private final Map<Integer, Double> resourceLearningCapabilities;

    public SpecialEventList(List<Activity> activities, Map<Integer, Integer> resources) {
        super(activities, resources);
        initialise();
    }

    @Override
    protected void initialise() {

    }
}
