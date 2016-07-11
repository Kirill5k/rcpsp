package app.gui;

import app.project.Activity;
import app.project.ActivityList;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * Created by Kirill on 16/11/2015.
 */
class PlotSurface<T extends ActivityList> extends JPanel {
    private static final int SHAPE_SIZE = 10;

    private T project;
    private Map<Integer, Map<Integer, Integer>> vertical = new HashMap<>();
    private Map<Integer, Integer> yLimits = new HashMap<>();

    public PlotSurface(T project) {
        this.project = project;
        project.getResCapacities().keySet().forEach(k -> vertical.put(k, new HashMap<>()));
        project.getResCapacities().entrySet().forEach(e -> yLimits.put(e.getKey(), e.getValue()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        Graphics2D graph = (Graphics2D)g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        graph.setRenderingHints(rh);
        graph.setFont(new Font("Arial", Font.PLAIN, SHAPE_SIZE-3));

        project.getSequence().stream().filter(a -> !a.equals(project.getDummyEndActivity()) || !a.equals(project.getDummyStartActivity()))
                .forEach(a -> plotActivity(a, graph));
    }

    private void plotActivity(Activity a, Graphics2D graph){
        int x_start = project.getStartingTimes().get(a);
        int x_end = project.getFinishTimes().get(a);
        IntStream.range(x_start, x_end).boxed().forEach(x -> plotActivityAtX(a, graph, x));
    }

    private void plotActivityAtX(Activity a, Graphics2D graph, int x){
        a.getResReq().entrySet().stream().filter(e -> e.getKey() == 1).forEach(e -> plotActivityAtXY(a, graph, x, e.getKey(), e.getValue()));
//        a.getResReq().entrySet().forEach(e -> plotActivityAtXY(a, graph, x, e.getKey(), e.getValue()));
    }

    private void plotActivityAtXY(Activity a, Graphics2D graph, int x, int resNum, int resCap) {
//        int s = 0;
//        for (int i = 0; i < resNum; i++) {
//            s += yLimits.getOrDefault(i, 0);
//        }

        int y = vertical.get(resNum).getOrDefault(x, 0);
        int yEnd = y + resCap;

        vertical.get(resNum).put(x, yEnd);

        drawRect(graph, x, y, resCap);
        drawString(graph, String.valueOf(a.getNumber()), x, yEnd);
    }


    private void drawRect(Graphics2D g, int x, int yStart, int height){
        g.setColor(Color.cyan);
        g.fillRect(x * SHAPE_SIZE, yStart * SHAPE_SIZE, SHAPE_SIZE, height * SHAPE_SIZE);
    }

    private void drawString(Graphics2D g, String s, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawString(s, x * SHAPE_SIZE, y * SHAPE_SIZE);
    }

}
