package app.asset;

import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 16/02/2016.
 */
public class ActivityList extends AbstractProject implements Comparable<ActivityList> {

    //final private SortedMap<Activity, Integer> schedule;

    public ActivityList(Map<Integer, Integer> resources, List<Activity> activities) {
        super(resources, activities);
        //schedule = Schedules_Old.createSerialSchedule(this);
       // makespan = schedule.get(schedule.lastKey());
    }

//    public Map<Activity, Integer> getSchedule() {
//        return schedule;
//    }

    public Activity getRandomActivity() {
        int index = (int)(Math.random()*size()-2)+1;
        if (index <= 0) index = 1;
        if (index >= size() - 1) index = 30;
        return activities.get(index);
    }

    public Activity getActivity(int index){
        return activities.get(index);
    }

    @Override
    public String toString() {
        String s = "[";

        for (Activity a : activities)
            s += a.getNumber() + " ";

        return s.trim() + "]";
    }

    @Override
    public int compareTo(ActivityList o) {
        return Integer.compare(this.makespan, o.makespan);
    }
}
