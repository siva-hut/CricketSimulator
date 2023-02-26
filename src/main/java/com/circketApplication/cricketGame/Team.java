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
    private Vector<Player> players;
    private Player batsmanOnStrike;
    private Player batsmanOffStrike;
    private String teamName;
    private int score = 0;

    private int wicketsLost = 0;
    private int bowlerIndex = 8;
    private Overs battingOvers;
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
        batsmanOffStrike = players.get(1);
        notDoneBatting[1] = true;
    }
    public void createTeamPlayers(List<PlayerDao> playerDaos)
    {
        Faker faker = new Faker();
        players = new Vector<>(11);
        if(playerDaos==null)
            playerDaos = new ArrayList<>();
        while (playerDaos.size()<11) {
            playerDaos.add(PlayerDao.builder().
                    name(faker.name().name()).
                    teamName(teamName).playerType(RandomGenerator.
                            getRandomGenerator().
                            getRandomPlayer()).build());

        }
        for (int i=0;i<11;i++) {
            PlayerDao playerDao = playerDaos.get(i);
            Player player;
            if(playerDao.getPlayerType()==null)
            {
                int random = new Random().nextInt(2);
                if(random==1)
                    playerDao.setPlayerType("Bowler");
                else
                    playerDao.setPlayerType("Batsman");
            }
            if(playerDao.getPlayerType().equals("Bowler")) {
                player = PlayerFactory.getBowler(playerDao.getName());
            }
            else{
                player = PlayerFactory.getBatsman(playerDao.getName());
            }
            player.setId(playerDao.getId());
//            player.setBallsFaced(playerDao.getBallsFaced());
//            player.setOversBowled(new Overs(0,playerDao.getBallsBowled()));
//            player.setRunsScored(playerDao.getRunsScored());
//            player.setWicketsTaken(playerDao.getWicketsTaken());
//            player.setRunsGiven(playerDao.getRunsGiven());
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
    public void changeStrike()
    {
        Player duplicate = batsmanOnStrike;
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