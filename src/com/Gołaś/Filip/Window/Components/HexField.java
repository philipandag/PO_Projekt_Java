package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class HexField extends Field {
    private static final double sqrt3 = Math.sqrt(3);
    private Polygon polygon;
    private int sideLength;
    private int WIDTH;
    private int HEIGHT;
    private Point center;

    public HexField(Color color, World world, Point pos, int sideLength, int row, int collumn) {
        super(color, world, pos);
        this.pos = new Point(row, collumn);
        this.sideLength = sideLength;
        this.WIDTH = (int)(sqrt3*sideLength);
        this.HEIGHT = 2*sideLength;
        this.center = new Point(getBounds().x + WIDTH/2, getBounds().y + HEIGHT/2);
        polygon = createHexagon();
        setContentAreaFilled(false);
        setFocusPainted(true);
        setBorderPainted(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(null);
    }

    @Override
    public boolean contains(int x, int y) {
        return polygon.contains(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillPolygon(polygon);
        g.setColor(Color.BLACK);
        g.drawPolygon(polygon);
        g.drawString(this.getText(), (int)(center.x*0.85), (int)(center.y*1.2));
    }

    private Polygon createHexagon(){
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++) {
            int xval = (int) (center.x + sideLength
                    * Math.sin(i * 2 * Math.PI / 6D));
            int yval = (int) (center.y + sideLength
                    * Math.cos(i * 2 * Math.PI / 6D));
            p.addPoint(xval, yval);
        }
        return p;
    }
}
