package com.tlu.mathapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class GameOver extends AppCompatActivity {
    Button gameBtn, homeBtn, okBtn;
    EditText name;
    static String score;
    static AppDatabase db;
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

        okBtn = (Button) findViewById(R.id.inputButton);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveToDB();
            }
        });
        //Not recommended to access on main thread, because larger db-s will lock the UI
        //Since our application is small, it should not be a problem, so we will allow it via allowMainThreadQueries()
        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "results").allowMainThreadQueries().build();
        this.score = getIntent().getStringExtra("SCORE");
        setScoreText(score);
    }

    //We have to control user input on back button pressed
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, LandingPageActivity.class);
        startActivity(intent);
        //SaveToDB(); // Currently we allow user not to save score.
    }

    private void SaveToDB(){
        name = (EditText)findViewById(R.id.nameInput);
        submitResult(name.getText().toString(), score);
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

    private void submitResult(String name, String score){
        if(name.matches("")) name = "nimetu"; // if name is empty
        Result result = new Result(name, score);
        db.resultsDao().insertAll(result);
        goHome();
    }

}
