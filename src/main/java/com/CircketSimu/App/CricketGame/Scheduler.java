package com.CircketSimu.App.CricketGame;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class Scheduler {
    public static ArrayList<Game> activeGameArray = new ArrayList<Game>();
    @Scheduled(fixedRate=10)
    public static void runGame()
    {
        Iterator<Game> itr= activeGameArray.iterator();
        while(itr.hasNext()){
            Game game = itr.next();
            GameController.simulateNextBall(game);
            if(game.isGameOver()) {
               /* if (game.getWinner() == null)
                    System.out.println("Draw");
                else
                    System.out.println("Winner " + game.getWinner().getTeamName());
                activeGamesId.remove(game.getGameId());*/
                itr.remove();
            }
            System.out.println(game.getBattingTeam().getScore());
        }
    }
    public static void addGame(Game game)
    {
        activeGameArray.add(game);
    }
}
