package com.Gołaś.Filip.Game;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class DirectionSquare extends Direction<DirectionSquare.SquareValue>{

    public enum SquareValue implements DirectionValueEnum{
        N,
        NE,
        E,
        SE,
        S,
        SW,
        W,
        NW,
    }
    private static final List<SquareValue> VALUES = List.of(SquareValue.values());
    public static final int SIZE = VALUES.size();

    public DirectionSquare(){
        super(SquareValue.N);
    }

    @Override
    public Direction randomise(){
        this.value = VALUES.get(RANDOM.nextInt(SIZE));
        return this;
    }

    public int getDx(){
        return (this.value == SquareValue.E || this.value == SquareValue.NE || this.value == SquareValue.SE) ? 1 :
                (this.value == SquareValue.W || this.value == SquareValue.NW || this.value == SquareValue.SW) ? -1 :
                        0;
    }

    public int getDy(){
        return (this.value == SquareValue.N || this.value == SquareValue.NW || this.value == SquareValue.NE) ? -1 :
                (this.value == SquareValue.S || this.value == SquareValue.SW || this.value == SquareValue.SE) ? 1 :
                        0;
    }

    public Direction next(){
        this.value = SquareValue.values()[(value.ordinal() + 1) % SIZE];
        return this;
    }

    public int getSize(){
        return SIZE;
    }

    @Override
    public String toString() {
        return switch (this.value) {
            case N -> "Up";
            case S -> "Down";
            case E -> "Right";
            case W -> "Left";
            case NE -> "Up-Right";
            case NW -> "Up-Left";
            case SE -> "Down-Right";
            case SW -> "Down-Left";
            default -> "error";
        };
    }

    public void setByKey(int key)
    {
        switch(key){
            case KeyEvent.VK_DOWN -> value = SquareValue.S;
            case KeyEvent.VK_LEFT -> value = SquareValue.W;
            case KeyEvent.VK_UP -> value = SquareValue.N;
            case KeyEvent.VK_RIGHT -> value = SquareValue.E;
        }
    }
}
