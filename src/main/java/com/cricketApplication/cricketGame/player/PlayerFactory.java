package com.cricketApplication.cricketGame.player;

import com.cricketApplication.dao.entities.PlayerDao;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {
    private static Player createPlayer(PlayerDao playerDao) {
        Player player;
        if (playerDao.getPlayerType().equals("Bowler")) {
            player = getBowler(playerDao.getName());
        } else {
            player = getBatsman(playerDao.getName());
        }
        player.setId(playerDao.getId());
        return player;
    }
    public static List<Player> createPlayers(List<PlayerDao>playerDaoList){
        List<Player> playerList = new ArrayList<>();
        for (PlayerDao playerDao:playerDaoList) {
            playerList.add(createPlayer(playerDao));
        }
        return playerList;
    }
    private static Player getBowler(String playerName) {
        return new Bowler(playerName);
    }

    private static Player getBatsman(String playerName) {
        return new Batsman(playerName);
    }
}
