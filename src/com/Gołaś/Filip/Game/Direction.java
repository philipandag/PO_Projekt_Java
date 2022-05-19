package com.Gołaś.Filip.Game;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public abstract class Direction<DirectionEnum extends DirectionValueEnum> implements Serializable, DirectionInterface{

    protected DirectionEnum value;
    protected static final Random RANDOM = new Random();
    protected Direction(DirectionEnum v){
        this.value = v;
    }
    public void set(DirectionEnum v){
        this.value = v;
    }
    public abstract Direction randomise();

    public abstract int getDx();

    public abstract int getDy();

    public abstract Direction next();

    public abstract int getSize();
    public abstract void setByKey(int key);
}
