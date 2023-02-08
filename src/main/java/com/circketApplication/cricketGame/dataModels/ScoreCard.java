package com.circketApplication.cricketGame.dataModels;

import com.circketApplication.cricketGame.Game;

public class ScoreCard {
    String ballsRemaining;
    String wicketsLost;
    String bowlerName;
    String wicketsTaken;
    String batsmanOnStikeName;
    String batsmanOnStrikeScore;
    String batsmanOffStikeName;
    String batsmanOffStrikeScore;
    String batsmanOffStrikeballsFaced;
    String currentBallScore;
    String battingTeam;
    String battingTeamScore;
    String bowlingTeamScore;
    String batsmanOnStrikeballsFaced;
    String bowlerRunsGiven,ballsBowled;
    String currentOver;
    String bowlingTeam;
    @Override
    public String toString()
    {   System.out.println(String.format("Over: %s/20 " + " BallOutCome: %s ",currentOver, currentBallScore));
        System.out.println(String.format("Bowling Team: %s      "+ "Bowling Team score: %s ",bowlingTeam,bowlingTeamScore));
        System.out.println(String.format("Bowler: %s  Runs / Balls : %s / %s   WicketsTaken : %s",bowlerName,bowlerRunsGiven,ballsBowled,wicketsTaken));
        System.out.println(String.format("BatsmanOnstrike: %s"+ "  Runs / Balls: %s / %s", batsmanOnStikeName, batsmanOnStrikeScore, batsmanOnStrikeballsFaced));
        System.out.println(String.format("BatsmanOffstrike: %s"+ "  Runs / Balls: %s / %s", batsmanOffStikeName, batsmanOffStrikeScore, batsmanOffStrikeballsFaced));
        System.out.println(String.format("Batting Team: %s "+ " Batting Team score: %s   wicketsLost %s",battingTeam,battingTeamScore,wicketsLost));
        return " ";
    }
    private void setBatsmanData(Game game)
    {
        batsmanOnStrikeScore = String.valueOf(game.getBattingTeam().getBatsmanOnStrike().getRunsScored());
        batsmanOnStikeName = game.getBattingTeam().getBatsmanOnStrike().getPlayerName();
        batsmanOnStrikeballsFaced = String.valueOf(game.getBattingTeam().getBatsmanOnStrike().getBallsFaced());
        batsmanOffStikeName = game.getBattingTeam().getBatsmanOffStrike().getPlayerName();
        batsmanOffStrikeballsFaced = String.valueOf(game.getBattingTeam().getBatsmanOffStrike().getBallsFaced());
        batsmanOffStrikeScore = String.valueOf(game.getBattingTeam().getBatsmanOffStrike().getRunsScored());
    }
    private void setBowlerData(Game game)
    {
        bowlerName = game.getBowlingTeam().getBowler().getPlayerName();
        wicketsTaken = String.valueOf(game.getBowlingTeam().getBowler().getWicketsTaken());
        bowlerRunsGiven = String.valueOf(game.getBowlingTeam().getBowler().getRunsGiven());
        ballsBowled = String.valueOf(game.getBowlingTeam().getBowler().oversBowled.getOvers());

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
