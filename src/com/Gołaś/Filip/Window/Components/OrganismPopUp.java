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
import java.util.ArrayList;

public class OrganismPopUp extends JPopupMenu {
    enum Mode {
        ADD,
        REMOVE
    }

    Mode mode;
    Field field;
    JFrame window;
    World world;

    private interface itemAdder{
        public void addItem();
    }

    private void addOrganismItem(Organism o, JMenu menu){
        JMenuItem item = new JMenuItem(o.getSpeciesName());
        addListener(item, () -> {
            Organism clone = o.clone();
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
        field = (Field) e.getComponent();
        world = ((Field) e.getComponent()).getWorld();
        JMenuItem item;
        switch (mode){
            case ADD -> {
                Organism[] animals = new Organism[]{new Antelope(world), new CyberSheep(world), new Human(world), new Sheep(world), new Tortoise(world), new Wolf(world)};
                Organism[] plants = new Organism[]{new Belladonna(world), new Dandelion(world), new Grass(world), new Guarana(world), new PineBorscht(world)};

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

