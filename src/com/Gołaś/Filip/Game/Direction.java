package com.Gołaś.Filip.Game;

import java.util.List;
import java.util.Random;

public enum Direction {
    N,
    NE,
    E,
    SE,
    S,
    SW,
    W,
    NW;
    private static final List<Direction> VALUES = List.of(values());
    public static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Direction randomise(){
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public int getDx(){
        return (this == Direction.E || this == Direction.NE || this == Direction.SE)? 1 :
                (this == Direction.W || this == Direction.NW || this == Direction.SW) ? -1 :
                        0;
    }
    public int getDy(){
        return (this == Direction.N || this == Direction.NW || this == Direction.NE)? -1 :
                (this == Direction.S || this == Direction.SW || this == Direction.SE) ? 1 :
                        0;
    }

    public Direction next(){
        return values()[(ordinal() + 1) % SIZE];
    }

    @Override
    public String toString() {
        return switch (this) {
            case N -> "Up";
            case S -> "Down";
            case E -> "Right";
            case W -> "Left";
            default -> "error";
        };
    }
}
