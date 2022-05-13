package com.Gołaś.Filip.Window;

import com.Gołaś.Filip.Window.Components.HexBoard;
import com.Gołaś.Filip.Window.Components.HexField;

import javax.swing.*;
import java.awt.*;

public class TestWindow extends JFrame{

    public TestWindow(int fieldsHorizontally, int fieldsVertically) {
        super("Zwierzatka");


        setLayout(new BorderLayout());
        add(new HexBoard(new Dimension(fieldsHorizontally,fieldsVertically)), BorderLayout.CENTER);
        setMinimumSize(new Dimension(640, 640));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        pack();
        setMinimumSize(getSize());
        setVisible(true);
        JFrame frame = this;
    }

}