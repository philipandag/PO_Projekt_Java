package com.Gołaś.Filip.Game;

import java.awt.event.KeyEvent;
import java.util.List;


public class DirectionHex extends Direction<DirectionHex.HexValue> {

    public enum HexValue implements DirectionValueEnum{
        NE,
        E,
        SE,
        SW,
        W,
        NW,
    }
    private static final List<HexValue> VALUES = List.of(HexValue.values());
    public static final int SIZE = VALUES.size();

    public DirectionHex(){
        super(HexValue.NW);
    }

    public DirectionHex(HexValue v){
        super(v);
    }

    @Override
    public Direction randomise(){
        this.value = VALUES.get(RANDOM.nextInt(SIZE));
        return this;
    }

    public int getDx(){
        return (this.value == HexValue.E || this.value == HexValue.NE) ? 1 :
                (this.value == HexValue.W || this.value == HexValue.SW) ? -1 :
                        0;
    }

    public int getDy(){
        return (this.value == HexValue.NW || this.value == HexValue.NE) ? -1 :
                (this.value == HexValue.SW || this.value == HexValue.SE) ? 1 :
                        0;
    }

    public Direction next(){
        this.value = HexValue.values()[(value.ordinal() + 1) % SIZE];
        return this;
    }

    public int getSize(){
        return SIZE;
    }

    @Override
    public String toString() {
        return switch (this.value) {
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
            case KeyEvent.VK_DOWN -> value = HexValue.SE;
            case KeyEvent.VK_LEFT -> value = HexValue.W;
            case KeyEvent.VK_UP -> value = HexValue.NW;
            case KeyEvent.VK_RIGHT -> value = HexValue.E;
        }
    }
}
