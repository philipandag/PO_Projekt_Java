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
import java.util.Random;

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
        for(Field row[] : board.getGrid()){
            for(Field f : row){
                f.setWorld(this);
                f.addListener();
            }
        }
    }

    public void fill(int times) {
        Class<? extends Organism>[] allOrgs = new Class[]{Antelope.class, CyberSheep.class, Fox.class, Human.class, Sheep.class, Tortoise.class, Wolf.class, Belladonna.class, Dandelion.class, Grass.class, Guarana.class, PineBorscht.class};
        Random r = new Random();
        for(Class c : allOrgs){
            for(int i = 0; i < times; i++) {
                while (true) {
                    Point p = new Point(r.nextInt(0, getBoard().getSize().width), r.nextInt(0, getBoard().getSize().height));
                    if (board.getGrid()[p.x][p.y].empty()) {
                        try {
                            Organism o = (Organism) c.getConstructor(World.class).newInstance(this);
                            o.setPos(p);
                            addOrganism(o);
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
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
        Window = window;
        if(type == BoardType.NORMAL) {
            board = new Board(size, this);
            Direction.setMode(Direction.Mode.NORMAL);
        }
        else {
            board = new HexBoard(size, this);
            Direction.setMode(Direction.Mode.HEX);
        }
        System.out.println("# Poczatek gry");
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

    public Field[][] getBoardGrid(){
        return board.getGrid();
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
        return this;
    }

    public void nextTurn() {
        System.out.println("# Nastala tura " + turnNumber);

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
    public void anihilate(){
        //organisms = null;
        //toDelete = null;
        //humanInputListeners = null;
        //board = null;
        //Window = null;
        removeKeyEventDispatcher();
        keyEventDispatcher = null;
    }
}
