package com.Gołaś.Filip.Organisms.Animals;

import com.Gołaś.Filip.Game.Direction;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public abstract class Animal<Species> extends Organism {
    public Animal(Color color, String character, String name, int initiative, int strength, int breedCooldown, int maxBreedCooldown, World world){
        super(color, character, name, initiative, strength, breedCooldown, maxBreedCooldown, world);
    }
    public Animal(Color color, String character, String name, int initiative, int strength, int breedCooldown, int maxBreedCooldown){
        super(color, character, name, initiative, strength, breedCooldown, maxBreedCooldown);
    }

    public void collision(Organism attacker){
        super.collision(attacker);
        if(speciesName == attacker.getSpeciesName()){
            breedWith(attacker);
        }
        else {
            fight(attacker);
        }
    }

    public void breedWith(Organism attacker){
        if(readyToBreed() && attacker.readyToBreed()) {
            forceReproduce();
            resetBreedCooldown();
        }
        else
        {
            System.out.println("\tROZMNAZANIE\t" + speciesName + " nie jest gotowy na rozmnazanie");
        }
    }
}
