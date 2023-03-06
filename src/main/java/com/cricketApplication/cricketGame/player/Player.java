package com.cricketApplication.cricketGame.player;

import com.cricketApplication.cricketGame.util.Overs;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Player {
    public Overs oversBowled = new Overs(0);
    private Long id;
    private int runsGiven = 0;
    private int wicketsTaken = 0;
    private String playerName;
    private int runsScored = 0;
    private int ballsFaced = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRunsGiven() {
        return runsGiven;
    }

    public void wicketTaken() {
        wicketsTaken++;
    }

    public void addRunsGiven(int run) {
        this.runsGiven += run;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

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

    public abstract char simulateRun(boolean noBall);

    public void updateRun(int run) {
        runsScored += run;
        ballsFaced++;
    }

    public abstract String playerType();
}
