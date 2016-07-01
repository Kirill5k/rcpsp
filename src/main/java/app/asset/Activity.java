package app.asset;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 16/02/2016.
 */
public class Activity implements Comparable<Activity>{

    private final int number;
    private final int duration;
    private final Map<Integer, Integer> resReq;
    private final List<Activity> predecessors = new ArrayList<>();
    private final List<Activity> successors = new ArrayList<>();

    public Activity(int number, int duration, Map<Integer, Integer> resReq) {
        this.number = number;
        this.duration = duration;
        this.resReq = resReq;
    }

    public int getNumber() {
        return number;
    }

    public List<Activity> getSuccessors() {
        return successors;
    }

    public int getDuration() {
        return duration;
    }

    public Map<Integer, Integer> getResReq() {
        return resReq;
    }

    public List<Activity> getPredecessors() {
        return predecessors;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;

        Activity activity = (Activity) o;

        if (getNumber() != activity.getNumber()) return false;
        if (getDuration() != activity.getDuration()) return false;
        if (getPredecessors() != null ? !getPredecessors().equals(activity.getPredecessors()) : activity.getPredecessors() != null)
            return false;
        if (getSuccessors() != null ? !getSuccessors().equals(activity.getSuccessors()) : activity.getSuccessors() != null)
            return false;
        return getResReq().equals(activity.getResReq());

    }

    @Override
    public int hashCode() {
        int result = getNumber();
        result = 31 * result + getDuration();
        result = 31 * result + getResReq().hashCode();
        return result;
    }

    @Override
    public int compareTo(Activity o) {
        return Integer.compare(this.number, o.getNumber());
    }
}