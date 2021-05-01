package com.company.models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    public static final String BATTING = "BATTING";
    public static final int MIN_SCORE = 4;
    private  String name;
    private List<Player> players; //Player container
    private int totalScore;
    private int totalWickets;
    private int currentBatsman;
    private boolean hasWonToss;

    public Team(String name,int noOfPlayers) {
        this.name = name;
        this.totalScore=0;
        this.totalWickets=-1;
        this.currentBatsman=-1;
        this.hasWonToss=false;
        setPlayers(noOfPlayers);
    }
    private void setPlayers(int noOfPlayers){
        this.players=new ArrayList<>();

        for(int i=0; i<(noOfPlayers/2);i++){
            Player player=new Player(String.valueOf(i));
            players.add(player);
        }

        for(int i= (noOfPlayers/2); i<noOfPlayers;i++){
            Player player=new SuperPlayer(String.valueOf(i), MIN_SCORE);
            players.add(player);
        }
    }

    public int getTotalWickets() {
        return this.totalWickets;
    }

    public int getTotalScore() {
        return this.totalScore;
    }
    public boolean hasWonToss(){
        return this.hasWonToss;
    }

    public void setHasWonToss(boolean wonToss) {
        this.hasWonToss = wonToss;
    }

    public String getName(){
        return  this.name;
    }

    public Player getNextPlayer() {
        this.currentBatsman++;
        if(this.currentBatsman>= this.players.size()){
            return null;
        }

        this.totalWickets++;

        Player nextPlayer=this.players.get(this.currentBatsman);
        nextPlayer.setStatus(BATTING);

        return nextPlayer;
    }


    public void updateTotalScore(int result) {
        this.totalScore+=result;
    }

    public String getSummary() {
        return ""+ this.totalScore +"/" + this.totalWickets ;
    }



}
