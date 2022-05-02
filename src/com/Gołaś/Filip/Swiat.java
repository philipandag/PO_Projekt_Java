package com.Gołaś.Filip;

import com.Gołaś.Filip.Organizm;
import com.Gołaś.Filip.Organizmy.Zwierzeta.Czlowiek;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.security.Key;
import java.util.*;

public class Swiat {

    ListaOrganizmow organizmy;
    int numerTury = 1;
    private Plansza plansza;
    private OknoGry okno;

    public Swiat(int x, int y){
        organizmy = new ListaOrganizmow();
        plansza = new Plansza(x, y, this);
        System.out.println("# Poczatek gry");
    }

    public void setPlansza(Plansza plansza){
        this.plansza = plansza;
    }

    public Plansza getPlansza() {
        return plansza;
    }

    public Pole[][] getPlanszaGrid(){
        return plansza.getGrid();
    }
    public Swiat dodajOrganizm(Organizm o){
        organizmy.add(o);
        if(o instanceof KeyListener) {
            okno.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    System.out.println("Typed");
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    System.out.println("Pressed");
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    System.out.println("Relased");
                }
            });
        }
        o.swiat = this;
        return this;
    }

    public Swiat usunOrganizm(Organizm o){
        organizmy.remove(o);
        return this;
    }

    public void nastepnaTura() {
        System.out.println("# Nastala tura " + numerTury);
        for(Organizm organizm : organizmy){
            organizm.akcja();
        }
        numerTury++;
    }

    public void setOkno(OknoGry okno) {
        this.okno = okno;
    }
}
