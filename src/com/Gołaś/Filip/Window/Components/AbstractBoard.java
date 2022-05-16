package com.Gołaś.Filip.Window.Components;


import com.Gołaś.Filip.Game.World;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public abstract class AbstractBoard extends JPanel implements Serializable {
    protected static transient final Dimension defaultSize = new Dimension(640, 640);
    protected Dimension size;
    protected transient World world;
    protected AbstractField grid[][];

    public AbstractBoard(Dimension size, World world){
        int min = Math.min(defaultSize.width/size.width, defaultSize.height/size.height);
        setPreferredSize(new Dimension(min*size.width, min*size.height));
        this.world = world;
        this.size = size;
        this.grid = createGrid();
    }
    public AbstractField[][] getGrid()
    {
        return grid;
    }

    public boolean onBoard(Point pos){
        return pos.getX() >= 0 && pos.getX() < size.width && pos.getY() >= 0 && pos.getY() < size.height;
    }

    public AbstractField at(Point pos){
        if(onBoard(pos)) {
            return grid[pos.x][pos.y];
        }
        return null;
    }

    public abstract AbstractField[][] createGrid();

    public void setWorld(World world) {
        this.world = world;
    }
}
