package com.tlu.mathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class LandingPageActivity extends AppCompatActivity{
    boolean mState = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Button gameBtn, resBtn;
        final ImageView musicBtn;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        gameBtn =  findViewById(R.id.play_button);
        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
                bounceAnimation(gameBtn);
            }
        });

        resBtn = findViewById(R.id.results_button);
        resBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                results();
                bounceAnimation(resBtn);
            }
        });

        musicBtn = findViewById(R.id.tohoh);
        musicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.mServ != null && mState) {
                    MainActivity.mServ.pauseMusic();
                    mState = false;
                    musicBtn.setImageResource(R.drawable.mute);
                }
                else if(MainActivity.mServ != null && !mState) {
                    MainActivity.mServ.resumeMusic();
                    mState = true;
                    musicBtn.setImageResource(R.drawable.unmute);
                }
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

    // Results page
    public void results() {
        Intent intent = new Intent(this, ResultsTable.class);
        startActivity(intent);
    }

    // Button animation
    public void bounceAnimation(Button btn) {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        BounceInterpolator interpolator = new BounceInterpolator(0.1, 20);
        myAnim.setInterpolator(interpolator);
        btn.startAnimation(myAnim);
    }
}
