package com.circketApplication.cricketGame;

import com.circketApplication.cricketGame.util.Overs;

import java.util.ArrayList;

public class GameBuilder {
    String team1Name = "KKK";
    String team2Name = "MMM";
    int totalOvers = 20;
    int team1NumberOfBatsman = 6;

    public void setTeam1NumberOfBatsman(int team1NumberOfBatsman) {
        this.team1NumberOfBatsman = team1NumberOfBatsman;
    }

    public void setTeam2NumberOfBatsman(int team2NumberOfBatsman) {
        this.team2NumberOfBatsman = team2NumberOfBatsman;
    }

    public void setTeam1PlayerNames(ArrayList team1PlayerNames) {
        this.team1PlayerNames = team1PlayerNames;
    }

    public void setTeam2PlayerNames(ArrayList team2PlayerNames) {
        this.team2PlayerNames = team2PlayerNames;
    }

    int team2NumberOfBatsman = 6;
    private ArrayList team1PlayerNames = new ArrayList<>();
    private ArrayList team2PlayerNames = new ArrayList<>();
    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public void setTotalOvers(int totalOvers) {
        this.totalOvers = totalOvers;
    }
    private Team buildTeam1()
    {
        Team team = new Team();
        team.setTeamName(team1Name);
        team.createTeamPlayers(team1PlayerNames,team1NumberOfBatsman);
        return team;
    }
    private Team buildTeam2()
    {
        Team team = new Team();
        team.setTeamName(team2Name);
        team.createTeamPlayers(team2PlayerNames,team2NumberOfBatsman);
        return team;
    }
    public Game getGame()
    {
        Game game = new Game();
        game.setBattingTeam(buildTeam1());
        game.setBowlingTeam(buildTeam2());
        game.setOvers(new Overs(totalOvers));
        return game;
    }
}
