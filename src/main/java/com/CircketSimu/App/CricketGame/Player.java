package com.CircketSimu.App.CricketGame;

import java.util.Random;

public abstract class Player {
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
    public int getBallsFaced() {
        return ballsFaced;
    }
    private int ballsFaced;
    public abstract char simulateRun();
    public void updateRun(int run)
    {
        runsScored+=run;
        ballsFaced++;
    }
}
