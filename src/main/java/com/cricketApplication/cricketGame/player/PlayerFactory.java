package com.circketApplication.cricketGame.player;

import com.circketApplication.dao.entities.PlayerDao;

public class PlayerFactory {
    private static Player getBowler(String playerName)
    {
        return new Bowler(playerName);
    }
    private static Player getBatsman(String playerName)
    {
        return new Batsman(playerName);
    }

    public static Player createPlayer(PlayerDao playerDao){
        Player player;
        if(playerDao.getPlayerType() == "Bowler") {
            player = getBowler(playerDao.getName());
        }
        else {
            player = getBatsman(playerDao.getName());
        }
        return player;
    }
}
