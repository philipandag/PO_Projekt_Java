package com.Gołaś.Filip.Window.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HexField extends JButton {

    private Polygon polygon;
    private static final double sqrt3 = Math.sqrt(3);
    private int sideLength = 50;
    private int WIDTH = (int)(sqrt3*sideLength);
    private int HEIGHT = 2*sideLength;
    private Point position ;
    private Point gridPosition;
    public HexField(int sideLength, int row, int collumn) {
        super();
        this.gridPosition = new Point(collumn, row);
        this.sideLength = sideLength;
        this.WIDTH = (int)(sqrt3*sideLength);
        this.HEIGHT = 2*sideLength;

        this.position = new Point(getBounds().x + WIDTH/2, getBounds().y + HEIGHT/2);
        polygon = new Polygon();
        setContentAreaFilled(false);
        setFocusPainted(true);
        setBorderPainted(false);
        //setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(null);
        for (int i = 0; i < 6; i++) {
            int xval = (int) (position.x + sideLength
                    * Math.sin(i * 2 * Math.PI / 6D));
            int yval = (int) (position.y + sideLength
                    * Math.cos(i * 2 * Math.PI / 6D));
            polygon.addPoint(xval, yval);
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return polygon.contains(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillPolygon(polygon);
        g.setColor(Color.BLACK);
        g.drawPolygon(polygon);
    }

    public Point getPosition() {
        return gridPosition;
    }
}
