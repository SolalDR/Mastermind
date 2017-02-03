package com.solaldussout_revel.mastermind;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import com.solaldussout_revel.mastermind.object.Game;


public class NewGameActivity extends AppCompatActivity {

    private static Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);


        Button btnNewGame = (Button) findViewById(R.id.newGameButton);
        btnNewGame.setOnClickListener(setNewGameListener);
    }

    View.OnClickListener setNewGameListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setGame(new Game());
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
    };

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        NewGameActivity.game = game;
    }
}
