package app.gui;

import app.project.ActivityList;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 16/11/2015.
 */
public class SchedulePlot<T extends ActivityList> extends JFrame {
    private T project;

    public SchedulePlot(T project) {
        this.project = project;
        initUI();
    }

    private void initUI() {
        add(new PlotSurface<>(project));

        setTitle("Schedule");
        setSize(1900, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}


