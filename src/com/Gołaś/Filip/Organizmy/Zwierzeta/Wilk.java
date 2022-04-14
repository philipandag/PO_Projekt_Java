package com.Gołaś.Filip.Organizmy.Zwierzeta;

import com.Gołaś.Filip.Organizmy.Zwierze;
import com.Gołaś.Filip.Swiat;

import java.awt.*;

public class Wilk extends Zwierze {
    public Wilk(){
        super(new Color(50, 50, 50), "W", 5);
    }

    @Override
    public void akcja(){
        System.out.println("Wilk akcja!");
    }
}