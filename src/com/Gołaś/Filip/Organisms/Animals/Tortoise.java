package com.Gołaś.Filip.Organisms.Animals;

import com.Gołaś.Filip.Game.Direction;
import com.Gołaś.Filip.Game.DirectionInterface;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class Tortoise extends Animal {
    private static final int BREEDING_COOLDOWN = 3;
    private static final int STRENGTH = 2;
    private static final int INITIATIVE = 1;
    private static final String CHARACTER = "T";
    private static final Color COLOR = new Color(60, 80, 60);
    private static final int MINIMUM_ATTACKER_STRENGTH = 5;
    private static final double MOVE_CHANCE = 0.25;

    public Tortoise(World world){
        this();
        setWorld(world);
    }
    public Tortoise(){
        super(COLOR, CHARACTER, INITIATIVE, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN);
    }


    @Override
    public void action() {
        if(World.randomiser.chance(MOVE_CHANCE)) {
        DirectionInterface k = board.createDirection();
        k.randomise();
            moveTo(new Point(pos.x + k.getDx(), pos.y + k.getDy()));
        }
        breedCooldownDown();
    }

    @Override
    public void collision(Organism attacker) {
        if(attacker.getStrength() > MINIMUM_ATTACKER_STRENGTH)
            fight(attacker);
        else{
            System.out.println("\tCOLLISION\tTortoise blocks the attack of " + attacker.getSpeciesName());
        }
    }
}