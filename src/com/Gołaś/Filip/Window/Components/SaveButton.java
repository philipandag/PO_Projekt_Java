package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.OrganismList;
import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class SaveButton extends JButton {
    GameWindow gameWindow;
    String saveFilePath;
    SaveButton(GameWindow window, String saveFilePath){
        super("Save");
        this.saveFilePath = saveFilePath;
        this.gameWindow = window;
        setFocusable(false);
        addActionListener((ActionEvent e) -> {
            try(FileOutputStream fos = new FileOutputStream(saveFilePath);
                ObjectOutputStream out = new ObjectOutputStream(fos)
            ) {
                out.writeObject(window.getWorld());
                out.writeObject(window.getConsoleWindow().getText());

                out.close();
                fos.close();
                System.out.println("# Game saved!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }


}
