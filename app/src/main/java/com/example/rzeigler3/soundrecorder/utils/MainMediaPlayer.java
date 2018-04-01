package com.example.rzeigler3.soundrecorder.utils;

import android.media.MediaPlayer;

/**
 * Created by rzeigler3 on 3/19/2018.
 */

public class MainMediaPlayer extends MediaPlayer {

    private static final MainMediaPlayer ourInstance = new MainMediaPlayer();
    private boolean isIdling = true;

    public static MainMediaPlayer getInstance() {
        return ourInstance;
    }

    public void setIsIdling(boolean value) {
        isIdling = value;
    }

    public boolean getIsIdling() {
        return isIdling;
    }

    private MainMediaPlayer() {
    }

}
