package com.circketApplication.cricketGame;

import com.circketApplication.cricketGame.player.Batsman;
import com.circketApplication.cricketGame.player.Bowler;
import com.circketApplication.cricketGame.player.Player;
import com.circketApplication.cricketGame.player.PlayerFactory;
import com.circketApplication.cricketGame.util.RandomGenerator;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Team {
    private boolean[] notDoneBatting = new boolean[11];
    private Vector<Player> players;
    private Player batsmanOnStrike;
    private Player batsmanOffStrike;
    private String teamName;
    private int score = 0;
    private int wicketsLost = 0;
    private int bowlerIndex = 8;
    void createTeamPlayers(ArrayList<String> playerNames, int numberOfBatsman)
    {
        Faker faker = new Faker();
        players = new Vector<>(11);
        while (playerNames.size()<11)
        {
            playerNames.add(faker.name().name());
        }
        for (int i=0;i<11;i++)
        {   if(i<numberOfBatsman)
            {
                players.add(i, PlayerFactory.getBatsman(playerNames.get(i)));
            }
            else
            {
                players.add(i, PlayerFactory.getBowler(playerNames.get(i)));
            }
        }
        batsmanOnStrike = players.get(0);
        notDoneBatting[0] = true;
        notDoneBatting[1] = true;
        batsmanOffStrike = players.get(1);
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
    public Player getBatsmanOnStrike()
    {
        return batsmanOnStrike;
    }
    public Player getBatsmanOffStrike()
    {
        return batsmanOffStrike;
    }
    public void changeStrike()
    {   Player duplicate = batsmanOnStrike;
        batsmanOnStrike = batsmanOffStrike;
        batsmanOffStrike = duplicate;
    }
    public void nextBatsman()
    {
        int index = players.indexOf(batsmanOnStrike)+1;
        while(index<notDoneBatting.length)
        {
            if(!notDoneBatting[index])
            {
                batsmanOnStrike = players.get(index);
                notDoneBatting[index] = true;
                break;
            }
            index++;
        }
        if(index == 11)
        {
            batsmanOnStrike =  PlayerFactory.getBowler("NULL");
        }
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