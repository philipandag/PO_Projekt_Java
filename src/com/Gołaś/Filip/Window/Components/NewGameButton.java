package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NewGameButton extends JButton {
    GameWindow window;
    private class NewGameWindow extends JOptionPane{
        World.BoardType[] modes = {World.BoardType.NORMAL, World.BoardType.HEX};
        NewGameWindow(){
            JPanel panel = new JPanel(new GridLayout(0,1));
            JComboBox<World.BoardType> mode = new JComboBox<World.BoardType>(modes);
            MySpinner width = new MySpinner(1, 50, 20);
            MySpinner height = new MySpinner(1, 50, 20);

            panel.add(new JLabel("Mode:"));
            panel.add(mode);
            panel.add(new JLabel("Width:"));
            panel.add(width);
            panel.add(new JLabel("Height:"));
            panel.add(height);

            int result = JOptionPane.showConfirmDialog(null, panel, "New Game",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                World w = new World(new Dimension((int)width.getValue(), (int)height.getValue()), null, (World.BoardType)mode.getSelectedItem());
                window.setWorld(w);
            }
        }
    }
    public NewGameButton(GameWindow window) {
        super("New Game");
        this.window = window;
        addActionListener((ActionEvent e) -> {
            new NewGameWindow();
        });

    }
}
