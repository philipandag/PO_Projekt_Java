package com.Gołaś.Filip.Organizmy.Zwierzeta;

import com.Gołaś.Filip.Kierunek;
import com.Gołaś.Filip.Organizmy.Zwierze;
import com.Gołaś.Filip.Swiat;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Czlowiek extends Zwierze implements KeyListener {
    private Kierunek kierunek;
    public Czlowiek(){
        super(new Color(50, 200, 150), "C", 4);
        kierunek = Kierunek.N;
    }


    @Override
    public void akcja(){
        System.out.println("Czlowiek akcja!");
        moveTo(getPosX() + kierunek.getDx(), getPoxY() + kierunek.getDy());

    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("p");
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            kierunek = Kierunek.N;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            kierunek = Kierunek.E;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            kierunek = Kierunek.S;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            kierunek = Kierunek.W;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
