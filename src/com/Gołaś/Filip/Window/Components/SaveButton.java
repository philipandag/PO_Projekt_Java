package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.OrganismList;
import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class SaveButton extends JButton {
    GameWindow gameWindow;
    String saveFilePath;
    SaveButton(GameWindow window, String saveFilePath){
        super("Zapisz");
        this.saveFilePath = saveFilePath;
        this.gameWindow = window;
        addActionListener((ActionEvent e) -> {
            try {
                new FileOutputStream(saveFilePath).close();
                FileOutputStream fos = new FileOutputStream(saveFilePath);
                ObjectOutputStream out = new ObjectOutputStream(fos);
                OrganismList organisms = window.getWorld().getOrganisms();

                out.writeObject(window.getWorld());

                out.close();
                fos.close();
                System.out.println("# Zapisano gre!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }


}
