package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;
import java.io.Serializable;

public class Board extends JPanel implements Serializable {
    private static transient final Dimension preferredSize = new Dimension(640, 640);
    protected Dimension size;
    private transient World world;
    private Field grid[][];

    public Board(Dimension size, World world){
        int min = Math.min(preferredSize.width/size.width, preferredSize.height/size.height);
        setPreferredSize(new Dimension(min*size.width, min*size.height));
        setBorder(BorderFactory.createLineBorder(Color.RED));
        this.world = world;
        this.size = size;
        this.grid = new Field[size.width][size.height];
        int index = 0;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        for(int row = 0; row < size.height; row++){
            for(int col = 0; col < size.width; col++){
                Color color = index % 2 == 0 ? Color.WHITE : new Color(230, 230,230);
                gbc.gridx = col;
                gbc.gridy = row;
                Field field = new Field(color, world, new Point(col, row));
                add(field, gbc);
                grid[col][row] = field;
                grid[col][row].updateFont(new Dimension(preferredSize.width / size.width, preferredSize.height / size.height));
                index++;
            }
            if(size.width %2==0)
                index++;
        }
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

}
