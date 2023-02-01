package com.circketApplication.cricketGame.player;

public class PlayerFactory {
    public Player getBowler(String playerName)
    {
        return new Bowler(playerName);
    }
    public Player getBatsman(String playerName)
    {
        return new Batsman(playerName);
    }
}
