package com.Gołaś.Filip;

import com.Gołaś.Filip.Window.GameWindow;
import com.Gołaś.Filip.Window.TestWindow;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                new TestWindow(20,20);
            }
        };
        SwingUtilities.invokeLater(r);
    }

}
