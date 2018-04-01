package com.example.rzeigler3.soundrecorder.recordaudio;

import android.media.MediaRecorder;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.rzeigler3.soundrecorder.R;
import com.example.rzeigler3.soundrecorder.recordaudio.reviewaudio.ReviewAudioDialogue;
import com.example.rzeigler3.soundrecorder.utils.StringTimeCreate;
import com.example.rzeigler3.soundrecorder.utils.Time;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rzeigler3 on 3/15/2018.
 */

public class RecordAudioHandler implements CompoundButton.OnCheckedChangeListener {

    private RecordAudioFragment mRecordAudioFragment;
    private MediaRecorder mMediaRecorder;
    private ToggleButton mTBtnRecordPause;
    private TextView mTxvRecordTime;
    private boolean mIsRecording;
    private int mCurrentTime;
    private Handler mHandler = new Handler();
    private RotateAnimation mRotateAnimation;

    public RecordAudioHandler(RecordAudioFragment recordAudioFragment) {
        mRecordAudioFragment = recordAudioFragment;
        mTBtnRecordPause = recordAudioFragment.getTbtRecordPause();
        mTxvRecordTime = recordAudioFragment.getTxvRecordTime();

        mRotateAnimation = new RotateAnimation(0F,360F,
                Animation.RELATIVE_TO_SELF, .5F, Animation.RELATIVE_TO_SELF,.5F);
        mRotateAnimation.setDuration(1000);
        mRotateAnimation.setRepeatCount(Animation.INFINITE);
        mRotateAnimation.setFillAfter(true);

        mRecordAudioFragment.getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {

                if (mIsRecording) {
                    mCurrentTime += 1;
                } else {
                    mCurrentTime = 0;
                }
                String strCurrentTime = StringTimeCreate.getTimeUsingSeconds(mCurrentTime);
                mTxvRecordTime.setText(strCurrentTime);

                mHandler.postDelayed(this, Time.ONE_SECOND_IN_MILLISECONDS);
            }

        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if (b == true) {

            mIsRecording = true;
            mTBtnRecordPause.startAnimation(mRotateAnimation);

            if (mMediaRecorder == null) {

                mMediaRecorder = new MediaRecorder();
                mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                String mFilePath = mRecordAudioFragment.getActivity().getExternalCacheDir() + "/" + R.string.cache_file;
                mMediaRecorder.setOutputFile(mFilePath);
                mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                try {
                    mMediaRecorder.prepare();
                } catch (IOException e) {
                    Logger.getLogger(RecordAudioHandler.class.getName()).log(Level.SEVERE, null, e);
                }

            }

            mMediaRecorder.start();

        } else {
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;
            mIsRecording = false;
            mTBtnRecordPause.clearAnimation();
            String cachedAudioPath = mRecordAudioFragment.getActivity().getExternalCacheDir() + "/" + R.string.cache_file;
            new ReviewAudioDialogue(cachedAudioPath).show(mRecordAudioFragment.getFragmentManager(), "Review Audio Dialogue");
        }

    }

}
