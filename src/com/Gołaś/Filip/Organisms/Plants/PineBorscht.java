package com.Gołaś.Filip.Organisms.Plants;

import com.Gołaś.Filip.Game.Direction;
import com.Gołaś.Filip.Organisms.Animals.CyberSheep;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class PineBorscht extends Plant{
    private static final int BREEDING_COOLDOWN = 3;
    private static final double BREED_CHANCE = 0.1;
    private static final int STRENGTH = 10;
    public static final String NAME = "BarszczSosnowskiego";
    private static final String CHARACTER = "B";
    private static final Color COLOR = new Color(255, 255, 0);

    public PineBorscht(){
        super(COLOR, CHARACTER, NAME, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN, BREED_CHANCE);
    }

    public PineBorscht(World world){
        this();
        setWorld(world);
    }

    @Override
    public Organism clone() {
        return new PineBorscht();
    }

    @Override
    public void action() {
        Point point = new Point();
        for(Direction d : Direction.values())
        {
            point.setLocation(pos.x + d.getDx(), pos.y + d.getDy());
            if(board.onBoard(point) && !board.at(point).empty() && !(board.at(point).organism instanceof PineBorscht) && !(board.at(point).organism instanceof CyberSheep)){
                board.at(point).organism.kill();
            }
        }
        super.action();
    }
}
