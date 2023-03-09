package com.cricketApplication.service.impl;

import com.cricketApplication.dao.entities.GameDao;
import com.cricketApplication.dao.repositories.GameRepository;
import com.cricketApplication.service.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service
public class BackGroundService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameService gameService;
//    Runs once everytime the application starts
    @EventListener(ApplicationReadyEvent.class)
    public void startPastGames(){
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        List<GameDao> gameDaoList = gameRepository.findByEndDateIsNullAndStartDateLessThan(currentDate);
        for (GameDao gameDao:gameDaoList) {
            gameService.resumeGame(gameDao);
        }
    }
    //Runs every second
    @Scheduled(fixedRate=1000)
    public void StartFutureGames(){
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        List<GameDao> gameDaoList = gameRepository.
                findByEndDateIsNullAndStartDateLessThanAndGameActive(currentDate,false);
        for (GameDao gameDao:gameDaoList) {
            gameService.resumeGame(gameDao);
        }
    }
}
