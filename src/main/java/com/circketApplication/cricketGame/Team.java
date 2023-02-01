package com.circketApplication.cricketGame;

import com.circketApplication.cricketGame.player.Batsman;
import com.circketApplication.cricketGame.player.Bowler;
import com.circketApplication.cricketGame.player.Player;
import com.circketApplication.cricketGame.util.RandomGenerator;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Vector;

public class Team {
    private Vector<Player> players;
    private String teamName;
    private int score = 0;
    private int wicketsLost = 0;
    private int bowlerIndex = 8;
    void createTeamPlayers(ArrayList<String> playerNames, int numberOfBatsman)
    {   Faker faker = new Faker();
        players = new Vector<>(11);
        while (playerNames.size()<11)
        {
            playerNames.add(faker.name().name());
        }
        for (int i=0;i<11;i++)
        {   if(i<numberOfBatsman)
            {
                players.add(i, new Batsman(playerNames.get(i)));
            }
            else
            {
                players.add(i, new Bowler(playerNames.get(i)));
            }
        }
    }
    public int getScore() {
        return score;
    }
    public int getWicketsLost() {
        return wicketsLost;
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
    {
        return players.get(bowlerIndex);
    }
    public void changeBowler()
    {
        bowlerIndex = RandomGenerator.random.nextInt(4,11);
    }
}