package com.circketApplication.cricketGame.player;

public class PlayerFactory {
    public static Player getBowler(String playerName)
    {
        return new Bowler(playerName);
    }
    public static Player getBatsman(String playerName)
    {
        return new Batsman(playerName);
    }
}
