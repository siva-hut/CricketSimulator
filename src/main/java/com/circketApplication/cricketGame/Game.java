package com.circketApplication.cricketGame;

import com.circketApplication.DataModels.ScoreCard;
import com.circketApplication.cricketGame.player.Player;
import com.circketApplication.cricketGame.util.Overs;

import java.time.LocalTime;
import java.util.UUID;

public class Game {
    //Instance variables
    private UUID gameId = UUID.randomUUID();
    private LocalTime startTime = LocalTime.now();
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

    private UUID getGameId() {
        return gameId;
    }
    private LocalTime getStartTime() {
        return startTime;
    }
    private Team getBattingTeam() {
        return battingTeam;
    }

    private Player getBatsman()
    {
        return battingTeam.getBatsman();
    }

    private Team getBowlingTeam() {
        return bowlingTeam;
    }

    private Player getBowler()
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
    public ScoreCard simulateNextBall()
    {
        char run = getBatsman().simulateRun(noBall);
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
        if(noBall == false && run!= 'W' ) {
            getBowler().oversBowled.nextBall();
            getOvers().nextBall();
            if (overs.overCompleted()) {
                getBowlingTeam().changeBowler();
            }
            checkGameStatus();
        }
        return createScoreCard(run);
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
            Boolean condition2 = getBattingTeam().getScore()>getBowlingTeam().getScore();;
            if(condition1 || condition2)
                gameOver=true;
        }
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
        scoreCard.setWicketsLost(String.valueOf(getBattingTeam().getWicketsLost()));
        scoreCard.setCurrentOver(overs.getOvers());
        return scoreCard;
    }
}