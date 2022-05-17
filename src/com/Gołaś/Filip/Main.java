package com.Gołaś.Filip;

import com.Gołaś.Filip.Window.GameWindow;
import com.Gołaś.Filip.Window.TestWindow;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        Runnable r = () ->
            new GameWindow(5,5);

        SwingUtilities.invokeLater(r);
    }

}
