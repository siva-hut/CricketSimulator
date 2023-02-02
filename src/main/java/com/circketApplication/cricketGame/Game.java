package com.circketApplication.cricketGame;

import com.circketApplication.DataModels.ScoreCard;
import com.circketApplication.cricketGame.player.Player;
import com.circketApplication.cricketGame.util.Overs;

import java.time.LocalTime;
import java.util.UUID;

public class Game {
    private char run;
    private final UUID gameId = UUID.randomUUID();
    private final LocalTime startTime = LocalTime.now();
    private Team battingTeam;
    private Team bowlingTeam;
    private Overs overs;
    private int innings = 1;
    private boolean gameOver = false;
    boolean noBall = false;
    private ScoreCard scoreCard = new ScoreCard();
    //Getters
    public Overs getOvers() {
        return overs;
    }
    private UUID getGameId() {
        return gameId;
    }
    private LocalTime getStartTime() {
        return startTime;
    }
    public Team getBattingTeam() {
        return battingTeam;
    }

    public Player getBatsman()
    {
        return battingTeam.getBatsman();
    }

    public Team getBowlingTeam() {
        return bowlingTeam;
    }

    public Player getBowler()
    {
        return bowlingTeam.getBowler();
    }
    private int getInnings() {
        return innings;
    }
    //Setters
    public void setBattingTeam(Team battingTeam) {
        this.battingTeam = battingTeam;
    }

    public void setBowlingTeam(Team bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    public void setOvers(Overs overs) {
        this.overs = overs;
    }
    //Utility
    public boolean isGameOver() {
        return gameOver;
    }

    public char getRun() {
        return run;
    }

    public ScoreCard simulateNextBall()
    {
        run = getBatsman().simulateRun(noBall);
        noBall = false;
        if(run == 'w'){
            simulateWicket();
        }
        else if(run == 'W') // Wide
        {
            wideSimulation();
        }
        else if(run == 'N')
        {
            noBall = true;
        }
        else
        {
            runSimulation(run);
        }
        if(!noBall && run!= 'W' ) {
            getBowler().oversBowled.nextBall();
            getOvers().nextBall();
            if (overs.overCompleted()) {
                getBowlingTeam().changeBowler();
            }
            checkGameStatus();
        }
        scoreCard.updateScoreCard(this);
        return scoreCard;
    }
    private void switchSides()
    {   innings++;
        Team duplicate = battingTeam;
        battingTeam = bowlingTeam;
        bowlingTeam = duplicate;
        overs.reset();
    }
    private void simulateWicket()
    {
        getBowler().wicketTaken();
        getBattingTeam().increaseWicketLost();
    }
    private void runSimulation(char run)
    {
        int nRun = run-'0' ;
        getBatsman().updateRun(nRun);
        getBowler().addRunsGiven(nRun);
        getBattingTeam().increaseScore(nRun);
    }
    private void wideSimulation()
    {
        getBowler().addRunsGiven(1);
        getBattingTeam().increaseScore(1);
    }
    private void checkGameStatus()
    {   Boolean condition1 = getOvers().ballsRemaining() == 0 || getBattingTeam().getWicketsLost()==10;
        if(getInnings() == 1 && condition1)
        {
            switchSides();
        }
        else if(getInnings() == 2)
        {
            Boolean condition2 = getBattingTeam().getScore()>getBowlingTeam().getScore();
            if(condition1 || condition2)
                gameOver=true;
        }
    }
}