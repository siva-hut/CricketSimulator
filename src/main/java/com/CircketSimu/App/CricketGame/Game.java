package com.CircketSimu.App.CricketGame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

public class Game {
    UUID gameId;
    private LocalTime startTime;
    private Team battingTeam;
    private Team bowlingTeam;
    private Overs overs;
    private int innings;
    private boolean gameOver;

    public Overs getOvers() {
        return overs;
    }

    public UUID getGameId() {
        return gameId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public Team getBattingTeam() {
        return battingTeam;
    }

    public Team getBowlingTeam() {
        return bowlingTeam;
    }

    public void setOvers(Overs overs) {
        this.overs = overs;
    }

    public int getInnings() {
        return innings;
    }

    public void setInnings(int innings) {
        this.innings = innings;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Game(Team battingTeam, Team bowlingTeam, int totalOvers)
    {
        startTime = LocalTime.now();
        overs = new Overs(totalOvers);
        this.battingTeam = battingTeam;
        this.bowlingTeam =  bowlingTeam;
        innings = 1;
        gameOver = false;
        gameId = UUID.randomUUID();
    }
    public Player getBatsman()
    {
        return battingTeam.getBatsman();
    }
    public void switchSides()
    {   innings++;
        Team duplicate = battingTeam;
        battingTeam = bowlingTeam;
        bowlingTeam = duplicate;
        overs.reset();
    }
}