package com.circketApplication.cricketGame.player;

import com.circketApplication.cricketGame.util.Overs;
import com.circketApplication.cricketGame.util.RandomGenerator;

public class Batsman extends Player {
    public Batsman(String playerName) {
        setPlayerName(playerName);
    }

    @Override
    public char simulateRun(boolean noBall)
    {
        if (noBall)
            return RandomGenerator.generateNoBallOutCome();
        int randomNumber = RandomGenerator.random.nextInt(1000);
        return RandomGenerator.generateOutCome(randomNumber);
    }

}
