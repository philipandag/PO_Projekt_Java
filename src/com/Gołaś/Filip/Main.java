package com.Gołaś.Filip;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Okno o = new Okno(7,9);
            }
        };
        SwingUtilities.invokeLater(r);
    }

}
