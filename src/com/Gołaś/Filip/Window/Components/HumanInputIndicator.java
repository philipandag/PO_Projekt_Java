package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.Direction;
import com.Gołaś.Filip.Listeners.HumanInputListener;
import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class HumanInputIndicator extends JPanel implements HumanInputListener {
    Direction humanDirection;
    Window window;
    JTextArea title;
    JTextArea direction;

    HumanInputIndicator(Direction humanDirection, GameWindow window) {
        this.window = window;
        this.title = new JTextArea("Kierunek ruchu Ludzi: ");
        this.humanDirection = humanDirection;
        this.direction = new JTextArea(humanDirection.toString());
        title.setEditable(false);
        direction.setEditable(false);
        add(title);
        add(direction);
        setVisible(true);
    }

    public void setDirection(Direction d)
    {
        humanDirection = d;
        direction.setText(humanDirection.toString());
    }

    @Override
    public void execute(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_DOWN -> {
                humanDirection.value = Direction.Value.S;
            }
            case KeyEvent.VK_LEFT -> {
                humanDirection.value = Direction.Value.W;
            }
            case KeyEvent.VK_RIGHT -> {
                humanDirection.value = Direction.Value.E;
            }
            case KeyEvent.VK_UP -> {
                humanDirection.value = Direction.Value.N;
            }
        }
        direction.setText(humanDirection.toString());
    }
}
