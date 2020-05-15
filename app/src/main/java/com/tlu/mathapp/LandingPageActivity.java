package com.tlu.mathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingPageActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button gameBtn, resBtn;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        gameBtn = (Button) findViewById(R.id.play_button);
        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        gameBtn = (Button) findViewById(R.id.results_button);
        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                results();
            }
        });
    }

    //Dont let uer go back to game with back button
    @Override
    public void onBackPressed() {
        if (shouldAllowBack()) {
            super.onBackPressed();
        }
    }

    //Back button logic
    private boolean shouldAllowBack() {
        //Don't allow to go back
        return false;
    }

    // Start game
    public void startGame() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    // Start game
    public void results() {
        Intent intent = new Intent(this, ResultsTable.class);
        startActivity(intent);
    }
}
