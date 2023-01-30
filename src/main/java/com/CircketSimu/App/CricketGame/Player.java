package com.CircketSimu.App.CricketGame;

import java.util.Random;

public abstract class Player {
    private static Random random = new Random();
    private String playerName;
    private int runsScored;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    private int wicketsTaken;
    private int ballsFaced;
    public abstract char simulateRun();
    protected static char generator()
    {
        char [] arr = {'0','1','2','3','4','5','6','w'};
        return arr[random.nextInt(arr.length)];
    }
    public void updateRun(int run)
    {
        runsScored+=run;
        ballsFaced++;
    }
}
