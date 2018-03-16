package com.example.rzeigler3.soundrecorder;

import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v4.app.ActivityCompat;
import android.widget.CompoundButton;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by rzeigler3 on 3/15/2018.
 */

public class RecordPauseHandler implements CompoundButton.OnCheckedChangeListener {

    private final String mExternalCacheDir;
    private MediaRecorder mMediaRecorder;
    private String mFileName = "myvoice.mpeg4";
    private String mFilePath;

    public RecordPauseHandler(String externalCacheDir) {
        mExternalCacheDir = externalCacheDir;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if (b == true) {

            System.out.println("We should be recording.");

            if (mMediaRecorder == null) {


                mMediaRecorder = new MediaRecorder();
                mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                mFilePath = mExternalCacheDir + "/" + mFileName;
                mMediaRecorder.setOutputFile(mFilePath);
                mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                try {
                    mMediaRecorder.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            mMediaRecorder.start();

        } else {
            System.out.println("Stop recording.");
            mMediaRecorder.stop();
            mMediaRecorder = null;

        }

    }

}
