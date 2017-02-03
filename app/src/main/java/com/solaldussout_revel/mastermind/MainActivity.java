package com.solaldussout_revel.mastermind;

import com.solaldussout_revel.mastermind.object.Game;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Game game;
    TextView activeView;
    GridLayout gridColor;
    TableLayout tableScore;
    String[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.game = NewGameActivity.getGame();
        colors = this.game.getColors();

        gridColor = (GridLayout) findViewById(R.id.gridColor);


        tableScore = (TableLayout) findViewById(R.id.tableScore);

        tableScore.setOnClickListener(setTableClickListener);


        manageButtonListener();

    }


    View.OnClickListener setColorListButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (activeView != null) {
                activeView.setBackground(v.getBackground());
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
        TextView color1Button = (TextView) findViewById(R.id.color1Button);
        TextView color2Button = (TextView) findViewById(R.id.color2Button);
        TextView color3Button = (TextView) findViewById(R.id.color3Button);
        TextView color4Button = (TextView) findViewById(R.id.color4Button);


        color1Button.setOnFocusChangeListener(setColorButtonFocus);
        color2Button.setOnFocusChangeListener(setColorButtonFocus);
        color3Button.setOnFocusChangeListener(setColorButtonFocus);
        color4Button.setOnFocusChangeListener(setColorButtonFocus);


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
