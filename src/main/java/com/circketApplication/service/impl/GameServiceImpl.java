package com.circketApplication.service.impl;

import com.circketApplication.cricketGame.Game;
import com.circketApplication.cricketGame.GameBuilder;
import com.circketApplication.service.CricketGamePersistence;
import com.circketApplication.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private CricketGamePersistence cricketGamePersistence;
    @Override
    public Long createGame(GameBuilder gameBuilder) {
        Game game =  gameBuilder.getGame();
        cricketGamePersistence.persistGameCreation(game);
        addGame(game);
        System.out.println(activeGameArray.indexOf(game));
        return game.getId();
    }
    public static ArrayList<Game> activeGameArray = new ArrayList<Game>();
    public static Map<Long, Integer> hashMap = new HashMap<Long, Integer>();
    @Scheduled(fixedRate=1000)
    public void simulateNextBall()
    {
        Iterator<Game> itr= activeGameArray.iterator();
        while(itr.hasNext()){
            Game game = itr.next();
            game.simulateNextBall();
            cricketGamePersistence.persistBallData(game);
            if(game.isGameOver()) {
                //UPDATE TEAM WINS
                //UPDATE PLAYER AND PLAYER STATS
                //UPDATE TEAMSTATS
                cricketGamePersistence.persistGameOnCompletion(game);
                itr.remove();
            }
        }
    }
    @Override
    public void addGame(Game game)
    {
        hashMap.put(game.getId(),activeGameArray.size());
        activeGameArray.add(game);
    }
    @Override
    public void pauseGame(Long gameId){
        activeGameArray.remove((int)hashMap.get(gameId));
        System.out.println(activeGameArray.size());
    }
}
