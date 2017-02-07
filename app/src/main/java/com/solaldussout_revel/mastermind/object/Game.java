package com.solaldussout_revel.mastermind.object;

import java.lang.Long;


public class Game {
    private Score[] scores;
    private Integer numTour;
    private Integer tourMax;
    private String[] secretsComb;
    private String[] colors;
    private Long timeout;
    private Boolean won;

    public Game() {
        this.setNumTour(1);
        this.setColors();
        this.setTourMax(12);
        this.setWon(false);
        this.generateSecretComb();
    }

    //////////////////////////////////////////////////////
    //
    //                  INITIALIZER
    //
    //////////////////////////////////////////////////////

    //Initialize colors possible
    private void setColors(){
        String[] colorsSet = new String[]{ "#ffe10000", "#fff6fe00", "#ff00de2c", "#ff006fde", "#fffead44", "#fffefefe", "#ff8000d5", "#fff600fe"};
        this.setColors(colorsSet);
    }


    //////////////////////////////////////////////////////
    //
    //                  ACTIONS
    //
    //////////////////////////////////////////////////////


    //Génère un tableau secret de combinaison à partir des couleurs initialisé précédemment
    private String[] generateSecretComb(){

        String[] secretsComb = new String[4];

        for(int i=0; i<secretsComb.length; i++){
            secretsComb[i] = this.getRandomColor();
        }

        setSecretsComb(secretsComb);
        return secretsComb;

    }

    //Rajoute un scores à la liste de score de game
    public void addScore(String[] actual){

        Score[] actScores = this.getScores(); //Actual scores tab
        Score[] newScores;
        newScores  = actScores!=null ? new Score[actScores.length+1] : new Score[1];


        //Hydrate le tableau new scores depuis
        if(actScores!=null){
            for(int i = 0; i<actScores.length; i++){
                newScores[i] = actScores[i];
            }
        }
        newScores[newScores.length-1] = new Score(this.getSecretsComb(), actual);
        this.setScores(newScores);


        //On test si le jeu est gagné
        isGameWon();

    };

    //Increment property numTour
    public void nextTour(){
        this.setNumTour(this.getNumTour()+1);
    }

    //////////////////////////////////////////////////////
    //
    //                  TEST
    //
    //////////////////////////////////////////////////////

    //Récupère le dernier score et test si le jeu est gagné
    private void isGameWon(){
        Score lastScore = this.getLastScore();
        if(lastScore.getCountGoodPosition() == 4){
            this.setWon(true);
        }
    }



    //////////////////////////////////////////////////////
    //
    //                  GETTERS COSTUM
    //
    //////////////////////////////////////////////////////


    //Récupère le dernier score
    public Score getLastScore(){
        Score[] actScores = getScores();
        if(actScores!=null){
            return actScores[actScores.length-1];
        } else {
            return null;
        }
    }

    //Retourne une couleur aléatoire dans le tableau de couleurs initialisé précédemmment
    private String getRandomColor(){
        String[] colorRef = this.getColors();
        Double r = Math.random();
        Double l = ((Integer) colorRef.length).doubleValue();
        Double rankDouble = Math.floor(r*l);
        return colorRef[rankDouble.intValue()];
    }


    //////////////////////////////////////////////////////
    //
    //                  GETTERS & SETTERS
    //
    //////////////////////////////////////////////////////

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public Boolean getWon() {
        return won;
    }

    private void setWon(Boolean won) {
        this.won = won;
    }

    public Integer getTourMax() {
        return tourMax;
    }

    private void setTourMax(Integer tourMax) {
        this.tourMax = tourMax;
    }

    public String[] getColors() {
        return colors;
    }

    private void setColors(String[] colors) {
        this.colors = colors;
    }

    private Score[] getScores() {
        return scores;
    }

    private void setScores(Score[] scores) {
        this.scores = scores;
    }

    public Integer getNumTour() {
        return numTour;
    }

    private void setNumTour(Integer numTour) {
        this.numTour = numTour;
    }

    public String[] getSecretsComb() {
        return secretsComb;
    }

    private void setSecretsComb(String[] secretsComb) {
        this.secretsComb = secretsComb;
    }

}
