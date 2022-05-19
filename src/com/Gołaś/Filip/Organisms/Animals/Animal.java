package com.Gołaś.Filip.Organisms.Animals;

import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public abstract class Animal extends Organism {
    protected Animal(Color color, String character, int initiative, int strength, int breedCooldown, int maxBreedCooldown, World world){
        super(color, character, initiative, strength, breedCooldown, maxBreedCooldown, world);
    }
    protected Animal(Color color, String character, int initiative, int strength, int breedCooldown, int maxBreedCooldown){
        super(color, character, initiative, strength, breedCooldown, maxBreedCooldown);
    }
    @Override
    public void collision(Organism attacker){
        super.collision(attacker);
        if(getName().equals(attacker.getName())){
            breedWith(attacker);
        }
        else {
            fight(attacker);
        }
    }

    public void breedWith(Organism attacker){
        if(readyToBreed() && attacker.readyToBreed()) {
            reproduce();
            resetBreedCooldown();
        }
    }
}
