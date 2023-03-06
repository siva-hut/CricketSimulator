package com.circketApplication.cricketGame.util;

public class Overs {
    private int number;
    private int decimal;
    private int totalOvers;
    private boolean reball = false;
    public int getNumberOfBalls()
    {
        return (number*6)+decimal;
    }
    public int getTotalOvers() {
        return totalOvers;
    }
    public Overs(int totalOvers,int ballsBowled)
    {
        number = ballsBowled/6;
        decimal = ballsBowled%6;
    }
    public Overs(int totalOvers)
    {
        this.number=0;
        this.decimal=0;
        this.totalOvers = totalOvers;
    }
    public void reBall()
    {
        reball = true;
    }
    public String getOvers()
    {
        return String.format("%d.%d",number,decimal);
    }
    public float getOversInFloat(){
        float result = decimal;
        result/=10;
        result+=number;
        return result;
    }
    public void nextBall()
    {   if(!reball) {
        decimal++;
        if (decimal == 7) {
            number++;
            decimal = 1;
        }
        }
        else{
            reball = false;
        }
    }
    public boolean overCompleted()
    {
        if(decimal==1 && number!=0)
        {
            return true;
        }
        return false;
    }
    public int ballsRemaining()
    {
        return (totalOvers*6) - ((number*6)+ decimal);
    }
    public int oversLeft()
    {
        return totalOvers-number;
    }
}
