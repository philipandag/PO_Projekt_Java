package com.Gołaś.Filip.Organisms;

import com.Gołaś.Filip.Game.Randomiser;
import com.Gołaś.Filip.Window.Components.AbstractBoard;
import com.Gołaś.Filip.Window.Components.Board;
import com.Gołaś.Filip.Game.Direction;
import com.Gołaś.Filip.Game.World;

import java.awt.*;
import java.util.Random;

public abstract class Organism implements Comparable<Organism>, java.io.Serializable{
    protected World world;
    protected AbstractBoard board;
    protected Color color;
    protected int initiative;
    protected int strength;
    protected String character;
    protected String speciesName;
    protected Point pos;
    protected int breedCooldown;
    protected int maxBreedCooldown;
    protected boolean alive;
    protected static final Randomiser randomiser = new Randomiser();

    public Organism(Color color, String character, String name, int initiative, int strength, int breedCooldown, int maxBreedCooldown){
        this.character = character;
        this.speciesName = name;
        this.color = color;
        this.initiative = initiative;
        this.strength = strength;
        this.breedCooldown = breedCooldown;
        this.maxBreedCooldown = maxBreedCooldown;
        this.alive = true;
    }

    public Organism(Color color, String character, String name, int initiative, int strength, int breedCooldown, int maxBreedCooldown,  World world){
        this(color, character, name, initiative, strength, breedCooldown, maxBreedCooldown);
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
        Direction k = new Direction();
        k.randomise();
        moveTo(new Point(pos.x + k.getDx(), pos.y + k.getDy()));
        breedCooldownDown();
    }

    public void fight(Organism attacker){
        //System.out.println("Fight " + attacker.getSpeciesName() + "/" + attacker.getStrength() + " atakuje " + getSpeciesName() + "/" + getStrength());
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
        System.out.println("\tKOLIZJA\t" + attacker.getSpeciesName() + " natrafia na " + speciesName);
    }

    public abstract Organism clone();

    public void moveTo(Point newPos){
        if (board.onBoard(newPos)) {
            System.out.println("\tRUCH\t" + speciesName + " idzie z (" + pos.x + ", " + pos.y + ") do (" + newPos.x + ", " + newPos.y + ")");
            if(board.at(newPos).empty()) {
                board.at(pos).setField(null);
                board.at(newPos).setField(this);
                pos = newPos;
            } else {
                board.at(newPos).organism.collision(this);
            }
        }
    }

    public void setAt(Point newPos){
        if (board.onBoard(newPos)) {
            if(!board.at(newPos).empty())
                world.deleteOrganism(board.at(newPos).organism);
            board.at(newPos).setField(this);
            pos = newPos;
        }
    }

    public boolean forceReproduce(){
        Direction direction = new Direction();
        direction.randomise();
        Point p = new Point();
        for (int i = 0; i < Direction.SIZE; i++) {
            p.setLocation(pos.x + direction.getDx(), pos.y + direction.getDy());
            if (board.onBoard(p) && board.at(p).empty()) {
                Organism child = clone();
                child.setPos(p);
                System.out.println("\tROZMNAZANIE\tRodzi sie " + child.getSpeciesName() + " na (" + p.x + ", " + p.y + ")");
                world.addOrganism(child);
                return true;
            }
            direction = direction.next();
        }
        //System.out.println("\tROZMNAZANIE\t" + getSpeciesName() + " nie ma miejsca by przyjsc na swiat w poblizu (" + p.x + ", " + p.y + ")");
        return false;
    }

    public void breed() {
        if(readyToBreed() && forceReproduce())
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
        return speciesName;
    }

    public void kill(){
        System.out.println("\tSMIERC\t" + speciesName + " umiera!");
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
}
