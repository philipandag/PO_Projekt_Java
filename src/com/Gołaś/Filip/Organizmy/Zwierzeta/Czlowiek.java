package com.Gołaś.Filip.Organizmy.Zwierzeta;

import com.Gołaś.Filip.Organizmy.Zwierze;
import com.Gołaś.Filip.Swiat;

import java.awt.*;

public class Czlowiek extends Zwierze {
    public Czlowiek(){
        super(new Color(50, 200, 150), "C", 4);
    }

    @Override
    public void akcja(){
        System.out.println("Czlowiek akcja!");
    }
}
