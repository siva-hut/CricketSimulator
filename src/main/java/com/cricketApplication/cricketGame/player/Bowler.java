package com.cricketApplication.cricketGame.player;

import com.cricketApplication.cricketGame.util.RandomGenerator;

public class Bowler extends Player {
    public Bowler(String playerName) {
        setPlayerName(playerName);
    }

    @Override
    public char simulateRun(boolean noBall) {
        int randomNumber = RandomGenerator.random.nextInt(700);
        return RandomGenerator.generateOutCome(randomNumber);
    }

    @Override
    public String playerType() {
        return "Bowler";
    }
}
