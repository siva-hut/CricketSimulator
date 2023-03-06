package com.cricketApplication.cricketGame.util;

import java.util.Random;

public class RandomGenerator {
    public static Random random = new Random();
    private static RandomGenerator randomGenerator;

    ;

    private RandomGenerator() {
    }

    public static RandomGenerator getRandomGenerator() {
        if (randomGenerator == null) {
            randomGenerator = new RandomGenerator();
        }
        return randomGenerator;
    }

    public static char generateOutCome(int index) {
        char[] arr = {'w', '3', '4', '5', '6', '1', '0', '0', '1', '2', 'N', 'W'};
        if (index < 80) {
            return arr[random.nextInt(2)];
        }
        if (index > 650) {
            return arr[random.nextInt(2, 9)];
        } else {
            return arr[random.nextInt(arr.length)];
        }
    }

    public static char generateNoBallOutCome() {
        char[] arr = {'0', '4', '6'};
        return arr[random.nextInt(arr.length)];
    }

    public String getRandomPlayer() {
        int number = random.nextInt(2);
        if (number == 1)
            return "Batsman";
        return "Bowler";
    }
}
