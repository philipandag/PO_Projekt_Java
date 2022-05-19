package com.Gołaś.Filip.Game;

import com.Gołaś.Filip.Listeners.HumanInputListener;
import com.Gołaś.Filip.Organisms.Animals.*;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Organisms.Plants.*;
import com.Gołaś.Filip.Window.Components.*;
import com.Gołaś.Filip.Window.GameWindow;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.KeyEvent;

public class World implements Serializable {
    private class SerializableList<T> extends ArrayList<T> implements Serializable{}
    protected OrganismList organisms = new OrganismList();
    protected SerializableList<Organism> toDelete = new SerializableList<>();
    protected SerializableList<HumanInputListener> humanInputListeners = new SerializableList<>();
    protected int turnNumber = 1;
    protected AbstractBoard board;
    protected transient GameWindow Window;
    public static final Randomiser randomiser = new Randomiser();

    protected KeyEventDispatcher keyEventDispatcher = (Serializable & KeyEventDispatcher)(KeyEvent ke) -> {
        synchronized (Window) {
            if(ke.getID() == KeyEvent.KEY_PRESSED)
                for(HumanInputListener listener : humanInputListeners) {
                    listener.execute(ke);
                }
        }
        return false;
    };

    public World(Dimension size, GameWindow window, Class<? extends AbstractBoard> boardClass){
        Window = window;

        try {
            board = boardClass.getConstructor(Dimension.class, World.class).newInstance(size, this);
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("# The game begins");
    }

    public void fill(int percentage) {
        Class<? extends Organism>[] allOrgsExceptHuman = new Class[]{Antelope.class, CyberSheep.class, Fox.class, Sheep.class, Tortoise.class, Wolf.class, Belladonna.class, Dandelion.class, Grass.class, Guarana.class, PineBorscht.class};
        int organismsToPlace = (int)(board.getSize().width * board.getSize().height * (percentage / 100.0));
        for(int i = 0; i < organismsToPlace; i++) {
            placeOrganismRandomly(allOrgsExceptHuman[i % allOrgsExceptHuman.length]);
        }
    }

    private boolean placeOrganismRandomly(Class<? extends Organism> c){
        Point p = new Point(randomiser.nextInt(0, getBoard().getSize().width), randomiser.nextInt(0, getBoard().getSize().height));
        int tries = 0;
        while (tries < getBoard().getSize().width * getBoard().getSize().height) {
            if (board.getGrid()[p.x][p.y].empty()) {
                try {
                    Organism o = c.getConstructor(World.class).newInstance(this);
                    o.setPos(p);
                    addOrganism(o);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                moveToNextField(p);
            }
        }
        return false;
    }

    private void moveToNextField(Point p){
        if(p.x == board.getSize().width-1) { // last field in this row
            if (p.y == board.getSize().height - 1) // last filed in the board
                p.setLocation(0, 0);
            else
                p.setLocation(0, p.y + 1);
        }
        else
            p.setLocation(p.x+1, p.y);
    }

    public GameWindow getWindow(){
        return this.Window;
    }

    public AbstractBoard getBoard() {
        return board;
    }

    public World addOrganism(Organism o){
        o.setWorld(this);
        if(board.at(o.getPos()).empty()) {
            organisms.add(o);
            board.at(o.getPos()).setField(o);
        }

        return this;
    }

    public World deleteOrganism(Organism o){
        if(board.at(o.getPos()).getOrganism() == o)
            board.at(o.getPos()).clearField();
        organisms.remove(o);
        if(o instanceof Human human){
            humanInputListeners.remove(human.getListener());
        }
        return this;
    }

    public void nextTurn() {
        System.out.println("# Turn " + turnNumber);

        for(int i = 0; i < organisms.size(); i++) {
            Organism o = organisms.get(i);
            if (o.isAlive())
                o.action();
        }

        for(Organism o : organisms) {
            if (!o.isAlive()){
                toDelete.add(o);
            }
        }

        for(Organism o : toDelete){
            deleteOrganism(o);
        }

        toDelete.clear();
        turnNumber++;
        getWindow().repaint();
    }

    public void setWindow(GameWindow okno) {
        this.Window = okno;
    }

    public void addHumanDirectionListener(HumanInputListener listener)
    {
        humanInputListeners.add(listener);
    }

    public OrganismList getOrganisms() {
        return organisms;
    }

    public void removeKeyEventDispatcher(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(keyEventDispatcher);
    }
    public void addKeyEventDispatcher(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
    }

}
