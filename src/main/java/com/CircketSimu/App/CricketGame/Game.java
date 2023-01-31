package com.CircketSimu.App.CricketGame;

import com.CircketSimu.App.DataModels.ScoreCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

public class Game {
    public Game(Team battingTeam, Team bowlingTeam, int totalOvers)
    {
        startTime = LocalTime.now();
        overs = new Overs(totalOvers);
        this.battingTeam = battingTeam;
        this.bowlingTeam =  bowlingTeam;
        gameId = UUID.randomUUID();
    }
    //Instance variables
    private UUID gameId;
    private LocalTime startTime;
    private Team battingTeam;
    private Team bowlingTeam;
    private Overs overs;
    private int innings = 1;
    private boolean gameOver = false;
    boolean noBall = false;
    //Getters
    private Overs getOvers() {
        return overs;
    }

    public UUID getGameId() {
        return gameId;
    }
    public LocalTime getStartTime() {
        return startTime;
    }

    private Team getBattingTeam() {
        return battingTeam;
    }

    private Team getBowlingTeam() {
        return bowlingTeam;
    }
    private int getInnings() {
        return innings;
    }
    //Setters
    private void setOvers(Overs overs) {
        this.overs = overs;
    }

    private void setInnings(int innings) {
        this.innings = innings;
    }

    private void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    private Player getBatsman()
    {
        return battingTeam.getBatsman();
    }
    //Utility
    public boolean isGameOver() {
        return gameOver;
    }
    public ScoreCard simulateNextBall()
    {   Player bowler = getBowlingTeam().getBowler();
        char run;
        if(noBall) {
            run = RandomGenerator.generateNoBallOutCome();
            noBall = false;
        }
        else
        {
            run = getBattingTeam().getBatsman().simulateRun();
        }
        bowler.oversBowled.nextBall();
        //Wicket
        if(run == 'w'){
            bowler.wicketTaken();
            getBattingTeam().increaseWicketLost();
        }
        //Wide
        else if(run == 'W')
        {
            bowler.addRunsGiven(1);
            getBattingTeam().increaseScore(1);
            return createScoreCard(run);
        }
        //Noball
        else if(run == 'N')
        {   noBall = true;
            return createScoreCard(run);
        }
        //Runs
        else
        {   int nRun = run-'0' ;
            getBattingTeam().getBatsman().updateRun(nRun);
            bowler.addRunsGiven(nRun);
            getBattingTeam().increaseScore(nRun);
        }
        getOvers().nextBall();
        if(overs.overCompleted()) {
            getBowlingTeam().changeBowler();
        }
        checkGameStatus();
        return createScoreCard(run);
    }
    private void switchSides()
    {   innings++;
        Team duplicate = battingTeam;
        battingTeam = bowlingTeam;
        bowlingTeam = duplicate;
        overs.reset();
    }
    private ScoreCard createScoreCard(char run)
    {
        ScoreCard scoreCard = new ScoreCard();
        scoreCard.setBatsmanScore(String.valueOf(getBatsman().getRunsScored()));
        scoreCard.setBatsmanName(getBatsman().getPlayerName());
        scoreCard.setCurrentBallScore(String.valueOf(run));
        scoreCard.setBallsRemaining(String.valueOf(overs.ballsRemaining()));
        scoreCard.setBowlerName(getBowlingTeam().getBowler().getPlayerName());
        scoreCard.setWicketsTaken(String.valueOf(getBowlingTeam().getBowler().getWicketsTaken()));
        scoreCard.setBallsFaced(String.valueOf(getBatsman().getBallsFaced()));
        scoreCard.setBattingTeamScore(String.valueOf(getBattingTeam().getScore()));
        scoreCard.setBatsmanScore(String.valueOf(getBatsman().getRunsScored()));
        scoreCard.setBowlerRunsGiven(String.valueOf(getBowlingTeam().getBowler().getRunsGiven()));
        scoreCard.setWicketsTaken(String.valueOf(getBowlingTeam().getBowler().getWicketsTaken()));
        scoreCard.setBallsBowled(String.valueOf(getBowlingTeam().getBowler().oversBowled.getOvers()));
        scoreCard.setBowlingTeamScore(String.valueOf(getBowlingTeam().getScore()));
        scoreCard.setBowlingTeam(getBowlingTeam().getTeamName());
        scoreCard.setCurrentOver(overs.getOvers());
        return scoreCard;
    }
    private void checkGameStatus()
    {
        if(getInnings() == 1)
        {
            if(getOvers().ballsRemaining() == 0 || getBattingTeam().getWicketsLost()==10)
                switchSides();
        }
        if(getInnings() == 2)
        {
            Boolean condition1 = getOvers().ballsRemaining() == 0 || getBattingTeam().getWicketsLost()==10;
            Boolean condition2 = getBattingTeam().getScore()>getBowlingTeam().getScore();;
            if(condition1 || condition2)
                setGameOver(true);
        }
    }
}