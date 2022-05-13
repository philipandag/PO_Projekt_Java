package com.Gołaś.Filip.Organisms.Plants;

import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class Dandelion extends Plant{
    private static final int BREEDING_COOLDOWN = 3;
    private static final double BREED_CHANCE = 0.1;
    private static final int STRENGTH = 0;
    public static final String NAME = "Mlecz";
    private static final String CHARACTER = "M";
    private static final Color COLOR = new Color(180, 255, 80);
    private static final int BREED_ATTEMPTS = 3;

    public Dandelion(World world){
        this();
        setWorld(world);
    }

    public Dandelion(){
        super(COLOR, CHARACTER, NAME, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN, BREED_CHANCE);
    }

    @Override
    public Organism clone() {
        return new Dandelion();
    }

    @Override
    public void breed() {
        boolean didReproduce = false;
        for(int i = 0; i < BREED_ATTEMPTS; i++) {
           if(readyToBreed()) {
               forceReproduce();
               didReproduce = true;
           }
        }
        if(didReproduce)
            resetBreedCooldown();
    }
}
