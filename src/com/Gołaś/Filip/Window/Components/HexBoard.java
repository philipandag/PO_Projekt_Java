package com.Gołaś.Filip.Window.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HexBoard extends JPanel {
    private HexField fields[][];
    private static final double sqrt3 = Math.sqrt(3);
    private static final int fieldSideLength = 20;
    private static final int fieldHeight = 2*fieldSideLength;
    private static final int fieldWidth = (int)(sqrt3*fieldSideLength);

    public HexBoard(Dimension size){
        int offsetX, offsetY;
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.black));
        fields = new HexField[size.height][size.width];
        for(int row = 0; row < size.height; row++) {
            for (int collumn = 0; collumn < size.width; collumn++) {

                fields[row][collumn] = new HexField(fieldSideLength, collumn, row);
                //fields[row][collumn].moveGrid(new Point(collumn, row));
                offsetY = (int) ((3.0/2.0)*(row * fieldSideLength));
                offsetX = (int) (collumn * fieldWidth) + (int) (row * fieldWidth/2);

                fields[row][collumn].setBounds(offsetX, offsetY, fieldWidth, fieldHeight);
                fields[row][collumn].addActionListener((ActionEvent event) -> {
                    HexField clicked = (HexField) event.getSource();
                    System.out.println("CLICK!, (" + clicked.getPosition().x + ", " + clicked.getPosition().y + ")");
                });
                add(fields[row][collumn]);
            }
        }
    }
}
