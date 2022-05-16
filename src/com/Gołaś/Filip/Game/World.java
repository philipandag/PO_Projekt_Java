package com.Gołaś.Filip.Game;

import com.Gołaś.Filip.Listeners.HumanInputListener;
import com.Gołaś.Filip.Listeners.KeyboardListener;
import com.Gołaś.Filip.Organisms.Animals.Human;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Window.Components.*;
import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.KeyEvent;
import java.util.ListIterator;

public class World implements Serializable {

    protected OrganismList organisms;
    protected List<Organism> toDelete = new ArrayList<Organism>();
    protected List<HumanInputListener> humanInputListeners;
    protected int turnNumber = 1;
    protected AbstractBoard board;
    protected transient GameWindow Window;

    protected KeyEventDispatcher keyEventDispatcher = (Serializable & KeyEventDispatcher)(KeyEvent ke) -> {
        synchronized (Window) {
            if(ke.getID() == KeyEvent.KEY_PRESSED)
                for(HumanInputListener listener : humanInputListeners) {
                    listener.execute(ke);
                }
        }
        return true;
    };

    public void repairRefsInOrganisms() {
        for(Organism o : organisms){
            o.setWorld(this);
            o.setBoard(getBoard());
        }
    }

    public void repairRefsInBoard() {
        for(AbstractField row[] : board.getGrid()){
            for(AbstractField f : row){
                f.setWorld(this);
                f.addListener();
            }
        }
    }

    public enum BoardType{
        NORMAL,
        HEX,
    }

    public World(Dimension size, GameWindow window, BoardType type){
        humanInputListeners = new ArrayList<HumanInputListener>();
        organisms = new OrganismList();
        if(type == BoardType.NORMAL) {
            board = new Board(size, this);
            Direction.setMode(Direction.Mode.NORMAL);
        }
        else {
            board = new HexBoard(size, this);
            Direction.setMode(Direction.Mode.HEX);
        }

        Window = window;
        System.out.println("# Poczatek gry");

        addKeyEventDispatcher();
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public GameWindow getWindow(){
        return this.Window;
    }

    public AbstractBoard getBoard() {
        return board;
    }

    public AbstractField[][] getBoardGrid(){
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

    public void removeHumanDirectionListener(HumanInputListener listener)
    {
        humanInputListeners.remove(listener);
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

    public List<HumanInputListener> getHumanInputListeners() {
        return humanInputListeners;
    }
}
