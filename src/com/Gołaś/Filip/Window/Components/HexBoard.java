package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.DirectionHex;
import com.Gołaś.Filip.Game.World;

import javax.swing.*;
import java.awt.*;

public class HexBoard extends AbstractBoard {
    private static final Color defaultColor = Color.WHITE;

    public HexBoard(Dimension size, World world){
        super(size, world, DirectionHex.class);
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
