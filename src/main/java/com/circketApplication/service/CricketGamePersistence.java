package com.circketApplication.service;

import com.circketApplication.cricketGame.Game;
import com.circketApplication.cricketGame.player.Player;

public interface CricketGamePersistence {
    void persistGameCreation(Game game);
    void persist(String teamName);
    void persistBallData(Game game);
    void persistGameOnCompletion(Game game);
    void persistNewPlayer(Player player,String teamName);
    void reloadGame(Game game);
}
