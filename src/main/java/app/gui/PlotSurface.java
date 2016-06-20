package app.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Kirill on 16/11/2015.
 */
class PlotSurface extends JPanel {
    private List<Map<Integer, List<Integer>>> canvases;
    private int shapeSize;

    public PlotSurface(List<Map<Integer, List<Integer>>> canvases, int shapeSize) {
        this.canvases = canvases;
        this.shapeSize = shapeSize;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        g2d.setFont(new Font("Arial", Font.PLAIN, shapeSize-3));

        int x, yMax=0;
        for (Map<Integer, List<Integer>> c : canvases) {
            Map<Integer, List<Integer>> canvas = new TreeMap<>(c);
            for (Map.Entry<Integer, List<Integer>> e : canvas.entrySet()) {
                x = (e.getKey()+1)*shapeSize;

                if (e.getKey()==-1) {
                    yMax += e.getValue().size() + 2;
                    for (int i = 0; i < e.getValue().size(); i++) {
                        g2d.setColor(new Color(0,0,0));
                        g2d.fillRect(x + shapeSize, (yMax - i) * shapeSize, 3, shapeSize);
                        g2d.drawString(Integer.toString(i), x, (yMax - i + 1) * shapeSize);
                    }
                } else {
                    for (int i = 0; i < e.getValue().size(); i++) {
                        int coefficient = e.getValue().get(i);
                        g2d.setColor(new Color(150-1*coefficient, 200-1*coefficient, 250-2*coefficient));
                        g2d.fillRect(x+3, (yMax - i)*shapeSize, shapeSize, shapeSize);
                    }
                }

                //Draw x-axis
                g2d.setColor(new Color(0, 0, 0));
                g2d.fillRect(x + 3, yMax * shapeSize + shapeSize, shapeSize, 3);
                //g2d.setColor(new Color(255, 255, 255));
                g2d.drawString(Integer.toString(e.getKey()+1), x+3, (yMax+1) * shapeSize + shapeSize);
            }

        }
    }

}
