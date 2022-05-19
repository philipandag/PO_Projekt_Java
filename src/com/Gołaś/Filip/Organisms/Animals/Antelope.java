package com.Gołaś.Filip.Organisms.Animals;

import com.Gołaś.Filip.Game.Direction;
import com.Gołaś.Filip.Game.DirectionInterface;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class Antelope extends Animal {
    private static final int BREEDING_COOLDOWN = 3;
    private static final int STRENGTH = 4;
    private static final int INITIATIVE = 4;
    public static final String NAME = "Antelope";
    private static final String CHARACTER = "A";
    private static final Color COLOR = new Color(180, 180, 80);
    private static final double ESCAPE_CHANCE = 0.5;

    public Antelope(){
        super(COLOR, CHARACTER, INITIATIVE, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN);
    }
    public Antelope(World world){
       this();
       setWorld(world);
    }
    @Override
    public void action() {
        DirectionInterface k = board.createDirection();
        k.randomise();
        moveTo(new Point(pos.x + 2 * k.getDx(), pos.y + 2 * k.getDy()));
        breedCooldownDown();
    }

    @Override
    public void collision(Organism attacker) {
        if(World.randomiser.chance(ESCAPE_CHANCE) && escape())
            System.out.println("\tCOLLISION\tAntelope escapes from " + attacker.getSpeciesName());
        else {
            super.collision(attacker);
        }
    }

    protected boolean escape(){
        DirectionInterface d = board.createDirection();
        d.randomise();
        Point p = new Point();
        for (int i = 0; i < d.getSize(); i++) {
            p.setLocation(pos.x + d.getDx(), pos.y + d.getDy());
            if (board.onBoard(p)) {
                moveTo(p);
                return true;
            }
        }
        return false;
    }
}