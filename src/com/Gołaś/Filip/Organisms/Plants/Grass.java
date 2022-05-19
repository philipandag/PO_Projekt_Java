package com.Gołaś.Filip.Organisms.Plants;

import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class Grass extends Plant{
    private static final int BREEDING_COOLDOWN = 3;
    private static final double BREED_CHANCE = 0.1;
    private static final int STRENGTH = 0;
    private static final String CHARACTER = "g";
    private static final Color COLOR = new Color(20, 80, 20);

    public Grass(){
        super(COLOR, CHARACTER, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN, BREED_CHANCE);
    }

    public Grass(World world){
        this();
        setWorld(world);
    }

}
