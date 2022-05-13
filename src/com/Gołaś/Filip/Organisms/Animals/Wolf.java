package com.Gołaś.Filip.Organisms.Animals;

import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class Wolf extends Animal {
    private static final int BREEDING_COOLDOWN = 3;
    private static final int STRENGTH = 9;
    private static final int INITIATIVE = 5;
    public static final String NAME = "Wilk";
    private static final String CHARACTER = "W";
    private static final Color COLOR = new Color(100, 100, 100);

    public Wolf(World world){
        this();
        setWorld(world);
    }
    public Wolf(){
        super(COLOR, CHARACTER, NAME, INITIATIVE, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN);
    }

    @Override
    public void action() {

    }

    @Override
    public Organism clone() {
        return new Wolf();
    }
    public static Wolf clone(World w) { return new Wolf(w);}
}