package com.Gołaś.Filip;

import java.awt.*;
import java.time.temporal.Temporal;

public abstract class Organizm implements Comparable<Organizm>{
    private Swiat swiat;
    private Color kolor;
    private int inicjatywa;
    private String znak;
    public Organizm(Color kolor, String znak, int inicjatywa){
        this.znak = znak;
        this.kolor = kolor;
        this.inicjatywa = inicjatywa;
    }

    public Organizm(Color kolor, Swiat swiat, int inicjatywa){
        this.swiat = swiat;
        this.kolor = kolor;
        this.inicjatywa = inicjatywa;
    }

    @Override
    public int compareTo(Organizm o) {
        return inicjatywa - o.inicjatywa;
    }

    public Color getKolor(){
        return kolor;
    }

    public void setSwiat(Swiat swiat){
        this.swiat = swiat;
    }

    public String getZnak(){
        return znak;
    }
}
