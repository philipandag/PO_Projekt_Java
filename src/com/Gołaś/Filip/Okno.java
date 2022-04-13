package com.Gołaś.Filip;

import javax.swing.*;
import java.awt.*;

public class Okno extends JFrame{
    private Plansza plansza;
    private Gui gui;
    private Swiat swiat;
    Okno(int polWPoziomie, int polWPionie) {
        super("Zwierzatka");
        swiat = new Swiat(polWPoziomie, polWPionie);
        plansza = new Plansza(polWPoziomie, polWPionie, swiat);
        swiat.setPlansza(plansza);
        gui = new Gui();
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