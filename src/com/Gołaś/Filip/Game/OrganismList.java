package com.Gołaś.Filip.Game;

import com.Gołaś.Filip.Organisms.Organism;

import java.util.ArrayList;
import java.util.ListIterator;

public class OrganismList extends ArrayList<Organism> {
    @Override
    public boolean add(Organism organism) {
        ListIterator<Organism> it = this.listIterator();
        while(it.hasNext()){
            Organism o = it.next();
            if(o.getInitiative() < organism.getInitiative()){
                it.previous();
                break;
            }
        }
        it.add(organism);
        return true;
    }
}
