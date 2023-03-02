package com.circketApplication.cricketGame;

import com.circketApplication.cricketGame.util.Overs;
public class GameBuilder {
    String team1Name ;
    String team2Name ;
    int totalOvers = 20;
    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public void setTotalOvers(int totalOvers) {
        this.totalOvers = totalOvers;
    }
    private Team buildTeam(String teamName)
    {
        Team team = new Team();
        team.setTeamName(teamName);
        return team;
    }
    private void setTeamNames(){
        if(team1Name==null)
        {
            team1Name = "Default_Team1";
        }
        if(team2Name==null)
        {
            team2Name = "Default_Team2";
        }
    }
    public Game getGame()
    {
        Game game = new Game();
        setTeamNames();
        game.setBattingTeam(buildTeam(team1Name));
        game.setBowlingTeam(buildTeam(team2Name));
        game.setOvers(new Overs(totalOvers));
        return game;
    }
}
