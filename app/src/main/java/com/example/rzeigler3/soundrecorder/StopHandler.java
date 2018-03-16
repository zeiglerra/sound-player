package com.example.rzeigler3.soundrecorder;

import android.media.MediaPlayer;
import android.view.View;

import java.io.IOException;

/**
 * Created by Rick on 3/15/2018.
 */

public class StopHandler implements View.OnClickListener {

    private String mExternalCacheDir;
    private String mFileName = "myvoice.mpeg4";
    private String mFilePath;

    public StopHandler(String externalCacheDir) {
        mExternalCacheDir = externalCacheDir;
    }

    @Override
    public void onClick(View v) {
        System.out.println("Stop!");
        MediaPlayer mPlayer = new MediaPlayer();
        mFileName = mExternalCacheDir + "/" + mFileName;
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
