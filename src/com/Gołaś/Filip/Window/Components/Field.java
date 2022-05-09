package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Organisms.Organism;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class Field extends JButton{
    public Organism organism;
    private World world;
    private boolean empty;
    private Point pos;
    private Color EMPTY_COLOUR;
    private Dimension preferredSize = new Dimension(32, 32);

    public Field(Color background, World world, Point pos) {
        this.pos = pos;
        this.world = world;
        this.organism = null;
        this.empty = true;
        this.EMPTY_COLOUR = background;
        setBackground(EMPTY_COLOUR);
        setBorder(new EmptyBorder(0, 0, 0, 0));

        addMouseListener(new MouseAdapter() {
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
        setFont(new Font(font.getName(), Font.PLAIN, Math.max(font.getSize(), dimension.height)));
    }
}
