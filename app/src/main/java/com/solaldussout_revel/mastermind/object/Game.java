package com.solaldussout_revel.mastermind.object;

import android.annotation.TargetApi;
import android.annotation.TargetApi;
import java.lang.Long;
import java.util.Random;

/**
 * Created by Solal on 03/02/2017.
 */


public class Game {
    Score[] scores;
    Integer numTour;
    Integer tourMax = 12;
    String[] secretsComb;
    String[] colors;

    public Game() {
        this.setNumTour(0);
        this.setColors();
        this.generateSecretComb();

    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
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

    public String[] getSecretsComb() {
        return secretsComb;
    }

    public void setSecretsComb(String[] secretsComb) {
        this.secretsComb = secretsComb;
    }

    public String[] generateSecretComb(){
        String[] secretsComb = new String[4];
        for(int i=0; i<secretsComb.length; i++){
            secretsComb[i] = this.getRandomHex();
        }
        setSecretsComb(secretsComb);
        return secretsComb;
    }

    public void setColors(){
        String[] colorsSet = new String[8];

        colorsSet[0] = "#ffe10000";
        colorsSet[1] = "#fff6fe00";
        colorsSet[2] = "#ff00de2c";
        colorsSet[3] = "#ff006fde";
        colorsSet[4] = "#fffead44";
        colorsSet[5] = "#fffefefe";
        colorsSet[6] = "#ff8000d5";
        colorsSet[7] = "#fff600fe";

        this.setColors(colorsSet);
    }


    private String getRandomHex(){
        String[] colorRef = this.getColors();

        Double r = Math.random();
        Double l = Double.valueOf(colorRef.length);
        Double rankDouble = Math.floor(r*l);
        return colorRef[rankDouble.intValue()];
    }



    public void addScore(String[] actual){
        Score[] actScores = this.getScores();
        Score[] newScores;
        Score newScore;
        String[] actualScore = new String[actual.length];

        //On rajoute un champs au tableau de score
        if(actScores!=null){
            newScores = new Score[actScores.length+1];
        } else {
            newScores = new Score[1];
        }


        //On définis le nouveau score
        for(int i=0; i<actual.length; i++){
            actualScore[i] = actual[i];
        }
        newScore = new Score(this.getSecretsComb(), actualScore);

        if(actScores!=null){
            for(int i = 0; i<actScores.length; i++){
                newScores[i] = actScores[i];
            }
        }
        newScores[newScores.length-1] = newScore;

        this.setScores(newScores);
    };

    public Score getLastScore(){
        Score[] actScores = getScores();
        if(actScores!=null){
            return actScores[actScores.length-1];
        } else {
            return null;
        }
    }

    public void nextTour(){
        this.setNumTour(this.getNumTour()+1);
    }
}
