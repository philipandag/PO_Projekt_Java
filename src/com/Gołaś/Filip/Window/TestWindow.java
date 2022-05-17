package com.Gołaś.Filip.Window;

import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Window.Components.HexBoard;
import com.Gołaś.Filip.Window.Components.HexField;

import javax.swing.*;
import java.awt.*;

public class TestWindow extends GameWindow{

    public TestWindow(int fieldsHorizontally, int fieldsVertically) {
        super(fieldsHorizontally, fieldsVertically);

        setLayout(new BorderLayout());
        add(new HexBoard(new Dimension(fieldsHorizontally,fieldsVertically), new World(new Dimension(fieldsHorizontally, fieldsVertically), this, World.BoardType.HEX)), BorderLayout.CENTER);
        setMinimumSize(new Dimension(640, 640));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        pack();
        setMinimumSize(getSize());
        setVisible(true);
        JFrame frame = this;
    }

}