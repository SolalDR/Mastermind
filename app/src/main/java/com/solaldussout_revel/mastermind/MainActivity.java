package com.solaldussout_revel.mastermind;

import com.solaldussout_revel.mastermind.object.Game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Game game;
    TextView activeView;
    GridLayout gridColor;
    TableLayout tableScore;
    String[] colors;
    Button validScoreButton;

    TextView color1Button;
    TextView color2Button;
    TextView color3Button;
    TextView color4Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.game = NewGameActivity.getGame();
        colors = this.game.getColors();

        gridColor = (GridLayout) findViewById(R.id.gridColor);
        validScoreButton = (Button) findViewById(R.id.validScoreButton);

        tableScore = (TableLayout) findViewById(R.id.tableScore);



        tableScore.setOnClickListener(setTableClickListener);
        validScoreButton.setOnClickListener(validScoreButtonListener);

        manageButtonListener();

    }

    public void genRowTable(){
        TableRow tableRow = (TableRow)getLayoutInflater().inflate(R.layout.table_row_layout, null);
        TextView[] textViewsColors = new TextView[4];

        textViewsColors[0] = (TextView) tableRow.findViewById(R.id.square1);
        textViewsColors[1] = (TextView) tableRow.findViewById(R.id.square2);
        textViewsColors[2] = (TextView) tableRow.findViewById(R.id.square3);
        textViewsColors[3] = (TextView) tableRow.findViewById(R.id.square4);

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
        color1Button.setBackground(ColorDrawable.createFromPath("#FFCCCCCC"));
        color2Button.setBackground(ColorDrawable.createFromPath("#FFCCCCCC"));
        color3Button.setBackground(ColorDrawable.createFromPath("#FFCCCCCC"));
        color4Button.setBackground(ColorDrawable.createFromPath("#FFCCCCCC"));

    }

    View.OnClickListener validScoreButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ColorDrawable background1 = (ColorDrawable) color1Button.getBackground();
            ColorDrawable background2 = (ColorDrawable) color2Button.getBackground();
            ColorDrawable background3 = (ColorDrawable) color3Button.getBackground();
            ColorDrawable background4 = (ColorDrawable) color4Button.getBackground();

            colors = new String[]{
                getStrFromColorDraw((ColorDrawable) color1Button.getBackground()),
                getStrFromColorDraw((ColorDrawable) color1Button.getBackground()),
                getStrFromColorDraw((ColorDrawable) color1Button.getBackground()),
                getStrFromColorDraw((ColorDrawable) color1Button.getBackground())
            };

            Boolean test = true;
            for(int i=0; i<colors.length; i++){
                if(colors[i] == "#FFCCCCCC"){
                    test = false;
                }
            }

            if(test){
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
        genRowTable();

        /*clearColorButton();
        this.game.nextTour();*/
    }

    View.OnClickListener setColorButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.requestFocus();
        }
    };

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
