package com.Gołaś.Filip.Game;

public interface DirectionInterface {
    public DirectionInterface randomise();
    public int getDx();
    public int getDy();
    public DirectionInterface next();
    public int getSize();
    public void setByKey(int key);
}
