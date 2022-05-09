package com.Gołaś.Filip;

import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                GameWindow o = new GameWindow(10,10);

            }
        };
        SwingUtilities.invokeLater(r);
    }

}
