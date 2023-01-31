package com.CircketSimu.App.CricketGame;

public class Bowler extends Player{
    Bowler(String playerName)
    {
        setPlayerName(playerName);
        oversBowled = new Overs(0);

    }
    @Override
    public char simulateRun()
    {   char outcome;
        outcome = RandomGenerator.generateOutCome();
        return outcome;
    }
}
