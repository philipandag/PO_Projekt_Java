package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NextTurnButton extends JButton {
    World world;
    public NextTurnButton(World world) {
        super("Nastepna Tura");
        addActionListener((ActionEvent e) -> {
                world.nextTurn();
        });
    }
}
