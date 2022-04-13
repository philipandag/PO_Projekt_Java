package com.Gołaś.Filip;

import com.Gołaś.Filip.Organizm;

import java.util.*;

public class Swiat {

    Set<Organizm> organizmy;
    private Plansza plansza;
    public Swiat(int wymiarX, int wymiarY){
        organizmy = new TreeSet<Organizm>((o1, o2) -> o1.compareTo(o2));
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
}
