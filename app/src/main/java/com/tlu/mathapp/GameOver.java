package com.tlu.mathapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class GameOver extends AppCompatActivity {
    Button gameBtn, homeBtn;
    static String score;
    static AppDatabase db;
    //Creates the database
    Result testResult = new Result("test1", score);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        gameBtn = (Button) findViewById(R.id.play_button);
        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
        homeBtn = (Button) findViewById(R.id.to_home);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "results").build();
        this.score = getIntent().getStringExtra("SCORE");
        setScoreText(score);
    }
    public void startGame() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
    public void goHome() {
        Intent intent = new Intent(this, LandingPageActivity.class);
        startActivity(intent);
    }

    private void setScoreText(String s){
        final TextView score = (TextView)findViewById(R.id.game_over_score);
        score.setText("Score: " + s);
    }
}
