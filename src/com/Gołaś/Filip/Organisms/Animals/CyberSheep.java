package com.Gołaś.Filip.Organisms.Animals;

import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;

public class CyberSheep extends Animal {
    private static final int BREEDING_COOLDOWN = 3;
    private static final int STRENGTH = 11;
    private static final int INITIATIVE = 4;
    private static final String CHARACTER = "#";
    private static final Color COLOR = new Color(50, 50, 50);

    public CyberSheep(){
        super(COLOR, CHARACTER, INITIATIVE, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN);
    }
    public CyberSheep(World world){
        this();
        setWorld(world);
    }

}