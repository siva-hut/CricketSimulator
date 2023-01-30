package com.CircketSimu.App.CricketGame;

public class GameController {
    public static void simulateNextBall(Game game)
    {
        char run = game.getBatsman().simulateRun();
        if(run == 'w')
            game.getBattingTeam().increaseWicketLost();
        else
        {   int nRun = run-'0' ;
            game.getBattingTeam().getBatsman().updateRun(nRun);
            game.getBattingTeam().increaseScore(nRun);
        }

        game.getOvers().nextBall();
        checkGameStatus(game);
    }
    private static void checkGameStatus(Game game)
    {
        if(game.getInnings() == 1)
        {
            if(game.getOvers().ballsRemaining() == 0 || game.getBattingTeam().getWicketsLost()==10)
                game.switchSides();
        }
        if(game.getInnings() == 2)
        {
            Boolean condition1 = game.getOvers().ballsRemaining() == 0 || game.getBattingTeam().getWicketsLost()==10;
            Boolean condition2 = game.getBattingTeam().getScore()>game.getBowlingTeam().getScore();;
            if(condition1 || condition2)
                game.setGameOver(true);
        }
    }
    public void result(Game game)
    {
        System.out.println(game);
    }

}
