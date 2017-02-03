package com.solaldussout_revel.mastermind.object;

import android.annotation.TargetApi;


import android.annotation.TargetApi;

/**
 * Created by Solal on 03/02/2017.
 */

import java.util.Random;


public class Game {
    Score[] scores;
    Integer numTour;
    Long[] secretsComb;

    public Game() {
        this.generateSecretComb();
    }

    public Score[] getScores() {
        return scores;
    }

    public void setScores(Score[] scores) {
        this.scores = scores;
    }

    public Integer getNumTour() {
        return numTour;
    }

    public void setNumTour(Integer numTour) {
        this.numTour = numTour;
    }

    public Long[] getSecretsComb() {
        return secretsComb;
    }

    public void setSecretsComb(Long[] secretsComb) {
        this.secretsComb = secretsComb;
    }

    public Long[] generateSecretComb(){
        Long[] secretsComb = new Long[4];

        for(int i=0; i<secretsComb.length; i++){
            secretsComb[i] = Long.valueOf(this.getRandomHexString(6));
            System.out.println(secretsComb[i]);
        }
        setSecretsComb(secretsComb);
        return secretsComb;
    }

    private String getRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, numchars);
    }


    private void addScore(long hexa){

    };
}
