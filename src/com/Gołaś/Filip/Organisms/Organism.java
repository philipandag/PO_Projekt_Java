package com.Gołaś.Filip.Organisms;

import com.Gołaś.Filip.Game.Direction;
import com.Gołaś.Filip.Game.DirectionInterface;
import com.Gołaś.Filip.Window.Components.AbstractBoard;
import com.Gołaś.Filip.Game.World;

import java.awt.*;
import java.lang.reflect.Constructor;

public abstract class Organism implements Comparable<Organism>, java.io.Serializable{
    public static final int ORGANISM_CLASSES = 12;
    protected World world;
    protected AbstractBoard board;
    protected Color color;
    protected int initiative;
    protected int strength;
    protected String character;
    protected Point pos;
    protected int breedCooldown;
    protected int maxBreedCooldown;
    protected boolean alive;

    protected Organism(Color color, String character, int initiative, int strength, int breedCooldown, int maxBreedCooldown){
        this.character = character;
        this.color = color;
        this.initiative = initiative;
        this.strength = strength;
        this.breedCooldown = breedCooldown;
        this.maxBreedCooldown = maxBreedCooldown;
        this.alive = true;
    }

    protected Organism(Color color, String character, int initiative, int strength, int breedCooldown, int maxBreedCooldown,  World world){
        this(color, character, initiative, strength, breedCooldown, maxBreedCooldown);
        this.world = world;
    }

    public Organism setWorld(World w){
        this.world = w;
        this.board = w.getBoard();
        return this;
    }

    @Override
    public int compareTo(Organism o) {
        return initiative - o.initiative;
    }
    
    public Color getColor(){
        return color;
    }
    public String getCharacter(){
        return character;
    }
    public Integer getInitiative(){
        return initiative;
    }

    public void action(){
        DirectionInterface k = board.createDirection();
        k.randomise();
        moveTo(new Point(pos.x + k.getDx(), pos.y + k.getDy()));
        breedCooldownDown();
    }

    public void fight(Organism attacker){
        if(getStrength() <= attacker.getStrength()){
            kill();
            attacker.moveTo(this.pos);
        }
        else
        {
            attacker.kill();
        }
    }

    public void collision(Organism attacker){
        System.out.println("\tCOLLISION\t" + attacker.getSpeciesName() + " stumbles upon " + getName());
    }

    public void moveTo(Point newPos){
        if (board.onBoard(newPos)) {
            System.out.println("\tMOVEMENT\t" + getName() + " goes from (" + pos.x + ", " + pos.y + ") to (" + newPos.x + ", " + newPos.y + ")");
            if(board.at(newPos).empty()) {
                board.at(pos).setField(null);
                board.at(newPos).setField(this);
                pos = newPos;
            } else {
                board.at(newPos).getOrganism().collision(this);
            }
        }
    }

    public boolean reproduce(){
        DirectionInterface direction = board.createDirection();
        direction.randomise();
        Point p = new Point();
        for (int i = 0; i < direction.getSize(); i++) {
            p.setLocation(pos.x + direction.getDx(), pos.y + direction.getDy());
            if (board.onBoard(p) && board.at(p).empty()) {
                Organism child = clone();
                child.setPos(p);
                System.out.println("\tBREEDING\t" + child.getSpeciesName() + " was born on (" + p.x + ", " + p.y + ")");
                world.addOrganism(child);
                return true;
            }
            direction = direction.next();
        }
        return false;
    }

    public Organism clone(){
        Organism child = null;
        try {
            Constructor<? extends Organism> constructor = this.getClass().getConstructor(World.class);
            child = constructor.newInstance(this.world);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return child;
    }

    public void breed() {
        if(readyToBreed() && reproduce())
            resetBreedCooldown();
    }


    public Point getPos() {
        return pos;
    }

    public void setPos(Point p){
        pos = p;
    }

    public boolean readyToBreed(){
        return breedCooldown == 0;
    }

    public int getStrength() {
        return strength;
    }

    public String getSpeciesName() {
        return getName();
    }

    public void kill(){
        System.out.println("\tDEATH\t" + getName() + " dies!");
        board.at(this.pos).clearField();
        this.alive = false;
    }

    public boolean isAlive(){
        return alive;
    }

    public void breedCooldownDown(){
        if(breedCooldown > 0)
            breedCooldown--;
    }

    public void resetBreedCooldown(){
        breedCooldown = maxBreedCooldown;
    }

    public void changeStrength(int delta){
        strength += delta;
    }

    public void setBoard(AbstractBoard board) {
        this.board = board;
    }

    public String getName(){
        return this.getClass().getSimpleName();
    }
}
