package com.Gołaś.Filip.Game;

import java.util.Random;

public class Randomiser extends Random {
    private static final int ACCURRACY = 10000;

    public Randomiser(){
        super();
    }

    public boolean chance(double chance){
        int num = nextInt(ACCURRACY);
        //System.out.println(num + " < " + chance * ACCURRACY);
        return  num < chance * ACCURRACY;
    }
}
