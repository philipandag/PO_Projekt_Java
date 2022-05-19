package com.Gołaś.Filip.Organisms.Plants;

import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public abstract class Plant extends Organism{
    protected double breedChance;

    protected Plant(Color color, String character, int strength, int breedCooldown, int maxBreedCooldown, double breedChance){
        super(color, character, 0, strength, breedCooldown, maxBreedCooldown);
        this.breedChance = breedChance;
    }

    protected Plant(Color color, String character, int strength, int breedCooldown, int maxBreedCooldown, double breedChance, World world){
        this(color, character, strength, breedCooldown, maxBreedCooldown, breedChance);
        setWorld(world);
    }

    @Override
    public void collision(Organism attacker) {
        super.collision(attacker);
        fight(attacker);
    }

    @Override
    public void action() {
        breed();
        breedCooldownDown();
    }

    @Override
    public boolean readyToBreed() {
        return super.readyToBreed() && World.randomiser.chance(breedChance);
    }
}
