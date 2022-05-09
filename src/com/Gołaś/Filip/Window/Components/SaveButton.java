package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveButton extends JButton {
    World world;
    GameWindow gameWindow;
    SaveButton(World world){
        super("Zapisz");
        this.world = world;
        this.gameWindow = world.getWindow();
        addActionListener((ActionEvent e) -> {
            File file;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Zapisz grę");
            fileChooser.setAcceptAllFileFilterUsed(false);
            int userSelection = fileChooser.showSaveDialog(gameWindow);


            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                try {
                    FileWriter fileWriter = new FileWriter(fileToSave);
                    for(Organism o : world.getOrganisms()){
                        fileWriter.write(o.save());
                    }
                }catch(IOException exception){
                    System.out.println(exception.getMessage());
                }
            }
        });
    }


}
