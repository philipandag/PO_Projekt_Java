package com.Gołaś.Filip;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pole extends JButton{
    private Organizm organizm;
    private Swiat swiat;
    private boolean puste;
    private Color pusteKolor;
    private int posX;
    private int posY;
    public Pole(Color background, Swiat swiat, int x, int y) {
        this.posX = x;
        this.posY = y;
        this.swiat = swiat;
        this.organizm = null;
        this.puste = true;
        this.pusteKolor = background;
        setBackground(pusteKolor);
        setBorder(new EmptyBorder(0, 0, 0, 0));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(((Pole)e.getComponent()).puste()) {
                    super.mousePressed(e);
                    OrganizmPopUp popup = new OrganizmPopUp(e);
                }
            }
        });
    }
    public Swiat getSwiat(){
        return swiat;
    }
    public void setPole(Organizm organizm){
        if(organizm == null){
            wyczyscPole();
            return;
        }
        this.organizm = organizm;
        organizm.posX = this.posX;
        organizm.poxY = this.posY;
        puste = false;
        setBackground(organizm.getKolor());
        setText(organizm.getZnak());
        setForeground(Color.BLACK);
    }

    public void wyczyscPole(){
        organizm = null;
        puste = true;
        setBackground(pusteKolor);
        setText("");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(32, 32);
    }

    public boolean puste(){
        return puste;
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY(){
        return posY;
    }
}
