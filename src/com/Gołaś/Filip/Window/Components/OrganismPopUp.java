package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Organisms.Animals.*;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Organisms.Plants.*;
import com.Gołaś.Filip.Game.World;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class OrganismPopUp extends JPopupMenu {
    enum Mode {
        ADD,
        REMOVE
    }

    Mode mode;
    Field field;
    JFrame window;
    World world;

    private interface ItemAdder{
        public void addItem();
    }

    private JMenu createMenu(Class<? extends Organism>[] classes, String name){
        JMenu menu = new JMenu(name);
        for(Class<? extends Organism> c : classes){
            addOrganismItem(c, menu);
        }
        return menu;
    }

    private <T extends Organism> void addOrganismItem(Class<T> c, JMenu menu){
        JMenuItem item = new JMenuItem(c.getSimpleName());
        addListener(item, () -> {
            try {
                Organism clone = c.getConstructor().newInstance();
                clone.setPos(field.getPos());
                System.out.println("\t# Placed " + clone.getSpeciesName() + " on (" + field.getPos().x + ", " + field.getPos().y + ")");
                world.addOrganism(clone);
                field.setField(clone);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        });
        menu.add(item);
    }

    private void addListener(JMenuItem item, ItemAdder adder){
        item.addActionListener((ActionEvent e) -> adder.addItem());
    }

    public OrganismPopUp(MouseEvent e, Mode mode) {
        this.mode = mode;
        field = (Field) e.getComponent();
        world = ((Field) e.getComponent()).getWorld();
        JMenuItem item;
        switch (mode){
            case ADD -> {
                Class<? extends Animal>[] animals = new Class[]{Antelope.class, CyberSheep.class, Human.class, Sheep.class, Tortoise.class, Wolf.class, Fox.class};
                Class<? extends Plant>[] plants = new Class[]{Belladonna.class, Dandelion.class, Grass.class, Guarana.class, PineBorscht.class};

                JMenu animalsMenu = createMenu(animals, "Animals");
                JMenu plantsMenu = createMenu(plants, "Plants");

                add(animalsMenu);
                add(plantsMenu);
            }
            case REMOVE -> {
                item = new JMenuItem("Usun");
                addListener(item, () -> {
                    System.out.println("\t# Removed " + field.organism.getSpeciesName() + " from (" + field.getPos().x + ", " + field.getPos().y + ")");
                    world.deleteOrganism(field.organism);
                    field.clearField();
                });
                add(item);
            }
        }
        show(e.getComponent(), e.getX(), e.getY());
    }
}

