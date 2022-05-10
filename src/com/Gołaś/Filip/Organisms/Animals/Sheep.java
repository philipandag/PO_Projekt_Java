package com.Gołaś.Filip.Organisms.Animals;

import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class Sheep extends Animal {
    private static final int BREEDING_COOLDOWN = 3;
    private static final int STRENGTH = 4;
    private static final int INITIATIVE = 4;
    private static final String NAME = "Sheep";
    private static final String CHARACTER = "S";
    private static final Color COLOR = new Color(180, 180, 180);

    public Sheep(){
        super(COLOR, CHARACTER, NAME, INITIATIVE, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN);
    }
    public Sheep(World world){
        this();
        setWorld(world);
    }

    @Override
    public Organism clone() {
        return new Sheep(world);
    }
}