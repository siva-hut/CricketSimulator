package com.CircketSimu.App.CricketGame;

import java.util.Random;

public class RandomGenerator {
    public static Random random = new Random();
    public static char generateOutCome()
    {
        char [] arr = {'0','1','2','3','4','5','6','w','N','W'};
        return arr[random.nextInt(arr.length)];
    }
    public static char generateNoBallOutCome()
    {
        char[] arr = {'0','4','6'};
        return arr[random.nextInt(arr.length)];
    }
}
