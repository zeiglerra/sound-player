package com.example.rzeigler3.soundrecorder.recordaudio;

import android.media.MediaRecorder;
import android.widget.TextView;

/**
 * Created by rzeigler3 on 3/24/2018.
 */

public class RecordAudioThread implements Runnable {

    private RecordAudioFragment mRecordAudioFragment;
    private MediaRecorder mMediaRecorder;
    private TextView mTxvRecordTime;

    public RecordAudioThread(RecordAudioFragment recordAudioFragment) {
        mTxvRecordTime = recordAudioFragment.getTxvRecordTime();
    }

    @Override
    public void run() {

    }

}
