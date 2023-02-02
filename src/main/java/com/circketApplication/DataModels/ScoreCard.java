package com.circketApplication.DataModels;

import com.circketApplication.cricketGame.Game;
import org.springframework.stereotype.Repository;

public class ScoreCard {
    String ballsRemaining;
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
    String currentOver;
    String bowlingTeam;
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
    private void setBatsmanData(Game game)
    {
        batsmanScore   = String.valueOf(game.getBatsman().getRunsScored());
        batsmanName    = game.getBatsman().getPlayerName();
        ballsFaced     = String.valueOf(game.getBatsman().getBallsFaced());
    }
    private void setBowlerData(Game game)
    {
        bowlerName = game.getBowler().getPlayerName();
        wicketsTaken = String.valueOf(game.getBowler().getWicketsTaken());
        bowlerRunsGiven = String.valueOf(game.getBowler().getRunsGiven());
        ballsBowled = String.valueOf(game.getBowler().oversBowled.getOvers());

    }
    private void setBattingTeamData(Game game)
    {
        battingTeam = game.getBattingTeam().getTeamName();
        battingTeamScore = String.valueOf(game.getBattingTeam().getScore());
        wicketsLost = String.valueOf(game.getBattingTeam().getWicketsLost());
    }
    private void setBowlingTeamData(Game game)
    {
        bowlingTeamScore  = String.valueOf(game.getBowlingTeam().getScore());
        bowlingTeam = game.getBowlingTeam().getTeamName();
    }
    private void setGameData(Game game)
    {
        currentBallScore = String.valueOf(game.getRun());
        ballsRemaining = String.valueOf(game.getOvers().ballsRemaining());
        currentOver = game.getOvers().getOvers();

    }
    public void updateScoreCard(Game game)
    {
        setBatsmanData(game);
        setBowlerData(game);
        setBattingTeamData(game);
        setBowlingTeamData(game);
        setGameData(game);
    }
}
