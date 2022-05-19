package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NewGameButton extends JButton {
    GameWindow window;
    private class NewGameWindow extends JOptionPane{
        NewGameWindow(){
            JPanel panel = new JPanel(new GridLayout(0,1));
            JComboBox<String> mode = new JComboBox<>(new String[]{"Square", "Hex"});
            MySpinner width = new MySpinner(1, 100, 20);
            MySpinner height = new MySpinner(1, 100, 20);
            JSlider fill = new JSlider(0,100,0);

            panel.add(new JLabel("Mode:"));
            panel.add(mode);
            panel.add(new JLabel("Width:"));
            panel.add(width);
            panel.add(new JLabel("Height:"));
            panel.add(height);
            panel.add(new JLabel("Fill %"));
            panel.add(fill);

            int result = JOptionPane.showConfirmDialog(null, panel, "New Game",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                World w = switch((String)mode.getSelectedItem())
                {
                    case "Square" -> w = new World(new Dimension((int)width.getValue(), (int)height.getValue()), null, Board.class);
                    case "Hex" -> w = new World(new Dimension((int)width.getValue(), (int)height.getValue()), null, HexBoard.class);
                    default -> null;
                };
                window.setWorld(w);
                window.getWorld().fill(fill.getValue());
            }
        }
    }
    public NewGameButton(GameWindow window) {
        super("New Game");
        this.window = window;
        setFocusable(false);
        addActionListener((ActionEvent e) -> new NewGameWindow());

    }
}
