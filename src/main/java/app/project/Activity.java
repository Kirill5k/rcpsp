package app.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        return getNumber() == activity.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber());
    }

    @Override
    public int compareTo(Activity o) {
        return Integer.compare(this.number, o.getNumber());
    }
}