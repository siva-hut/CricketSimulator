package com.circketApplication.service;

import com.circketApplication.cricketGame.Game;
import com.circketApplication.cricketGame.GameBuilder;

public interface GameService {
    Long createGame(GameBuilder gameBuilder);
    void simulateNextBall();
    void addGame(Game game);
    void pauseGame(Long gameId);
    void resumeGame(Long gameId);
}
