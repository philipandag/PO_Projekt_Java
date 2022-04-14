package com.Gołaś.Filip;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                OknoKonsola k = new OknoKonsola();
                OknoGry o = new OknoGry(20,20);
            }
        };
        SwingUtilities.invokeLater(r);
    }

}
