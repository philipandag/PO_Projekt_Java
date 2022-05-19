package com.Gołaś.Filip.Window.Components;


import com.Gołaś.Filip.Game.Direction;
import com.Gołaś.Filip.Game.DirectionInterface;
import com.Gołaś.Filip.Game.DirectionValueEnum;
import com.Gołaś.Filip.Game.World;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class AbstractBoard extends JPanel implements Serializable {
    protected static transient final Dimension defaultSize = new Dimension(640, 640);
    protected Dimension size;
    protected World world;
    protected Field[][] grid;
    protected Class<? extends DirectionInterface> directionClass;

    protected AbstractBoard(Dimension size, World world, Class<? extends Direction> directionClass){
        int min = Math.min(defaultSize.width/size.width, defaultSize.height/size.height);
        setPreferredSize(new Dimension(min*size.width, min*size.height));
        this.directionClass = directionClass;
        this.world = world;
        this.size = size;
        this.grid = createGrid();
        setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    public Field[][] getGrid()
    {
        return grid;
    }

    public boolean onBoard(Point pos){
        return pos.getX() >= 0 && pos.getX() < size.width && pos.getY() >= 0 && pos.getY() < size.height;
    }

    public Field at(Point pos){
        if(onBoard(pos)) {
            return grid[pos.x][pos.y];
        }
        return null;
    }

    public abstract Field[][] createGrid();

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public Dimension getSize(){
        return size;
    }

    public DirectionInterface createDirection(){
        try {
            return (Direction)getDirectionConstructor().newInstance();
        }catch(InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AbstractBoard createBoard(Dimension size) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return this.getClass().getConstructor(Dimension.class).newInstance(size);
    }

    public static AbstractBoard createBoard (Class<? extends AbstractBoard> c, Dimension size) {
        try{
            return c.getConstructor(Dimension.class).newInstance(size);
        }catch(Exception e){
            e.printStackTrace();
        };
        return null;
    }

    private Constructor<? extends DirectionInterface> getDirectionConstructor() throws NoSuchMethodException {
            return directionClass.getConstructor();
    }

    public Class<? extends DirectionInterface> getDirectionClass(){
        return this.directionClass;
    }
}
