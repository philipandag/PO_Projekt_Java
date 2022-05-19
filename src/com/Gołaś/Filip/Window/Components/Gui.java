package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Gui extends JPanel {
    private static final String SAVE_FILE_PATH = "zapis.zwierzaki";
    GameWindow window;
    public Gui(GameWindow window){
        this.window = window;
        setLayout(new BorderLayout(3, 3));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setFocusable(false);
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        add(tools, BorderLayout.PAGE_START);
        tools.add(new NextTurnButton(window));
        tools.add(new SaveButton(window, SAVE_FILE_PATH));
        tools.add(new LoadButton(window, SAVE_FILE_PATH));
        tools.add(new NewGameButton(window));
    }
}
