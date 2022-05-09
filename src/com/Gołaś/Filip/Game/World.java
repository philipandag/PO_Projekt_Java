package com.Gołaś.Filip.Game;

import com.Gołaś.Filip.Listeners.HumanInputListener;
import com.Gołaś.Filip.Listeners.KeyboardListener;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Window.Components.Board;
import com.Gołaś.Filip.Window.Components.Field;
import com.Gołaś.Filip.Window.GameWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.KeyEvent;

public class World {

    OrganismList organisms;
    List<HumanInputListener> humanInputListeners;
    List<KeyboardListener> keyboardListeners;
    int turnNumber = 1;
    private Board board;
    private GameWindow Window;

    public World(Dimension size, GameWindow window){
        humanInputListeners = new ArrayList<HumanInputListener>();
        organisms = new OrganismList();
        board = new Board(size, this);
        Window = window;
        System.out.println("# Poczatek gry");

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher((KeyEvent ke) -> {
            synchronized (Window) {
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
        organisms.add(o);
        return this;
    }

    public World deleteOrganism(Organism o){
        organisms.remove(o);
        return this;
    }

    public void nextTurn() {
        System.out.println("# Nastala tura " + turnNumber);
        List<Organism> toDelete = new ArrayList<Organism>();

        for(int i = 0; i < organisms.size(); i++) {
            Organism organism = organisms.get(i);
            if (organism.isAlive())
                organism.action();
            else {
                toDelete.add(organism);
            }
        }

        organisms.removeAll(toDelete);
        turnNumber++;
    }

    public void setWindow(GameWindow okno) {
        this.Window = okno;
    }

    public void addHumanDirectionListener(HumanInputListener listener)
    {
        humanInputListeners.add(listener);
    }

    public void addKeyBoardListener(KeyboardListener listener){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher((KeyEvent ke) -> {
            synchronized (getWindow()){
                listener.execute(ke);
            }
            return true;
        });
    }

    public OrganismList getOrganisms() {
        return organisms;
    }
}
