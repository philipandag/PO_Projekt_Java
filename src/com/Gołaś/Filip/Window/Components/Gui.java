package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.Direction;
import com.Gołaś.Filip.Game.World;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Gui extends JPanel {
    World world;
    HumanInputIndicator hdi;
    public Gui(World world){
        this.world = world;
        setLayout(new BorderLayout(3, 3));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        add(tools, BorderLayout.PAGE_START);
        tools.add(new NextTurnButton(world));
        tools.add(new SaveButton(world));
        tools.add(new LoadButton(world));
        hdi = new HumanInputIndicator(Direction.N, world.getWindow());
        tools.add(hdi);
        world.addHumanDirectionListener(hdi);
    }
}
