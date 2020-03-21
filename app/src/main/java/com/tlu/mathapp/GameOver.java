package com.tlu.mathapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {
    Button gameBtn;
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
    }
    public void startGame() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}
