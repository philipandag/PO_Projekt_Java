package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;
import com.Gołaś.Filip.Organisms.Animals.Antelope;
import com.Gołaś.Filip.Organisms.Organism;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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
                BufferedReader fr = new BufferedReader(new FileReader(saveFilePath));
                StringBuilder sb = new StringBuilder();
                String line = new String();
                while((line = fr.readLine()) != null)
                {
                    String[] args = line.split(" ");
                    String a = "Wilk";
                    try {
                        Organism o = (Organism)Class.forName(packageName + args[0]).getDeclaredConstructor().newInstance(world);
                        o.load(line);
                    }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                           InvocationTargetException exception){
                        System.out.println(exception.getMessage());
                    }
                }
            }catch(java.io.IOException exception){
                System.out.printf(exception.getMessage());
            }
        });
    }
}
