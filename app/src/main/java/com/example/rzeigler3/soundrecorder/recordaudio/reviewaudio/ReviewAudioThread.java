package com.example.rzeigler3.soundrecorder.recordaudio.reviewaudio;

import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.rzeigler3.soundrecorder.utils.MainMediaPlayer;
import com.example.rzeigler3.soundrecorder.utils.StringTimeCreate;
import com.example.rzeigler3.soundrecorder.utils.Time;

/**
 * Created by rzeigler3 on 3/18/2018.
 */

public class ReviewAudioThread implements Runnable {

    private final static MainMediaPlayer mMediaPlayer = MainMediaPlayer.getInstance();

    private TextView mTxvCurrentTime;
    private TextView mTxvEndTime;
    private SeekBar mSkbSeekBar;
    private Handler mHandler;

    public ReviewAudioThread(ReviewAudioDialogue reviewAudioDialogue) {
        mTxvCurrentTime = reviewAudioDialogue.getTxvCurrentTime();
        mTxvEndTime = reviewAudioDialogue.getTxvEndTime();
        mSkbSeekBar = reviewAudioDialogue.getSkbSeekBar();
        mHandler = new Handler();
    }

    @Override
    public void run() {
        if (! mMediaPlayer.getIsIdling()) {
            int currentTime = mMediaPlayer.getCurrentPosition() / Time.ONE_SECOND_IN_MILLISECONDS;
            mSkbSeekBar.setProgress(currentTime);
            String strCurrentTime = StringTimeCreate.getTimeUsingSeconds(currentTime);
            mTxvCurrentTime.setText(strCurrentTime);
            mHandler.postDelayed(this, Time.ONE_SECOND_IN_MILLISECONDS);
        }
    }

}
