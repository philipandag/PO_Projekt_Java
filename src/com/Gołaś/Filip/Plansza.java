package com.Gołaś.Filip;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
public class Plansza extends JPanel {
    private int wymiarX;
    private int wymiarY;
    private Swiat swiat;
    public Plansza(int wymiarX, int wymiarY, Swiat swiat){
        this.swiat = swiat;
        this.wymiarX = wymiarX;
        this.wymiarY = wymiarY;
        int index = 0;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        for(int row = 0; row < wymiarY; row++){
            for(int col = 0; col < wymiarX; col++){
                Color color = index % 2 == 0 ? Color.WHITE : new Color(230, 230,230);
                gbc.gridx = col;
                gbc.gridy = row;
                add(new Pole(color, swiat, col, row), gbc);
                index++;
            }
            if(wymiarX%2==0)
                index++;
        }
    }
}
