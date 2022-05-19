package com.Gołaś.Filip.Organisms.Plants;

import com.Gołaś.Filip.Game.Direction;
import com.Gołaś.Filip.Game.DirectionInterface;
import com.Gołaś.Filip.Organisms.Animals.CyberSheep;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class PineBorscht extends Plant{
    private static final int BREEDING_COOLDOWN = 3;
    private static final double BREED_CHANCE = 0.1;
    private static final int STRENGTH = 10;
    private static final String CHARACTER = "P";
    private static final Color COLOR = new Color(255, 255, 0);

    public PineBorscht(){
        super(COLOR, CHARACTER, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN, BREED_CHANCE);
    }

    public PineBorscht(World world){
        this();
        setWorld(world);
    }


    @Override
    public void action() {
        Point point = new Point();
        DirectionInterface d = board.createDirection();
        for(int i = 0; i < d.getSize(); i++)
        {
            point.setLocation(pos.x + d.getDx(), pos.y + d.getDy());
            if(board.onBoard(point) && !board.at(point).empty() && !(board.at(point).getOrganism() instanceof PineBorscht) && !(board.at(point).getOrganism() instanceof CyberSheep)){
                board.at(point).getOrganism().kill();
            }
            d.next();
        }
        super.action();
    }
}
