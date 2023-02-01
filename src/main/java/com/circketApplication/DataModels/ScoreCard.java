package com.circketApplication.DataModels;

import org.springframework.stereotype.Repository;

@Repository
public class ScoreCard {
    String wicketsLost;
    String bowlerName;
    String wicketsTaken;
    String batsmanName;
    String batsmanScore;
    String currentBallScore;
    String battingTeam;
    String battingTeamScore;
    String bowlingTeamScore;
    String ballsFaced;
    String bowlerRunsGiven,ballsBowled;

    String bowlingTeam;

    public void setBowlingTeam(String bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    public void setCurrentOver(String currentOver) {
        this.currentOver = currentOver;
    }
    public void setWicketsLost(String wicketsLost) {
        this.wicketsLost = wicketsLost;
    }
    String currentOver;

    public void setBowlerRunsGiven(String bowlerRunsGiven) {
        this.bowlerRunsGiven = bowlerRunsGiven;
    }

    public void setBallsBowled(String ballsBowled) {
        this.ballsBowled = ballsBowled;
    }

    public void setBowlerName(String bowlerName) {
        this.bowlerName = bowlerName;
    }

    public void setWicketsTaken(String wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public void setBatsmanName(String batsmanName) {
        this.batsmanName = batsmanName;
    }

    public void setBatsmanScore(String batsmanScore) {
        this.batsmanScore = batsmanScore;
    }

    public void setBattingTeam(String battingTeam) {
        this.battingTeam = battingTeam;
    }

    public void setBattingTeamScore(String battingTeamScore) {
        this.battingTeamScore = battingTeamScore;
    }

    public void setBowlingTeamScore(String bowlingTeamScore) {
        this.bowlingTeamScore = bowlingTeamScore;
    }

    public void setBallsFaced(String ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public void setCurrentBallScore(String currentBallScore) {
        this.currentBallScore = currentBallScore;
    }

    public void setBallsRemaining(String ballsRemaining) {
        this.ballsRemaining = ballsRemaining;
    }

    @Override
    public String toString()
    {   System.out.println(String.format("Over: %s/20 " + " BallOutCome: %s ",currentOver, currentBallScore));
        System.out.println(String.format("Bowling Team: %s "+ "Bowling Team score: %s ",bowlingTeam,bowlingTeamScore));

        System.out.println(String.format("Bowler: %s  Runs /  Balls : %s / %s   WicketsTaken : %s",bowlerName,bowlerRunsGiven,ballsBowled,wicketsTaken));
        System.out.println(String.format("Batsman: %s"+ "  Runs / Balls: %s / %s",batsmanName,batsmanScore,ballsFaced));
        System.out.println(
                String.format("Batting Team: %s "+ " Batting Team score: %s + wicketsLost %s",battingTeam,battingTeamScore,wicketsLost));
        return " ";
    }
    String ballsRemaining;

}
