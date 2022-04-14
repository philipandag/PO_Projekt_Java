package com.Gołaś.Filip;

import com.Gołaś.Filip.Organizm;

import java.lang.reflect.Array;
import java.util.*;

public class Swiat {

    ListaOrganizmow organizmy;
    int numerTury = 1;
    private Plansza plansza;

    public Swiat(int x, int y){
        organizmy = new ListaOrganizmow();
        plansza = new Plansza(x, y, this);
    }

    public void setPlansza(Plansza plansza){
        this.plansza = plansza;
    }

    public Swiat dodajOrganizm(Organizm o){
        organizmy.add(o);
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
}
