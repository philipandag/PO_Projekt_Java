package com.Gołaś.Filip;

import com.Gołaś.Filip.Organizmy.Zwierzeta.Czlowiek;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class OrganizmPopUp extends JPopupMenu {
    JMenuItem czlowiek;
    JMenuItem czlowiek2;
    Pole pole;
    public OrganizmPopUp(MouseEvent e) {
        czlowiek = new JMenuItem("Czlowiek");
        czlowiek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCzlowiek();
            }
        });
        add(czlowiek);

        show(e.getComponent(), e.getX(), e.getY());
        pole = (Pole) e.getComponent();
    }

    public void setPole(Pole pole){
        this.pole = pole;
    }

    public void addCzlowiek(){
        pole.setPole(new Czlowiek());
    }
}

