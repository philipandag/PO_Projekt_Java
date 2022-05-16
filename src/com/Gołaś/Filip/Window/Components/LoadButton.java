package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.OrganismList;
import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Organisms.Animals.Antelope;
import com.Gołaś.Filip.Organisms.Organism;
import com.Gołaś.Filip.Window.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class LoadButton extends JButton {
    GameWindow window;
    String saveFilePath;
    LoadButton(GameWindow window, String saveFilePath){
        super("Wczytaj");
        this.window = window;
        this.saveFilePath = saveFilePath;
        addActionListener((ActionEvent e) -> {
            try {
                FileInputStream fileIn = new FileInputStream(saveFilePath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                try{
                    window.getWorld().removeKeyEventDispatcher();
                    World world = (World) in.readObject();
                    world.getHumanInputListeners().clear();
                    world.addKeyEventDispatcher();
                    world.repairRefsInBoard();
                    world.repairRefsInOrganisms();
                    world.setWindow(window);
                    window.setWorld(world);
                    window.setBoard(world.getBoard());
                }catch(IOException exception)
                {
                    System.out.println(exception.getMessage());
                }

                in.close();
                fileIn.close();
                System.out.println("# Wczytano Gre!");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });
    }
}
