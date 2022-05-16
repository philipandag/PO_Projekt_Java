package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HexBoard extends AbstractBoard {
    private static final double sqrt3 = Math.sqrt(3);
    private static final int fieldSideLength = 20;
    private static final int fieldHeight = 2*fieldSideLength;
    private static final int fieldWidth = (int)(sqrt3*fieldSideLength);
    private static final Color defaultColor = Color.WHITE;

    public HexBoard(Dimension size, World world){
        super(size, world);
        int offsetX, offsetY;
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.black));
        grid = new HexField[size.width][size.height];
        for(int row = 0; row < size.height; row++) {
            for (int collumn = 0; collumn < size.width; collumn++) {

                grid[collumn][row] = new HexField(defaultColor, world, new Point(collumn, row), fieldSideLength, collumn, row);
                //fields[row][row].moveGrid(new Point(collumn, row));
                offsetY = (int) ((3.0/2.0)*(row * fieldSideLength));
                offsetX = (int) (collumn * fieldWidth) + (int) (row * fieldWidth/2);

                grid[collumn][row].setBounds(offsetX, offsetY, fieldWidth, fieldHeight);
                add(grid[collumn][row]);
            }
        }
    }

    @Override
    public AbstractField[][] createGrid() {
        return new HexField[size.width][size.height];
    }
}
