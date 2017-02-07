package com.solaldussout_revel.mastermind;

import com.solaldussout_revel.mastermind.object.Game;
import com.solaldussout_revel.mastermind.object.Score;


import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MainActivity extends MenuParentActivity {

    Game game;
    TextView activeView;
    TextView tourNum;
    Chronometer chrono;
    GridLayout gridColor;
    TableLayout tableScore;
    String[] colors;
    Button validScoreButton;
    Toolbar toolbar;
    Boolean isStart;

    TextView color1Button;
    TextView color2Button;
    TextView color3Button;
    TextView color4Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.game = NewGameActivity.getGame();
        setContentView(R.layout.activity_main);
        colors = game.getColors();


        gridColor = (GridLayout) findViewById(R.id.gridColor);

        chrono = (Chronometer) findViewById(R.id.chrono);
        chrono.start();
        isStart = true;
        tourNum = (TextView) findViewById(R.id.tourNum);
        tourNum.setText("Tour : "+game.getNumTour());

        tableScore = (TableLayout) findViewById(R.id.tableScore);
        tableScore.setOnClickListener(setTableClickListener);

        validScoreButton = (Button) findViewById(R.id.validScoreButton);
        validScoreButton.setOnClickListener(validScoreButtonListener);

        manageButtonListener();

        initToolbar();
    }




    public void genRowTable(Score last){
        TableRow tableRow = (TableRow)getLayoutInflater().inflate(R.layout.table_row_layout, null);
        TextView[] textViewsColors = new TextView[4];

        textViewsColors[0] = (TextView) tableRow.findViewById(R.id.square1);
        textViewsColors[1] = (TextView) tableRow.findViewById(R.id.square2);
        textViewsColors[2] = (TextView) tableRow.findViewById(R.id.square3);
        textViewsColors[3] = (TextView) tableRow.findViewById(R.id.square4);

        TextView goodPlace = (TextView) tableRow.findViewById(R.id.goodPlace);
        TextView badPlace = (TextView) tableRow.findViewById(R.id.badPlace);

        goodPlace.setText(last.getCountGoodPosition().toString());
        badPlace.setText(last.getCountBadPosition().toString());


        textViewsColors[0].setBackground(color1Button.getBackground());
        textViewsColors[1].setBackground(color2Button.getBackground());
        textViewsColors[2].setBackground(color3Button.getBackground());
        textViewsColors[3].setBackground(color4Button.getBackground());

        tableScore.addView(tableRow);
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public void clearColorButton(){
        color1Button.setBackground(new ColorDrawable(0xFFCCCCCC));
        color2Button.setBackground(new ColorDrawable(0xFFCCCCCC));
        color3Button.setBackground(new ColorDrawable(0xFFCCCCCC));
        color4Button.setBackground(new ColorDrawable(0xFFCCCCCC));
    }

    View.OnClickListener validScoreButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            colors = new String[]{
                getStrFromColorDraw((ColorDrawable) color1Button.getBackground()),
                getStrFromColorDraw((ColorDrawable) color2Button.getBackground()),
                getStrFromColorDraw((ColorDrawable) color3Button.getBackground()),
                getStrFromColorDraw((ColorDrawable) color4Button.getBackground())
            };



            Boolean test = true;
            for(int i=0; i<colors.length; i++){
                if(colors[i].equals("#ffcccccc")){
                    test = false;
                }
            }

            if(test==true){
                validTour();
            } else {
                //Lancer un toast
            }
        }
    };

    public String getStrFromColorDraw(ColorDrawable c){
        Integer i = c.getColor();
        String h = "#"+Integer.toHexString(i);
        return h;
    }

    public void validTour(){
        //Rajouter une ligne dans le tableau
        game.addScore(colors);
        Score last = game.getLastScore();

        genRowTable(last);
        clearColorButton();
        this.game.nextTour();

        if(game.getNumTour()>game.getTourMax()||game.getWon() == true){
            chrono.stop();
            game.setTimeout(chrono.getBase());
            if(game.getWon() == true){
                tourNum.setText("Bien joué ! "+game.getNumTour()+" tours");
                manageHighScore();
            } else {
                tourNum.setText("Game Over Bitch");
            }
        } else {
            tourNum.setText("Tour : "+game.getNumTour());
        }
    }

    View.OnClickListener setColorButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.requestFocus();
        }
    };

    public void manageHighScore(){

        Long elapsed = SystemClock.elapsedRealtime() - chrono.getBase();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Integer highScoreVal = preferences.getInt("highscore",999999);

        if (highScoreVal > elapsed) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("highscore", elapsed.intValue());
            editor.commit();
        }

    }


    View.OnClickListener setColorListButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (activeView != null) {
                activeView.setBackground(v.getBackground());
                if(isStart == false){
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                }
            }
        }
    };

    View.OnClickListener setTableClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tableScore.requestFocus();
        }
    };


    View.OnFocusChangeListener setColorButtonFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                activeView = (TextView) v;
                gridColor.animate().translationY(-60);
            } else {
                gridColor.animate().translationY(0);
            }
        }
    };


    public void manageButtonListener() {
        color1Button = (TextView) findViewById(R.id.color1Button);
        color2Button = (TextView) findViewById(R.id.color2Button);
        color3Button = (TextView) findViewById(R.id.color3Button);
        color4Button = (TextView) findViewById(R.id.color4Button);

        color1Button.setOnFocusChangeListener(setColorButtonFocus);
        color2Button.setOnFocusChangeListener(setColorButtonFocus);
        color3Button.setOnFocusChangeListener(setColorButtonFocus);
        color4Button.setOnFocusChangeListener(setColorButtonFocus);

        color1Button.setOnClickListener(setColorButtonListener);
        color2Button.setOnClickListener(setColorButtonListener);
        color3Button.setOnClickListener(setColorButtonListener);
        color4Button.setOnClickListener(setColorButtonListener);


        TextView color1 = (TextView) findViewById(R.id.color1);
        TextView color2 = (TextView) findViewById(R.id.color2);
        TextView color3 = (TextView) findViewById(R.id.color3);
        TextView color4 = (TextView) findViewById(R.id.color4);
        TextView color5 = (TextView) findViewById(R.id.color5);
        TextView color6 = (TextView) findViewById(R.id.color6);
        TextView color7 = (TextView) findViewById(R.id.color7);
        TextView color8 = (TextView) findViewById(R.id.color8);

        color1.setOnClickListener(setColorListButtonClick);
        color2.setOnClickListener(setColorListButtonClick);
        color3.setOnClickListener(setColorListButtonClick);
        color4.setOnClickListener(setColorListButtonClick);
        color5.setOnClickListener(setColorListButtonClick);
        color6.setOnClickListener(setColorListButtonClick);
        color7.setOnClickListener(setColorListButtonClick);
        color8.setOnClickListener(setColorListButtonClick);

    }

}
