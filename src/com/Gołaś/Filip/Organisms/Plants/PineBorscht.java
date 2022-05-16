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
        super(COLOR, CHARACTER, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN, BREED_CHANCE);
    }

    public PineBorscht(World world){
        this();
        setWorld(world);
    }


    @Override
    public void action() {
        Point point = new Point();
        Direction d = new Direction(Direction.Value.N);
        for(int i = 0; i < Direction.SIZE; i++)
        {
            point.setLocation(pos.x + d.getDx(), pos.y + d.getDy());
            if(board.onBoard(point) && !board.at(point).empty() && !(board.at(point).organism instanceof PineBorscht) && !(board.at(point).organism instanceof CyberSheep)){
                board.at(point).organism.kill();
            }
            d.next();
        }
        super.action();
    }
}
