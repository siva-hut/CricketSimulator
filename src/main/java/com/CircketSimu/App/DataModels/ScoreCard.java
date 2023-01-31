package com.CircketSimu.App.DataModels;

import com.CircketSimu.App.CricketGame.Game;
import org.springframework.stereotype.Component;

public class ScoreCard {
    String bowlerName;
    String wicketsTaken;
    String batsmanName;
    String batsmanScore;
    String currentBallScore;

    public void setBowlerName(String bowlerName) {
        this.bowlerName = bowlerName;
    }

    public void setWicketsTaken(String wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public void setBatsmanName(String batsmanName) {
        this.batsmanName = batsmanName;
    }

    public void setBatsmanScore(String batsmanScore) {
        this.batsmanScore = batsmanScore;
    }

    public void setCurrentBallScore(String currentBallScore) {
        this.currentBallScore = currentBallScore;
    }

    public void setBallsRemaining(String ballsRemaining) {
        this.ballsRemaining = ballsRemaining;
    }
    @Override
    public String toString()
    {
        return bowlerName+" "+ballsRemaining+" "+wicketsTaken+" "+batsmanName+" "+batsmanScore+" "+currentBallScore;
    }
    String ballsRemaining;

}
