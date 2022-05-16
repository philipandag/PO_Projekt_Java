package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NextTurnButton extends JButton {
    GameWindow window;
    public NextTurnButton(GameWindow window) {
        super("Nastepna Tura");
        this.window = window;
        addActionListener((ActionEvent e) -> {
                window.getWorld().nextTurn();
        });
    }
}
