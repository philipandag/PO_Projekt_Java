package com.Gołaś.Filip.Organisms.Animals;

import com.Gołaś.Filip.Game.Direction;
import com.Gołaś.Filip.Game.DirectionInterface;
import com.Gołaś.Filip.Listeners.HumanInputListener;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Game.World;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Human extends Animal {
    private static final int BREEDING_COOLDOWN = 3;
    private static final int STRENGTH = 5;
    private static final int INITIATIVE = 40;
    private static final String CHARACTER = "H";
    private static final Color COLOR = new Color(150, 50, 255);
    private static final int MAX_SKILL_COOLDOWN = 5;
    private DirectionInterface direction = null;
    private int skillCooldown;
    private int bonusStrength;
    private HumanInputListener listener;

    public Human(World world){
        this();
        setWorld(world);
    }

    public Human(){
        super(COLOR, CHARACTER, INITIATIVE, STRENGTH, BREEDING_COOLDOWN, BREEDING_COOLDOWN);
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
    public Organism setWorld(World w) {
        super.setWorld(w);
        direction = board.createDirection();
        listener = (KeyEvent keyEvent) -> {
            if(keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                return switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_DOWN , KeyEvent.VK_LEFT , KeyEvent.VK_RIGHT , KeyEvent.VK_UP-> {
                        setDirectionValue(keyEvent.getKeyCode());
                        yield true;
                    }
                    case KeyEvent.VK_SPACE -> {
                        if (getSkillCooldown() == 0) {
                            useSkill();
                            System.out.println("\tABILITY\tHuman uses the Magic Potion!");
                        } else {
                            System.out.println("\tABILITY\tHuman can't use the Magic Potion for " + skillCooldown + " more turns, strength: " + getStrength());
                        }
                        yield true;
                    }
                    default -> false;
                };
            }
            return false;
        };
        w.addHumanDirectionListener(listener);
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

    public DirectionInterface getDirection() {
        return direction;
    }

    public void setDirectionValue(int dir){
        direction.setByKey(dir);
    }

    public int getSkillCooldown() {
        return skillCooldown;
    }

    public HumanInputListener getListener(){
        return this.listener;
    }
}
