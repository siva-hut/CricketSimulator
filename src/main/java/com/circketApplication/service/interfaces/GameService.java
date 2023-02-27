package com.circketApplication.service.interfaces;

import com.circketApplication.cricketGame.Game;
import com.circketApplication.cricketGame.GameBuilder;
import com.circketApplication.dao.entities.GameDao;

import java.util.Date;

public interface GameService {
    Long createGame(GameBuilder gameBuilder);
    void simulateNextBall();
    void addGame(Game game);
    void pauseGame(Long gameId);
    void resumeGame(Long gameId);
    void resumeGame(GameDao gameDao);
    Long scheduleGame(GameBuilder gameBuilder, Date date);
}
