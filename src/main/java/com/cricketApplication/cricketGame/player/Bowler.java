package com.circketApplication.cricketGame.player;

import com.circketApplication.cricketGame.util.Overs;
import com.circketApplication.cricketGame.util.RandomGenerator;

public class Bowler extends Player {
    public Bowler(String playerName) {
        setPlayerName(playerName);
    }
    @Override
    public char simulateRun(boolean noBall)
    {
        int randomNumber = RandomGenerator.random.nextInt(700);
        return RandomGenerator.generateOutCome(randomNumber);
    }

    @Override
    public String playerType() {
        return "Bowler";
    }
}
