package app.algorithm;


import app.project.ActivityList;
import app.project.EventList;

import java.util.List;

/**
 * Created by Kirill on 18/04/2016.
 */
interface Algorithm<T extends ActivityList> {
    List<T> findSolution();
}
