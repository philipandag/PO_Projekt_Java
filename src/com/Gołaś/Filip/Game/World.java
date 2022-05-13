package com.Gołaś.Filip.Game;

import com.Gołaś.Filip.Listeners.HumanInputListener;
import com.Gołaś.Filip.Listeners.KeyboardListener;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Window.Components.Board;
import com.Gołaś.Filip.Window.Components.Field;
import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.KeyEvent;
import java.util.ListIterator;

public class World {

    protected OrganismList organisms;
    protected List<Organism> toDelete = new ArrayList<Organism>();
    protected List<HumanInputListener> humanInputListeners;
    List<KeyboardListener> keyboardListeners;
    protected int turnNumber = 1;
    protected Board board;
    protected GameWindow Window;

    public World(Dimension size, GameWindow window){
        humanInputListeners = new ArrayList<HumanInputListener>();
        organisms = new OrganismList();
        board = new Board(size, this);
        Window = window;
        System.out.println("# Poczatek gry");

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher((KeyEvent ke) -> {
            synchronized (Window) {
                if(ke.getID() == KeyEvent.KEY_PRESSED)
                    for(HumanInputListener listener : humanInputListeners) {
                        listener.execute(ke);
                    }
            }
            return true;
        });
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public GameWindow getWindow(){
        return this.Window;
    }

    public Board getBoard() {
        return board;
    }

    public Field[][] getBoardGrid(){
        return board.getGrid();
    }

    public World addOrganism(Organism o){
        o.setWorld(this);
        if(board.at(o.getPos()).empty()) {
            //System.out.println("Dodano organizm " + o.getSpeciesName());
            organisms.add(o);
            board.at(o.getPos()).setField(o);
        }else
        {
            //System.out.println("\tKOLIZJA " + o.getSpeciesName() + " (" + o.getPos().x + " , " + o.getPos().y + ") To miejsce jest juz zajete przez " + board.at(o.getPos()).organism.getSpeciesName());
        }

        return this;
    }

    public World deleteOrganism(Organism o){
        if(board.at(o.getPos()).organism == o)
            board.at(o.getPos()).clearField();
        organisms.remove(o);
        return this;
    }

    public void nextTurn() {
        System.out.println("# Nastala tura " + turnNumber);

        for(int i = 0; i < organisms.size(); i++) {
            Organism o = organisms.get(i);
            if (o.isAlive())
                o.action();
            else {
                toDelete.add(o);
            }
        }

        for(Organism o : organisms) {
            if (!o.isAlive()){
                toDelete.add(o);
            }
        }

        for(Organism o : toDelete){
            if(board.at(o.getPos()).organism == o) {
                board.at(o.getPos()).clearField();
            }
        }

        organisms.removeAll(toDelete);
        toDelete.clear();
        turnNumber++;
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
    public void reset(){
        turnNumber = 1;
        for(Organism o : organisms){
            board.at(o.getPos()).clearField();
        }
        organisms.clear();
    }
}
