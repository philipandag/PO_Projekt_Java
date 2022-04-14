package com.Gołaś.Filip;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class ListaOrganizmow extends ArrayList<Organizm> {
    @Override
    public boolean add(Organizm organizm) {
        ListIterator<Organizm> it = this.listIterator();
        while(it.hasNext()){
            Organizm o = it.next();
            if(o.getInicjatywa() < organizm.getInicjatywa()){
                it.previous();
                break;
            }
        }
        it.add(organizm);
        return true;
    }
}
