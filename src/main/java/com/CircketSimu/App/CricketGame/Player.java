package com.CircketSimu.App.CricketGame;

import java.util.Random;

public abstract class Player {
    public Overs oversBowled;
    private int runsGiven = 0;
    private int wicketsTaken = 0;
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
    private String playerName;
    private int runsScored = 0;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getRunsScored() {
        return runsScored;
    }
    public int getBallsFaced() {
        return ballsFaced;
    }
    private int ballsFaced =0;
    public abstract char simulateRun();
    public void updateRun(int run)
    {
        runsScored+=run;
        ballsFaced++;
    }
}
