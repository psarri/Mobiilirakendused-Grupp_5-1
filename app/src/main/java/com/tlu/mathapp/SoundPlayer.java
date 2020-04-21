package com.tlu.mathapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {

    public static SoundPool soundPool;
    private static int correctSound;
    private static int overSound;
    private static int wrongSound;

    public SoundPlayer(Context context) {

        //SoundPool (int maxStreams, int streamType, int srcQuality)
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        correctSound = soundPool.load(context, R.raw.correct, 1);
        overSound = soundPool.load(context, R.raw.over, 1);
        wrongSound = soundPool.load(context, R.raw.wrong, 1);
    }

    public void playCorrectSound() {

        // play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(correctSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playOverSound() {

        // play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(overSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playWrongSound() {

        // play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(wrongSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
