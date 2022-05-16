package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Organisms.Organism;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public abstract class AbstractField extends JButton implements Serializable {
    public Organism organism;
    protected World world;
    protected boolean empty;
    protected Point pos;
    protected Color EMPTY_COLOUR;
    protected Dimension preferredSize = new Dimension(32, 32);

    public AbstractField(Color background, World world, Point pos) {
        this.pos = pos;
        this.world = world;
        this.organism = null;
        this.empty = true;
        this.EMPTY_COLOUR = background;
        setBackground(EMPTY_COLOUR);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        addListener();
    }
    public void addListener(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(((AbstractField)e.getComponent()).empty())
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
    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        setSize(preferredSize);
    }

    public void clearField(){
        organism = null;
        empty = true;
        setBackground(EMPTY_COLOUR);
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

    public void updateFont(Dimension dimension){
        Font font = getFont();
        setFont(new Font(font.getName(), Font.PLAIN, dimension.height));
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }

    public void setWorld(World world) {
        this.world = world;
    }

}
