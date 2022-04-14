package com.Gołaś.Filip;

import com.Gołaś.Filip.Organizmy.Zwierzeta.Czlowiek;
import com.Gołaś.Filip.Organizmy.Zwierzeta.Wilk;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;

public class OrganizmPopUp extends JPopupMenu {
    JMenuItem czlowiek;
    JMenuItem wilk;
    Pole pole;
    Swiat swiat;

    private interface itemAdder{
        public void addItem();
    }
    private void addItem(JMenuItem item, itemAdder adder){
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adder.addItem();
            }
        });
        add(item);
    }

    public OrganizmPopUp(MouseEvent e) {
        addItem(new JMenuItem("Czlowiek"), () -> {
            System.out.println("\t# Postawiono Czlowieka na (" + pole.getPosX() + ", " + pole.getPosY() + ")");
            Czlowiek o = new Czlowiek();
            swiat.dodajOrganizm(o);
            pole.setPole(o);
        });
        addItem(new JMenuItem("Wilk"), () -> {
            System.out.println("\t# Postawiono Wilka na (" + pole.getPosX() + ", " + pole.getPosY() + ")");
            Wilk o = new Wilk();
            swiat.dodajOrganizm(o);
            pole.setPole(o);
        });



        show(e.getComponent(), e.getX(), e.getY());
        pole = (Pole) e.getComponent();
        swiat = ((Pole) e.getComponent()).getSwiat();
    }


    public void setPole(Pole pole){
        this.pole = pole;
    }

}

