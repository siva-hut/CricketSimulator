package com.circketApplication.cricketGame.util;

import java.util.Random;

public class RandomGenerator {
    private static RandomGenerator randomGenerator;
    private RandomGenerator(){};
    public static RandomGenerator getRandomGenerator() {
        if(randomGenerator==null)
        {
            randomGenerator = new RandomGenerator();
        }
        return randomGenerator;
    }

    public static Random random = new Random();
    public static char generateOutCome(int index)
    {
        char [] arr = {'w','3','4','5','6','0','1','2','N','W'};
        if(index<80)
        {
            return arr[random.nextInt(2)];
        }
        if(index>600)
        {
            return arr[random.nextInt(2,7)];
        }
        else
        {
            return arr[random.nextInt(arr.length)];
        }
    }
    public static char generateNoBallOutCome()
    {
        char[] arr = {'0','4','6'};
        return arr[random.nextInt(arr.length)];
    }
}
