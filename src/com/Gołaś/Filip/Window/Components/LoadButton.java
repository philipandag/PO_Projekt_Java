package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.OrganismList;
import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Organisms.Animals.Antelope;
import com.Gołaś.Filip.Organisms.Organism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class LoadButton extends JButton {
    World world;
    String saveFilePath;
    String packageName = "com.Gołaś.Filip.Organisms";
    LoadButton(World world, String saveFilePath){
        super("Wczytaj");
        this.world = world;
        this.saveFilePath = saveFilePath;
        addActionListener((ActionEvent e) -> {
            try {
                FileInputStream fileIn = new FileInputStream(saveFilePath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                try{
                    world.getWindow().remove(world.getBoard());
                    world.reset();
                    Board newBoard = (Board) in.readObject();
                    for(int y = 0; y < newBoard.size.height; y++)
                        for(int x = 0; x < newBoard.size.width; x++)
                        {
                            newBoard.at(new Point(x, y)).addListener();
                        }
                    world.setBoard(newBoard);
                    world.getWindow().add(newBoard);
                    world.getWindow().pack();
                    world.getWindow().repaint();

                    while(true)
                        world.addOrganism((Organism) in.readObject());
                }catch(IOException exception)
                {}

                in.close();
                fileIn.close();
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
