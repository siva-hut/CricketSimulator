package com.CircketSimu.App.CricketGame;

public class Overs {
    private int number;
    private int decimal;
    private int totalOvers;
    public Overs(int totalOvers)
    {
        this.number=0;
        this.decimal=0;
        this.totalOvers = totalOvers;
    }
    public String getOvers()
    {
        return String.format("%d.%d",number,decimal);
    }
    public void nextBall()
    {
        decimal++;
        if(decimal==6)
        {
            number++;
            decimal = 0;
        }
    }
    public boolean overCompleted()
    {
        if(decimal==0)
            return true;
        return false;
    }
    public int ballsRemaining()
    {
        return (totalOvers*6) - ((number*6)+ decimal);
    }
    public void reset()
    {
        this.number=0;
        this.decimal=0;
    }
    public int oversLeft()
    {
        return totalOvers-number;
    }
}
