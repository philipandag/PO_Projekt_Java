package com.Gołaś.Filip.Window;

import com.Gołaś.Filip.Window.Components.Gui;
import com.Gołaś.Filip.Game.World;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{
    private Gui gui;
    private World world;
    private ConsoleWindow consoleWindow;
    public GameWindow(int fieldsHorizontally, int fieldsVertically) {
        super("Zwierzatka");
        consoleWindow = new ConsoleWindow();
        world = new World(new Dimension(fieldsHorizontally, fieldsVertically), this);
        setLayout(new BorderLayout());
        gui = new Gui(world);
        //gui.add(world.getBoard(), BorderLayout.CENTER);
        add(gui, BorderLayout.PAGE_START);
        add(world.getBoard());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        pack();
        setMinimumSize(getSize());
        setVisible(true);
        JFrame frame = this;
    }
}