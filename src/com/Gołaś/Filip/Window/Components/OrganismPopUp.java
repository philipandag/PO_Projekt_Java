package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.OrganismList;
import com.Gołaś.Filip.Organisms.Animals.*;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Organisms.Plants.*;
import com.Gołaś.Filip.Game.World;

import javax.management.MBeanRegistration;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class OrganismPopUp extends JPopupMenu {
    enum Mode {
        ADD,
        REMOVE
    }

    Mode mode;
    AbstractField field;
    JFrame window;
    World world;

    private interface itemAdder{
        public void addItem();
    }

    private void addOrganismItem(Organism o, JMenu menu){
        JMenuItem item = new JMenuItem(o.getSpeciesName());
        addListener(item, () -> {
            Organism clone = o.clone();
            clone.setPos(field.getPos());
            System.out.println("\t# Postawiono " + o.getSpeciesName() + " na (" + field.getPos().x + ", " + field.getPos().y + ")");
            world.addOrganism(clone);
            field.setField(clone);
        });
        menu.add(item);
    }

    private void addListener(JMenuItem item, itemAdder adder){
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adder.addItem();
            }
        });
    }

    public OrganismPopUp(MouseEvent e, Mode mode) {
        this.mode = mode;
        field = (AbstractField) e.getComponent();
        world = ((AbstractField) e.getComponent()).getWorld();
        JMenuItem item;
        switch (mode){
            case ADD -> {
                Organism[] animals = new Organism[]{new Antelope(), new CyberSheep(), new Human(), new Sheep(), new Tortoise(), new Wolf(), new Fox()};
                Organism[] plants = new Organism[]{new Belladonna(), new Dandelion(), new Grass(), new Guarana(), new PineBorscht()};

                JMenu animalsMenu = new JMenu("Zwierzeta");
                for(Organism o : animals){
                    addOrganismItem(o, animalsMenu);
                }
                add(animalsMenu);

                JMenu plantsMenu = new JMenu("Rosliny");
                for(Organism o : plants){
                    addOrganismItem(o, plantsMenu);
                }
                add(plantsMenu);
            }
            case REMOVE -> {
                item = new JMenuItem("Usun");
                addListener(item, () -> {
                    System.out.println("\t# Usunieto " + field.organism.getSpeciesName() + " z (" + field.getPos().x + ", " + field.getPos().y + ")");
                    world.getOrganisms().remove(field.organism);
                    field.clearField();
                });
                add(item);
            }
        }

        show(e.getComponent(), e.getX(), e.getY());
    }


    public void setPole(Field field){
        this.field = field;
    }

}

