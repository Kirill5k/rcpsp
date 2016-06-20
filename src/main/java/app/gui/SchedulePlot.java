package app.gui;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 16/11/2015.
 */
public class SchedulePlot extends JFrame {
    private List<Map<Integer, List<Integer>>> canvases;
    private int shapeSize;

    public SchedulePlot(List<Map<Integer, List<Integer>>> canvases, int shapeSize) {
        this.shapeSize = shapeSize;
        this.canvases = canvases;
        initUI();
    }

    private void initUI() {
        add(new PlotSurface(canvases, shapeSize));

        setTitle("Schedule");
        setSize(1900, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}


