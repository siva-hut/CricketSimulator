package com.circketApplication.cricketGame;

import com.circketApplication.cricketGame.player.Player;
import com.circketApplication.cricketGame.player.PlayerFactory;
import com.circketApplication.cricketGame.util.Overs;
import com.circketApplication.cricketGame.util.RandomGenerator;
import com.circketApplication.dao.entities.PlayerDao;
import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
@Getter
@Setter
public class Team {
    private Long id;
    private boolean[] notDoneBatting = new boolean[11];
    private ArrayList<Player> players = new ArrayList<>(11);
    private Player batsmanOnStrike;
    private Player batsmanOffStrike;
    private String teamName;
    private int score = 0;

    private int wicketsLost = 0;
    private int bowlerIndex = 8;
    private Overs battingOvers;
    public void createTeamPlayers(List<PlayerDao> playerDaos)
    {
        for (PlayerDao playerDao:playerDaos) {
            Player player = PlayerFactory.createPlayer(playerDao);
            players.add(player);
        }
        batsmanOnStrike = players.get(0);
        notDoneBatting[0] = true;
        batsmanOffStrike = players.get(1);
        notDoneBatting[1] = true;
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
    protected void changeStrike()
    {
        Player duplicate = batsmanOnStrike;
        batsmanOnStrike = batsmanOffStrike;
        batsmanOffStrike = duplicate;
    }
    protected void nextBatsman()
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
    }
    protected void increaseWicketLost()
    {
        wicketsLost++;
    }
    protected void increaseScore(int run)
    {
        score+=run;
    }
    public Player getBowler()
    {
        return players.get(bowlerIndex);
    }
    protected void changeBowler()
    {
        bowlerIndex++;
        if(bowlerIndex==11)
            bowlerIndex=7;
    }

}