package com.Gołaś.Filip.Organisms.Animals;

import com.Gołaś.Filip.Game.Direction;
import com.Gołaś.Filip.Listeners.HumanInputListener;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class Human extends Animal {
    private static final int BREEDING_COOLDOWN = 3;
    private static final int STRENGTH = 5;
    private static final int INITIATIVE = 40;
    public static final String NAME = "Czlowiek";
    private static final String CHARACTER = "C";
    private static final Color COLOR = new Color(150, 50, 255);
    private static final int MAX_SKILL_COOLDOWN = 5;
    private final Direction direction = new Direction();
    private int skillCooldown;
    private int bonusStrength;

    public Human(World world){
        this();
        setWorld(world);
    }

    public Human(){
        super(COLOR, CHARACTER, NAME, INITIATIVE, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN);
        skillCooldown = 0;
        bonusStrength = 0;
    }

    @Override
    public void action(){
        moveTo(new Point(pos.x + direction.getDx(), pos.y + direction.getDy()));
        breedCooldownDown();
        skillCooldownDown();
    }

    @Override
    public Organism clone() {
        return new Human();
    }



    @Override
    public Organism setWorld(World w) {
        super.setWorld(w);

        w.addHumanDirectionListener((KeyEvent keyEvent) -> {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_DOWN -> {
                    setDirectionValue(Direction.Value.S);
                }
                case KeyEvent.VK_LEFT -> {
                    setDirectionValue(Direction.Value.W);
                }
                case KeyEvent.VK_RIGHT -> {
                    setDirectionValue(Direction.Value.E);
                }
                case KeyEvent.VK_UP -> {
                    setDirectionValue(Direction.Value.N);
                }
                case KeyEvent.VK_SPACE -> {
                    if (getSkillCooldown() == 0) {
                        useSkill();
                        System.out.println("\tUMIEJETNOSC\tCzlowiek uzywa magicznej mikstury!");
                    } else {
                        System.out.println("\tUMIEJETNOSC\tCzlowiek nie moze uzyc mikstury jeszcze przez " + skillCooldown + " tur");
                    }
                }
            }
        });
        return this;
    }

    private void useSkill(){
        bonusStrength = 5;
        skillCooldown = MAX_SKILL_COOLDOWN;
    }

    private void skillCooldownDown(){
        if(skillCooldown > 0){
            skillCooldown--;
            bonusStrength--;
        }
    }

    @Override
    public int getStrength(){
        return strength + bonusStrength;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirectionValue(Direction.Value d){
        this.direction.set(d);
    }

    public int getSkillCooldown() {
        return skillCooldown;
    }

}
