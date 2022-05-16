package com.Gołaś.Filip.Window.Components;

import com.Gołaś.Filip.Game.World;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;
import java.io.Serializable;

public class Board extends AbstractBoard implements Serializable {

    public Board(Dimension size, World world){
        super(size, world);

        int index = 0;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        Dimension fontSize = new Dimension(defaultSize.width / size.width, defaultSize.height / size.height);
        for(int row = 0; row < size.height; row++){
            for(int col = 0; col < size.width; col++){
                Color color = index % 2 == 0 ? Color.WHITE : new Color(230, 230,230);
                gbc.gridx = col;
                gbc.gridy = row;
                Field field = new Field(color, world, new Point(col, row));
                add(field, gbc);
                grid[col][row] = field;
                grid[col][row].updateFont(fontSize);
                index++;
            }
            if(size.width %2==0)
                index++;
        }
    }

    @Override
    public AbstractField[][] createGrid(){
        return new Field[size.width][size.height];
    }

}
