package com.cricketApplication.service.interfaces;

import com.cricketApplication.cricketGame.Game;
import com.cricketApplication.cricketGame.GameBuilder;
import com.cricketApplication.dao.entities.GameDao;

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
