package com.circketApplication.cricketGame.player;

import com.circketApplication.cricketGame.util.Overs;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Player {
    private Long id;
    public Overs oversBowled = new Overs(0);
    private int runsGiven = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    public abstract char simulateRun(boolean noBall);
    public void updateRun(int run)
    {
        runsScored+=run;
        ballsFaced++;
    }
    public abstract String playerType();
}
