package com.CircketSimu.App.CricketGame;

public class Batsman extends Player {
    Batsman(String playerName)
    {
        setPlayerName(playerName);
    }
    @Override
    public char simulateRun()
    {   char outcome;
        outcome = RandomGenerator.generateOutCome();
        return outcome;
    }

}
