package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.OrganismList;
import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
                BufferedWriter fw = new BufferedWriter(new FileWriter(saveFilePath));
                OrganismList organisms = world.getOrganisms();
                for(Organism o : organisms){
                    fw.write(o.save());
                }
                fw.flush();
                fw.close();
                System.out.println("# Zapisano gre!");
            }catch (java.io.IOException exception){
                System.out.printf(exception.getMessage());
            }
        });
    }


}
