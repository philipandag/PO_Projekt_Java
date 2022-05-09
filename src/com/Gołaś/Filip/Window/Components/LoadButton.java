package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;

import javax.swing.*;

public class LoadButton extends JButton {
    World world;
    LoadButton(World world){
        super("Wczytaj");
        this.world = world;
    }
}
