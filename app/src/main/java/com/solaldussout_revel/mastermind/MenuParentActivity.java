package com.solaldussout_revel.mastermind;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.solaldussout_revel.mastermind.object.Game;

public class MenuParentActivity extends AppCompatActivity {

    Toolbar toolbar;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem stopGame = menu.findItem(R.id.stopGameButton);
        MenuItem rules = menu.findItem(R.id.rulesButton);

        stopGame.setOnMenuItemClickListener(setStopGameListener);
        rules.setOnMenuItemClickListener(setRulesListener);
        return true;
    }


    MenuItem.OnMenuItemClickListener setStopGameListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Intent secondeActivite = new Intent(getBaseContext(), NewGameActivity.class);
            startActivity(secondeActivite);
            return false;
        }
    };

    MenuItem.OnMenuItemClickListener setRulesListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Intent secondeActivite = new Intent(getBaseContext(), RulesActivity.class);
            startActivity(secondeActivite);
            return false;
        }
    };

    protected void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        System.out.println(toolbar);
        setSupportActionBar(toolbar);
    }
}
