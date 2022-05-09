package com.Gołaś.Filip.Organisms.Plants;

import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class Guarana extends Plant{
    private static final int BREEDING_COOLDOWN = 3;
    private static final double BREED_CHANCE = 0.1;
    private static final int STRENGTH = 0;
    private static final String NAME = "Guarana";
    private static final String CHARACTER = "G";
    private static final Color COLOR = new Color(255, 100, 80);
    private static final int STRENGTH_BONUS = 3;

    public Guarana(){
        super(COLOR, CHARACTER, NAME, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN, BREED_CHANCE);
    }

    public Guarana(World world){
        this();
        setWorld(world);
    }

    @Override
    public Organism clone() {
        return new Guarana(world);
    }

    @Override
    public void collision(Organism attacker) {
        super.collision(attacker);
        attacker.changeStrength(STRENGTH_BONUS);
    }
}
