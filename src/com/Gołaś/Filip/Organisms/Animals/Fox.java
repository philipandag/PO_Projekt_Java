package com.Gołaś.Filip.Organisms.Animals;

import com.Gołaś.Filip.Game.Direction;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class Fox extends Animal {
    private static final int BREEDING_COOLDOWN = 3;
    private static final int STRENGTH = 3;
    private static final int INITIATIVE = 7;
    private static final String CHARACTER = "L";
    private static final Color COLOR = new Color(120, 50, 20);

    public Fox(World world){
        this();
        setWorld(world);
    }
    public Fox(){
        super(COLOR, CHARACTER, INITIATIVE, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN);
    }

    public static Fox clone(World w) { return new Fox(w);}

    @Override
    public void action() {
        Direction k = new Direction();
        k.randomise();
        Point p = new Point();
        for(int i = 0; i < Direction.SIZE; i++){
            p.setLocation(pos.x + k.getDx(), pos.y + k.getDy());
            if(world.getBoard().onBoard(p))
            {
                if(world.getBoard().at(p).empty() || world.getBoard().at(p).organism.getStrength() <= strength)
                {
                    moveTo(new Point(pos.x + k.getDx(), pos.y + k.getDy()));
                    break;
                }
            }
            k = k.next();
        }
        breedCooldownDown();
    }
}