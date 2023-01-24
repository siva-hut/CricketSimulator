package com.CircketSimu.App.CricketGame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Random;

@Component
public class Game {
    static Random random = new Random();
    private static int getRandomNum()
    {
        return random.nextInt(7);
    }
    private Team[] teams;
    private LocalTime startTime;
    private int battingTeam;
    public void simulateNextBall()
    {
        int generatedNum = Game.getRandomNum();
        if(generatedNum == 5)
            wicketSimulation();
        else
            runSimulation(generatedNum);

    }
    public void wicketSimulation()
    {

    }
    public void runSimulation(int run)
    {

    }
    /*
    Start Time;
    Get Update;
    Send results after every 1 seconds;
    Update score
    End Time;
     */
}