package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.OrganismList;
import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.*;

public class SaveButton extends JButton {
    World world;
    GameWindow gameWindow;
    String saveFilePath;
    SaveButton(World world, String saveFilePath){
        super("Zapisz");
        this.saveFilePath = saveFilePath;
        this.world = world;
        this.gameWindow = world.getWindow();
        addActionListener((ActionEvent e) -> {
            try {
                FileOutputStream fos = new FileOutputStream(saveFilePath);
                ObjectOutputStream out = new ObjectOutputStream(fos);
                OrganismList organisms = world.getOrganisms();

                out.writeObject(world.getBoard());
                for(Organism o : organisms){
                    out.writeObject(o);
                }

                out.close();
                fos.close();
                System.out.println("# Zapisano gre!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }


}
