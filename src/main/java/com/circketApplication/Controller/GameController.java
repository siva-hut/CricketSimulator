package com.circketApplication.Controller;

import com.circketApplication.cricketGame.Game;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class GameController {
    public static ArrayList<Game> activeGameArray = new ArrayList<Game>();
    /*
    @Scheduled(fixedRate=200)
    public static void runGame()
    {
        Iterator<Game> itr= activeGameArray.iterator();
        while(itr.hasNext()){
            Game game = itr.next();
            System.out.println(game.simulateNextBall().toString());
            if(game.isGameOver()) {
                itr.remove();
            }

        }
    }
    public static void addGame(Game game)
    {
        activeGameArray.add(game);
    }*/
}
