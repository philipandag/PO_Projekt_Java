package com.Gołaś.Filip.Window;

import com.Gołaś.Filip.Window.Components.AbstractBoard;
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
        world = new World(new Dimension(fieldsHorizontally, fieldsVertically), this, World.BoardType.HEX);
        setLayout(new BorderLayout());
        gui = new Gui(this);
        //gui.add(world.getBoard(), BorderLayout.CENTER);
        add(gui, BorderLayout.PAGE_START);
        add(world.getBoard(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        pack();
        setMinimumSize(getSize());
        setVisible(true);
        JFrame frame = this;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public void setBoard(AbstractBoard board){
        for(Component c : getContentPane().getComponents()){
            if(c instanceof AbstractBoard)
                getContentPane().remove(c);
        }
        add(board);
        revalidate();
        repaint();
    }

    public Gui getGui() {
        return gui;
    }
}