package com.circketApplication.cricketGame.player;

public class PlayerFactory {
    private static Player nullPlayer =new Batsman("NULL");

    public static Player getBowler(String playerName)
    {
        return new Bowler(playerName);
    }
    public static Player getBatsman(String playerName)
    {
        return new Batsman(playerName);
    }
    public static Player getNullPlayer()
    {
        return nullPlayer;
    }
}
