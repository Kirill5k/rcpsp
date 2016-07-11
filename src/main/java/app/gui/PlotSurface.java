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
    private static final int FONT_SIZE = 7;
    private static final int Y_COEFFICIENT = 10;
    private static final int X_COEFFICIENT = 2;

    private T project;
    private Map<Integer, Map<Integer, Integer>> vertical = new HashMap<>();
    private Map<Integer, Integer> yBorders = new HashMap<>();

    public PlotSurface(T project) {
        this.project = project;
        project.getResCapacities().keySet().forEach(k -> vertical.put(k, new HashMap<>()));
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

        IntStream.range(0, project.getResCapacities().size()).boxed().forEach(i -> yBorders.put(i+1, 1 + yBorders.getOrDefault(i, 0) + project.getResCapacities().get(i+1)));
        project.getSequence().stream().filter(a -> !a.equals(project.getDummyEndActivity()) && !a.equals(project.getDummyStartActivity()))
                .forEach(a -> plotActivity(a, graph));

        drawAxis(graph);
    }

    private void drawAxis(Graphics2D g) {
        g.setColor(Color.black);
        project.getResCapacities().forEach((resNum, resCap) -> drawLine(g, 0, project.getMakespan(), yBorders.get(resNum), yBorders.get(resNum)));
        project.getResCapacities().forEach((resNum, resCap) -> drawLine(g, 0, 0, yBorders.get(resNum), yBorders.get(resNum)-resCap));

        project.getResCapacities().forEach((resNum, resCap) -> drawLine(g, 0, project.getMakespan(), yBorders.get(resNum)-resCap, yBorders.get(resNum)-resCap));
        project.getResCapacities().forEach((resNum, resCap) -> drawLine(g, project.getMakespan(), project.getMakespan(), yBorders.get(resNum), yBorders.get(resNum)-resCap));


        project.getResCapacities().forEach((resNum, resCap) ->
            IntStream.range(0, project.getMakespan()).boxed().filter(i -> i % 100 == 0 || i == project.getMakespan()-1).forEach(i -> drawString(g, String.valueOf(i/5), i, yBorders.get(resNum)+1)));

        project.getResCapacities().forEach((resNum, resCap) -> drawString(g, String.valueOf(resCap), 0, yBorders.get(resNum)-resCap+1));
    }

    private void plotActivity(Activity a, Graphics2D graph){
        int x_start = project.getStartingTimes().get(a);
        int x_end = project.getFinishTimes().get(a);
        IntStream.range(x_start, x_end).boxed().forEach(x -> plotActivityAtX(a, graph, x));
    }

    private void plotActivityAtX(Activity a, Graphics2D graph, int x){
        a.getResReq().entrySet().forEach(e -> plotActivityAtXY(a, graph, x, e.getKey(), e.getValue()));
    }

    private void plotActivityAtXY(Activity a, Graphics2D graph, int x, int resNum, int resCap) {
//        int s = yBorders.getOrDefault(resNum, 0);
//        s = 0;
        int yStart = vertical.get(resNum).getOrDefault(x, 0);
        int yEnd = yStart + resCap;

        int y = yBorders.getOrDefault(resNum, 0) - resCap - yStart;

        vertical.get(resNum).put(x, yEnd);

        drawRect(graph, x, y, resCap, a.getNumber());
//        drawString(graph, String.valueOf(a.getNumber()), x, y+1);
    }


    private void drawRect(Graphics2D g, int x, int yStart, int height, int coefficient){
        g.setColor(new Color(150-2*coefficient, 180-2*coefficient, 250-3*coefficient));
        g.fillRect(x * X_COEFFICIENT, yStart * Y_COEFFICIENT, X_COEFFICIENT, height * Y_COEFFICIENT);
    }

    private void drawString(Graphics2D g, String s, int x, int y) {
        g.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        g.setColor(Color.BLACK);
        g.drawString(s, x * X_COEFFICIENT + 2, y * Y_COEFFICIENT - 4);
    }

    private void drawLine(Graphics2D g, int x1, int x2, int y1, int y2) {
        g.setColor(Color.BLACK);
        g.drawLine(x1 * X_COEFFICIENT, y1 * Y_COEFFICIENT, x2 * X_COEFFICIENT, y2 * Y_COEFFICIENT);
    }

}
