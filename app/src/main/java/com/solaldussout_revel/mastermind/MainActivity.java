package com.solaldussout_revel.mastermind;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import com.solaldussout_revel.mastermind.object.Game;



public class MainActivity extends AppCompatActivity {

    private static Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);


        Button btnNewGame = (Button) findViewById(R.id.newGameButton);
        btnNewGame.setOnClickListener(setNewGameListener);

        Button btnRules = (Button) findViewById(R.id.RulesButton);
        btnRules.setOnClickListener(setRulesListener);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView highscore = (TextView) findViewById(R.id.highscoretext);
        Integer highScoreVal = preferences.getInt("highscore", 999999);

        if (highScoreVal == 999999) {
            highscore.setText(R.string.noBestScoreLabel);
        }
        else {
            Integer highScoreValDiv = highScoreVal/1000;
            highscore.setText("Meilleur Score : " + highScoreValDiv.toString() + " s");
        }

    }

    View.OnClickListener setNewGameListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setGame(new Game());
            Intent intent = new Intent(getBaseContext(), GameActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener setRulesListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getBaseContext(), RulesActivity.class);
            startActivity(intent);
        }
    };


    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        MainActivity.game = game;
    }

}
