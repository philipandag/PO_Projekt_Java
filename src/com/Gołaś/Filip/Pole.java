package com.Gołaś.Filip;

import com.Gołaś.Filip.Organizmy.Zwierzeta.Czlowiek;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.Gołaś.Filip.OrganizmPopUp;
public class Pole extends JButton{
    private Organizm organizm;
    private Swiat swiat;
    private boolean puste;
    private Color pusteKolor;
    public Pole(Color background, Swiat swiat) {
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
        this.organizm = organizm;
        organizm.setSwiat(swiat);
        puste = false;
        setBackground(organizm.getKolor());
        setText(organizm.getZnak());
    }

    public void usunOrganizm(){
        swiat.usunOrganizm(organizm);
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
}
