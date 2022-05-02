package com.Gołaś.Filip;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Kierunek {
    N,
    NE,
    E,
    SE,
    S,
    SW,
    W,
    NW;
    private static final List<Kierunek> VALUES = Collections.unmodifiableList((Arrays.asList(values())));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Kierunek losuj(){
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public int getDx(){
        return (this == Kierunek.E || this == Kierunek.NE || this == Kierunek.SE)? -1 :
                (this == Kierunek.W || this == Kierunek.NW || this == Kierunek.SW) ? 1 :
                        0;
    }
    public int getDy(){
        return (this == Kierunek.N || this == Kierunek.NW || this == Kierunek.NE)? -1 :
                (this == Kierunek.S || this == Kierunek.SW || this == Kierunek.SE) ? 1 :
                        0;
    }
}
