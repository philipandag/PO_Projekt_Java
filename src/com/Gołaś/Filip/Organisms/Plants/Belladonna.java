package com.Gołaś.Filip.Organisms.Plants;

import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class Belladonna extends Plant{
    private static final int BREEDING_COOLDOWN = 3;
    private static final double BREED_CHANCE = 0.1;
    private static final int STRENGTH = 99;
    private static final String NAME = "WilczaJagoda";
    private static final String CHARACTER = "J";
    private static final Color COLOR = new Color(120, 0, 0);

    public Belladonna(World world){
        this();
        setWorld(world);
    }

    public Belladonna(){
        super(COLOR, CHARACTER, NAME, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN, BREED_CHANCE);
    }

    @Override
    public Organism clone() {
        return new Belladonna(world);
    }

    @Override
    public void collision(Organism attacker) {
        kill();
        attacker.kill();
    }
}
