package com.tlu.mathapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Instructions extends AppCompatActivity {
    Button homeBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        final TextView instructionText = findViewById(R.id.instructionText);
        instructionText.setText(R.string.inst_text);


        homeBtn = findViewById(R.id.back_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
                bounceAnimation(homeBtn);
            }
        });
    }

    public void goHome() {
        Intent intent = new Intent(this, LandingPageActivity.class);
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
