package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class HexField extends Field {
    private static final double SQRT3 = Math.sqrt(3);
    private Polygon polygon;
    private int sideLength;
    private Point center;
    private int hexWidth;
    private int hexHeight;

    public HexField(Color color, World world, Point pos, int sideLength, int row, int collumn) {
        super(color, world, pos);
        this.pos = new Point(row, collumn);
        this.sideLength = sideLength;
        this.center = new Point((int)(sideLength*SQRT3/2), sideLength);
        this.polygon = createHexagon();
        setContentAreaFilled(false);
        setFocusPainted(true);
        setBorderPainted(false);
        setLayout(null);
        setFont(new Font("Monospaced", Font.BOLD, sideLength));
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
        g.drawString(this.getText(), getWidth()/4, sideLength*3/2);
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


    @Override
    public void setBounds(int x, int y, int width, int height) {
        if(width != getWidth() || height != getHeight() ){
            super.setBounds(x, y, width, height);
            this.sideLength = (int) (height / 2.0);
            this.center = new Point(getWidth() / 2, getHeight() / 2);
            polygon = createHexagon();
        } else {
            super.setBounds(x, y, width, height);
        }
    }

    @Override
    public void updateFont(int fieldHeight) {
        super.updateFont(fieldHeight*3/4);
    }
}
