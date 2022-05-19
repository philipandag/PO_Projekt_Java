package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Organisms.Organism;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class Field extends JButton implements Serializable {
    protected Organism organism;
    protected World world;
    protected boolean empty;
    protected Point pos;
    protected Color emptyColour;
    protected Dimension preferredSize = new Dimension(32, 32);

    public Field(Color background, World world, Point pos) {
        this.pos = pos;
        this.world = world;
        this.organism = null;
        this.empty = true;
        this.emptyColour = background;
        setFocusable(false);
        setBackground(emptyColour);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        addListener();
    }

    private class SerializableMouseAdapter extends MouseAdapter implements Serializable{};

    public void addListener(){
        addMouseListener(new SerializableMouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(((Field)e.getComponent()).empty())
                    new OrganismPopUp(e, OrganismPopUp.Mode.ADD);
                else
                    new OrganismPopUp(e, OrganismPopUp.Mode.REMOVE);
            }
        });
    }
    public World getWorld(){
        return world;
    }

    public void setField(Organism organism){
        if(organism == null){
            clearField();
            return;
        }
        this.organism = organism;
        this.organism.setPos(this.pos);
        empty = false;
        setBackground(organism.getColor());
        setText(organism.getCharacter());
        setForeground(Color.BLACK);
    }

    public void clearField(){
        organism = null;
        empty = true;
        setBackground(emptyColour);
        setText("");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(preferredSize.width, preferredSize.height);
    }

    public boolean empty(){
        return empty;
    }

    public Point getPos(){return pos;}

    public void updateFont(int height){
        Font font = getFont();
        setFont(new Font(font.getName(), font.getStyle(), height));
    }

    @Override
    public void setSize(int width, int height) {
        updateFont(height);
        super.setSize(width, height);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        updateFont(height);
        super.setBounds(x, y, width, height);
    }

    public void setWorld(World world) {
        this.world = world;
    }
    public Organism getOrganism() {
        return organism;
    }
    public void setOrganism(Organism o){
        organism = o;
    }

}
