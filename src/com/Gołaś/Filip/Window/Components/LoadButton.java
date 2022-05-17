package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.OrganismList;
import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Organisms.Animals.Antelope;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class LoadButton extends JButton {
    GameWindow window;
    String saveFilePath;
    LoadButton(GameWindow window, String saveFilePath){
        super("Load");
        this.window = window;
        this.saveFilePath = saveFilePath;
        addActionListener((ActionEvent e) -> {
            try {
                FileInputStream fileIn = new FileInputStream(saveFilePath);
                ObjectInputStream in = new ObjectInputStream(fileIn);

                World world = (World) in.readObject();
                window.setWorld(world);

                String consoleContent = (String) in.readObject();
                window.getConsoleWindow().setText(consoleContent);

                in.close();
                fileIn.close();
                System.out.println("# Game loaded!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
    }
}
