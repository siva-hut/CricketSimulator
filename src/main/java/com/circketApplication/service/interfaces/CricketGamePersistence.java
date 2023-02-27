package com.circketApplication.service.interfaces;

import com.circketApplication.cricketGame.Game;
import com.circketApplication.cricketGame.player.Player;
import com.circketApplication.dao.entities.GameDao;

import java.util.Date;

public interface CricketGamePersistence {
    void persistGameCreation(Game game);
    void persistGameCreation(Game game, Date date);
    void persist(String teamName);
    void persistBallData(Game game);
    void persistGameOnCompletion(Game game);

    void persistNewPlayer(Player player,String teamName);
    Game reloadGame(Long gameId);
    Game reloadGame(GameDao gameDao);
}
