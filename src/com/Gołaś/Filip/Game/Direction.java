package com.Gołaś.Filip.Game;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Direction implements Serializable {
    public enum Value {
    N,
    NE,
    E,
    SE,
    S,
    SW,
    W,
    NW;
};
    public enum Mode{
        NORMAL,
        HEX,
    }

    private static final List<Value> VALUES = List.of(Value.values());
    private static final List<Value> HEX_VALUES = List.of(Value.N, Value.E, Value.SW, Value.S, Value.W, Value.NE);
    public static final int SIZE = VALUES.size();
    public static final int HEX_SIZE = HEX_VALUES.size();
    private static final Random RANDOM = new Random();
    private static Mode mode;
    public Value value;

    public static void setMode(Mode mode) {
        Direction.mode = mode;
    }

    public Direction(){
        this.value = Value.N;
    }

    public Direction(Value v){
        this.value = v;
    }

    public void set(Value v){
        this.value = v;
    }
    public Direction randomise(){
        if(mode == Mode.NORMAL)
            this.value = VALUES.get(RANDOM.nextInt(SIZE));
        else if(mode == Mode.HEX)
            this.value = HEX_VALUES.get(RANDOM.nextInt(HEX_SIZE));
        return this;
    }

    public int getDx(){
        return (this.value == Value.E || this.value == Value.NE || this.value == Value.SE) ? 1 :
         (this.value == Value.W || this.value == Value.NW || this.value == Value.SW) ? -1 :
        0;
    }
//    public int getDx(){
//        if(this.value == Value.E || this.value == Value.NE || this.value == Value.SE)
//            return 1;
//        if (this.value == Value.W || this.value == Value.NW || this.value == Value.SW)
//            return -1;
//        return 0;
//    }

    public int getDy(){
        return (this.value == Value.N || this.value == Value.NW || this.value == Value.NE) ? -1 :
        (this.value == Value.S || this.value == Value.SW || this.value == Value.SE) ? 1 :
          0;
    }
//    public int getDy(){
//        if(this.value == Value.N || this.value == Value.NW || this.value == Value.NE)
//            return -1;
//        if(this.value == Value.S || this.value == Value.SW || this.value == Value.SE)
//            return 1;
//        return 0;
//    }

    public Direction next(){
        if(mode==Mode.NORMAL)
            this.value = Value.values()[(value.ordinal() + 1) % SIZE];
        else if(mode == Mode.HEX)
            this.value = HEX_VALUES.get((value.ordinal() + 1) % HEX_SIZE);
        return this;
    }

    @Override
    public String toString() {
        return switch (this.value) {
            case N -> "Up";
            case S -> "Down";
            case E -> "Right";
            case W -> "Left";
            default -> "error";
        };
    }
}
