package com.CircketSimu.App.CricketGame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Vector;

public class Team {
    private Vector<Player> players;
    private String teamName;
    private int score;
    private int wicketsLost;
    public Team(String teamName, String [] playerNames) {
        this.teamName = teamName;
        score = 0;
        wicketsLost = 0;
        createTeamPlayers(playerNames);
    }
    void createTeamPlayers(String [] playerNames)
    {
        System.out.println(playerNames.length);
        players = new Vector<>(11);
        for (int i=0;i<11;i++)
        {   if(i<=5)
            players.add(i, new Batsman(playerNames[i]));
        else
            players.add(i, new Bowler(playerNames[i]));

        }
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWicketsLost() {
        return wicketsLost;
    }

    public void setWicketsLost(int wicketsLost) {
        this.wicketsLost = wicketsLost;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public Player getBatsman()
    {
        return players.get(wicketsLost);
    }
    public void increaseWicketLost()
    {
        wicketsLost++;
    }
    public void increaseScore(int run)
    {
        score+=run;
    }
}