package com.CircketSimu.App.CricketGame;

public class Bowler extends Player{
    public Overs oversBowled;
    private int runsGiven;
    private int wicketsTaken;
    public int getRunsGiven() {
        return runsGiven;
    }
    public void wicketTaken()
    {
        wicketsTaken++;
    }
    public void addRunsGiven(int run) {
        this.runsGiven += run;
    }
    public int getWicketsTaken() {
        return wicketsTaken;
    }
    Bowler(String playerName)
    {
        setPlayerName(playerName);
        oversBowled = new Overs(5);

    }
    @Override
    public char simulateRun()
    {   char outcome;
        outcome = RandomGenerator.generateOutCome();
        return outcome;
    }
}
