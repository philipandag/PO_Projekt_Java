package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;

import javax.swing.*;
import java.awt.*;

public class HexBoard extends AbstractBoard {
    private static final double SQRT3 = Math.sqrt(3);
    private int FIELD_SIDE_LENGTH = 10;
    private int fieldHeight;
    private int fieldWidth;
    private static final Color defaultColor = Color.WHITE;

    public HexBoard(Dimension size, World world){
        super(size, world);
        int offsetX, offsetY;
        setLayout(null);
        fieldWidth = (int)(FIELD_SIDE_LENGTH*SQRT3);
        fieldHeight = FIELD_SIDE_LENGTH*2;
        setBorder(BorderFactory.createLineBorder(Color.black));
        grid = new HexField[size.width][size.height];
        for(int row = 0; row < size.height; row++) {
            for (int collumn = 0; collumn < size.width; collumn++) {

                grid[collumn][row] = new HexField(defaultColor, world, new Point(collumn, row), FIELD_SIDE_LENGTH, collumn, row);
                //fields[row][row].moveGrid(new Point(collumn, row));
                offsetY = (int) ((3.0/2.0)*(row * FIELD_SIDE_LENGTH));
                offsetX = (int) (collumn * fieldWidth) + (int) (row * fieldWidth/2);

                grid[collumn][row].setBounds(offsetX, offsetY, fieldWidth, fieldHeight);
                add(grid[collumn][row]);
            }
        }
    }

    @Override
    public Field[][] createGrid() {
        return new HexField[size.width][size.height];
    }
}
