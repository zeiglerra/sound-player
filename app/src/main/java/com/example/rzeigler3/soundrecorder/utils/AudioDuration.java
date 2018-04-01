package com.example.rzeigler3.soundrecorder.utils;

import android.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

/**
 * Created by rzeigler3 on 3/22/2018.
 */

public class AudioDuration {
    private static final AudioDuration ourInstance = new AudioDuration();

    public static AudioDuration getInstance() {
        return ourInstance;
    }

    private AudioDuration() {
    }
    
    public static String getDurationString(File audioFile) {
        String strDuration = "00:00";

        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(audioFile.getAbsolutePath());
            mediaPlayer.prepare();
            int durationSeconds = mediaPlayer.getDuration() / Time.ONE_SECOND_IN_MILLISECONDS;
            strDuration = StringTimeCreate.getTimeUsingSeconds(durationSeconds);
            mediaPlayer.release();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strDuration;
    }
}
