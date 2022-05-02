package com.Gołaś.Filip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OknoGry extends JFrame{
    private Plansza plansza;
    private Gui gui;
    private Swiat swiat;
    OknoGry(int polWPoziomie, int polWPionie) {
        super("Zwierzatka");
        swiat = new Swiat(polWPoziomie, polWPionie);
        swiat.setOkno(this);
        plansza = new Plansza(polWPoziomie, polWPionie, swiat);
        swiat.setPlansza(plansza);
        gui = new Gui(swiat);

        add(gui, BorderLayout.PAGE_START);
        gui.add(plansza);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        pack();
        setMinimumSize(getSize());
        setVisible(true);
    }

    public final JComponent getPlansza(){
        return plansza;
    }
}