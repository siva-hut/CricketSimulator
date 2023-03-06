package com.cricketApplication.cricketGame.player;

import com.cricketApplication.dao.entities.PlayerDao;

public class PlayerFactory {
    public static Player createPlayer(PlayerDao playerDao) {
        Player player;
        if (playerDao.getPlayerType() == "Bowler") {
            player = getBowler(playerDao.getName());
        } else {
            player = getBatsman(playerDao.getName());
        }
        return player;
    }

    private static Player getBowler(String playerName) {
        return new Bowler(playerName);
    }

    private static Player getBatsman(String playerName) {
        return new Batsman(playerName);
    }
}
