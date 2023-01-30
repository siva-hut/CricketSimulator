package com.CircketSimu.App.CricketGame;

public class Bowler extends Player{
    Bowler(String playerName)
    {
        setPlayerName(playerName);
    }
    public char simulateRun()
    {   char gen;
        gen = generator();
        return gen;
    }
}
