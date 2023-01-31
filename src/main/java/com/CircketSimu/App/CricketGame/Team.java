package com.CircketSimu.App.CricketGame;

import com.github.javafaker.Faker;
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
    private int bowlerIndex;
    public Team(String teamName, String [] playerNames) {
        this.teamName = teamName;
        createTeamPlayers(playerNames);
    }
    public Team(String teamName)
    {
        Faker faker = new Faker();
        String [] fakeNames = new String [11];
        for(int i=0;i<11;i++)
        {
            fakeNames[i] = faker.name().name();
        }
        createTeamPlayers(fakeNames);
    }
    void createTeamPlayers(String [] playerNames)
    {   bowlerIndex = 6;
        players = new Vector<>(11);
        for (int i=0;i<Math.min(playerNames.length,11);i++)
        {   //Need changes
            if(i<=5)
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
    public Player getBowler()
    {  // System.out.println(bowlerIndex);
        return players.get(bowlerIndex);
    }
    public void changeBowler()
    {
        bowlerIndex++;
        System.out.println(bowlerIndex);
        if(bowlerIndex>=players.size())
        bowlerIndex = 6;
    }
}