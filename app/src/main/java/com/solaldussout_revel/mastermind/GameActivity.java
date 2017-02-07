package com.solaldussout_revel.mastermind;

import com.solaldussout_revel.mastermind.object.Game;
import com.solaldussout_revel.mastermind.object.Score;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class GameActivity extends MenuParentActivity {

    Game game;
    TextView activeView;
    TextView tourNum;
    Chronometer chrono;
    GridLayout gridColor;
    TableLayout tableScore;
    String[] colors;
    Button validScoreButton;
    Boolean isStart;


    TextView[] colorsButton;
    TextView[] colorsSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.game = MainActivity.getGame();
        setContentView(R.layout.activity_main);
        colors = game.getColors();

        //Get Views
        gridColor = (GridLayout) findViewById(R.id.gridColor);
        tourNum = (TextView) findViewById(R.id.tourNum);
        tableScore = (TableLayout) findViewById(R.id.tableScore);
        validScoreButton = (Button) findViewById(R.id.validScoreButton);

        //Init values and listener
        String numTour = "Tour : "+game.getNumTour();
        tourNum.setText(numTour);

        tableScore.setOnClickListener(setTableClickListener);
        validScoreButton.setOnClickListener(validScoreButtonListener);
        manageButtonListener();


        initChrono();
        initToolbar();
    }


    //////////////////////////////////////////////////////
    //
    //                 INITIALIZER
    //
    //////////////////////////////////////////////////////

    //Récupère les text views et assigne des event listener
    public void manageButtonListener() {
        //Init views
        colorsButton = new TextView[]{
                (TextView) findViewById(R.id.color1Button),
                (TextView) findViewById(R.id.color2Button),
                (TextView) findViewById(R.id.color3Button),
                (TextView) findViewById(R.id.color4Button)
        };

        colorsSelect = new TextView[]{
                (TextView) findViewById(R.id.color1),
                (TextView) findViewById(R.id.color2),
                (TextView) findViewById(R.id.color3),
                (TextView) findViewById(R.id.color4),
                (TextView) findViewById(R.id.color5),
                (TextView) findViewById(R.id.color6),
                (TextView) findViewById(R.id.color7),
                (TextView) findViewById(R.id.color8)
        };

        //Init listener
        for(int i=0; i<colorsButton.length; i++){
            colorsButton[i].setOnFocusChangeListener(setColorButtonFocus);
            colorsButton[i].setOnClickListener(setColorButtonListener);
        }
        for(int i=0; i<colorsSelect.length; i++){
            colorsSelect[i].setOnClickListener(setColorListButtonClick);
        }
    }

    public void initChrono(){
        chrono = (Chronometer) findViewById(R.id.chrono);
        chrono.start();
        isStart = true;
    }


    //////////////////////////////////////////////////////
    //
    //                  CODE GENERATOR
    //
    //////////////////////////////////////////////////////


    //Génère une ligne de tableau
    public void genRowTable(Score last){

        //Init layouts and view
        TableRow tableRow = (TableRow)getLayoutInflater().inflate(R.layout.table_row_layout, null);
        TextView goodPlace = (TextView) tableRow.findViewById(R.id.goodPlace);
        TextView badPlace = (TextView) tableRow.findViewById(R.id.badPlace);
        TextView[] textViewsColors = new TextView[]{
                (TextView) tableRow.findViewById(R.id.square1),
                (TextView) tableRow.findViewById(R.id.square2),
                (TextView) tableRow.findViewById(R.id.square3),
                (TextView) tableRow.findViewById(R.id.square4)
        };

        //Set bubble good place and bad place
        String gPlaceStr = last.getCountGoodPosition().toString();
        String bPlaceStr = last.getCountBadPosition().toString();
        goodPlace.setText(gPlaceStr);
        badPlace.setText(bPlaceStr);

        //Set backgrounds
        for(int i=0; i<textViewsColors.length; i++){
            textViewsColors[i].setBackground(colorsButton[i].getBackground());
        }

        tableScore.addView(tableRow);
    }



    //////////////////////////////////////////////////////
    //
    //              STYLE UPDATE
    //
    //////////////////////////////////////////////////////

    //Remet tout les bouton de combinaison à gris
    public void clearColorButton(){
        for(int i=0; i<colorsButton.length; i++){
            colorsButton[i].setBackground(new ColorDrawable(0xFFCCCCCC));
        }
    }

    //Met à jour le tableau de couleur active à partir de la combinaisons
    public void setActiveColors(){
        colors = new String[]{
                getStrFromColorDraw((ColorDrawable) colorsButton[0].getBackground()),
                getStrFromColorDraw((ColorDrawable) colorsButton[1].getBackground()),
                getStrFromColorDraw((ColorDrawable) colorsButton[2].getBackground()),
                getStrFromColorDraw((ColorDrawable) colorsButton[3].getBackground())
        };
    }


    //////////////////////////////////////////////////////
    //
    //              ACTIONS
    //
    //////////////////////////////////////////////////////

    //Callback du bouton de validation après vérification de la validité des boutons
    public void validTour(){

        //Ajoute un score
        game.addScore(colors);
        this.game.nextTour();

        //Met à jour les vues
        genRowTable(game.getLastScore()); //Génère une ligne de tableau à partir du dernier score
        clearColorButton();

        //Gère la fin du jeu
        if(game.getNumTour()>game.getTourMax()||game.getWon()){

            chrono.stop();
            game.setTimeout(chrono.getBase());

            if(game.getWon()){
                tourNum.setText("Bien joué ! "+game.getNumTour()+" tours");
                manageHighScore();
            } else {
                tourNum.setText(R.string.gameLoseLabel);
            }

        } else {
            String t = "Tour : "+game.getNumTour();
            tourNum.setText(t);
        }
    }



    //Gère l'update du meilleur score
    public void manageHighScore(){
        Long elapsed = SystemClock.elapsedRealtime() - chrono.getBase();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Integer highScoreVal = preferences.getInt("highscore",999999);

        if (highScoreVal > elapsed) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("highscore", elapsed.intValue());
            editor.apply();
        }

    }





    //////////////////////////////////////////////////////
    //
    //                  EVENT LISTENER
    //
    //////////////////////////////////////////////////////


    //Listener sur les boutons du bas
    View.OnClickListener setColorButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.requestFocus();
        }
    };


    //Listener sur les différentes couleurs
    View.OnClickListener setColorListButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (activeView != null) {
                activeView.setBackground(v.getBackground());
                if(!isStart){
                    chrono.start();
                }
            }
        }
    };


    //Listener sur le tableau de rendu
    View.OnClickListener setTableClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tableScore.requestFocus();
        }
    };


    //Listener sur le changement de focus
    View.OnFocusChangeListener setColorButtonFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                activeView = (TextView) v;
                gridColor.animate().translationY(dpToPx(-60)).setDuration(100);
            } else {
                gridColor.animate().translationY(0).setDuration(100);
            }
        }
    };


    //Listener sur le bouton valider
    View.OnClickListener validScoreButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setActiveColors();

            Boolean test = true;
            for(int i=0; i<colors.length; i++){
                if(colors[i].equals("#ffcccccc")){
                    test = false;
                }
            }

            if(test){
                validTour();
            } else {
                System.out.println("Rentrez un truc");
            }
        }
    };


    //////////////////////////////////////////////////////
    //
    //              FONCTION UTILE
    //
    //////////////////////////////////////////////////////


    //Convertis une dimension en dp en px pour les animations
    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


    public String getStrFromColorDraw(ColorDrawable c){
        Integer i = c.getColor();
        return "#"+Integer.toHexString(i);
    }


}
