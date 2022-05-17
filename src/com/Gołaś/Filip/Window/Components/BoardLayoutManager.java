package com.Gołaś.Filip.Window.Components;

import java.awt.*;
import java.io.Serializable;

public class BoardLayoutManager implements LayoutManager, Serializable {

    protected Dimension boardDimension;

    public BoardLayoutManager(Dimension boardDimension){
        this.boardDimension = boardDimension;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        // not used
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        // not used
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return parent.getPreferredSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return parent.getMinimumSize();
    }

    @Override
    public void layoutContainer(Container parent) {
        int smallerDimension = Math.min(parent.getWidth() / boardDimension.width, parent.getHeight() / boardDimension.height);
        Dimension fieldDimension = new Dimension(smallerDimension, smallerDimension);
        int horizontalDifference = parent.getWidth() - fieldDimension.width * boardDimension.width;
        Component[] fields = parent.getComponents();

        int i = 0;
        for(int y = 0; y < boardDimension.height; y++)
            for(int x = 0; x < boardDimension.width; x++, i++)
            {
                fields[i].setBounds(horizontalDifference/2 + x * smallerDimension, y * smallerDimension, smallerDimension, smallerDimension);
            }
    }
}
