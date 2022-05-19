package com.Gołaś.Filip.Window.Components;

import javax.swing.*;
import java.awt.*;

public class HexBoardLayoutManager extends BoardLayoutManager {

    private static final double SQRT3 = Math.sqrt(3);
    public HexBoardLayoutManager(Dimension boardDimension) {
        super(boardDimension);
    }

    @Override
    public void layoutContainer(Container parent) {
        int calculatedWidth = (int)(parent.getWidth() / (boardDimension.width + (boardDimension.height - 1) / 2.0));
        int calculatedHeight = (int)(parent.getHeight() / (1 + (boardDimension.height-1) * (3.0/4.0)));
        Component[] fields = parent.getComponents();

        int i = 0;
        if (calculatedHeight / 2.0 < calculatedWidth / SQRT3) {

            calculatedWidth = (int) (calculatedHeight / 2.0 * SQRT3);
            int hexHeightInGrid = (int)(calculatedHeight * (3.0/4.0));

            for (int y = 0; y < boardDimension.height; y++)
                for (int x = 0; x < boardDimension.width; x++, i++) {
                    fields[i].setBounds(x * calculatedWidth + y * calculatedWidth / 2, y * hexHeightInGrid, calculatedWidth, calculatedHeight);
                }

        } else {

            calculatedHeight = (int) (calculatedWidth / SQRT3 * 2.0);
            int hexHeightInGrid = (int)(calculatedHeight * (3.0/4.0));

            for (int y = 0; y < boardDimension.height; y++)
                for (int x = 0; x < boardDimension.width; x++, i++) {
                    fields[i].setBounds(x * calculatedWidth + y * calculatedWidth / 2, y * hexHeightInGrid, calculatedWidth, calculatedHeight);
                }

        }
    }
}
