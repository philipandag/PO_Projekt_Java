package com.Gołaś.Filip;

import java.awt.*;
import java.time.temporal.Temporal;

public abstract class Organizm implements Comparable<Organizm>{
    protected Swiat swiat;
    protected Color kolor;
    protected int inicjatywa;
    protected String znak;
    protected int posX;
    protected int poxY;
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

    public Integer getInicjatywa(){
        return inicjatywa;
    }
    public abstract void akcja();

    public void moveTo(int x, int y){
        Pole[][] grid = swiat.getPlanszaGrid();
        grid[this.posX][this.poxY].setPole(null);
        grid[x][y].setPole(this);
    }

    public int getPosX() {
        return posX;
    }

    public int getPoxY() {
        return poxY;
    }
}
