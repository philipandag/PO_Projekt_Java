package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;

import javax.swing.*;
import java.awt.*;

public class HexBoard extends AbstractBoard {
    private static final double SQRT3 = Math.sqrt(3);
    private int fieldSideLength = 10;
    private int fieldHeight;
    private int fieldWidth;
    private static final Color defaultColor = Color.WHITE;

    public HexBoard(Dimension size, World world){
        super(size, world);
        setLayout(new HexBoardLayoutManager(size));
        for(int row = 0; row < size.height; row++) {
            for (int collumn = 0; collumn < size.width; collumn++) {
                grid[collumn][row] = new HexField(defaultColor, world, new Point(collumn, row), 10, collumn, row);
                add(grid[collumn][row]);
            }
        }
    }

    @Override
    public Field[][] createGrid() {
        return new HexField[size.width][size.height];
    }
}
