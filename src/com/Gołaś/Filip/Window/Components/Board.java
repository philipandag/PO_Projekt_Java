package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.DirectionSquare;
import com.Gołaś.Filip.Game.World;

import java.awt.*;
import java.io.Serializable;

public class Board extends AbstractBoard implements Serializable {

    public Board(Dimension size, World world){
        super(size, world, DirectionSquare.class);
        setLayout(new BoardLayoutManager(size));
        int index = 0;
        for(int row = 0; row < size.height; row++) {
            for (int col = 0; col < size.width; col++) {
                Color color = index % 2 == 0 ? Color.WHITE : new Color(230, 230, 230);
                Field field = new Field(color, world, new Point(col, row));
                add(field);
                grid[col][row] = field;
                index++;
            }
            if(size.width %2==0)
                index++;
        }
    }

    @Override
    public Field[][] createGrid(){
        return new Field[size.width][size.height];
    }

}
