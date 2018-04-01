package com.example.rzeigler3.soundrecorder.recordaudio.reviewaudio;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.rzeigler3.soundrecorder.R;
import com.example.rzeigler3.soundrecorder.utils.MainMediaPlayer;
import com.example.rzeigler3.soundrecorder.utils.StringTimeCreate;
import com.example.rzeigler3.soundrecorder.utils.Time;

import java.io.IOException;

/**
 * Created by rzeigler3 on 3/16/2018.
 */

@SuppressLint("ValidFragment")
public class ReviewAudioDialogue extends DialogFragment {

    private final static MainMediaPlayer mMediaPlayer = MainMediaPlayer.getInstance();

    private String mCachedAudioPath;
    private ReviewAudioThread mReviewAudioThread;
    private TextView txvCurrentTime;
    private TextView txvEndTime;
    private SeekBar skbSeekBar;
    private ToggleButton tbtnPlayPause;
    private EditText etxtFileName;
    private Button btnDiscard;
    private Button btnSave;

    @SuppressLint("ValidFragment")
    public ReviewAudioDialogue(String cachedAudioPath) {
        mCachedAudioPath = cachedAudioPath;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_audio,container,false);

        txvCurrentTime = view.findViewById(R.id.txvCurrentTime);
        txvEndTime = view.findViewById(R.id.txvEndTime);
        skbSeekBar = view.findViewById(R.id.skbSeekBar);
        tbtnPlayPause = view.findViewById(R.id.tbtnPlayPause);
        etxtFileName = view.findViewById(R.id.etxtFileName);
        btnDiscard = view.findViewById(R.id.btnDiscard);
        btnSave = view.findViewById(R.id.btnSave);

        mMediaPlayer.stop();
        mMediaPlayer.reset();

        try {
            mMediaPlayer.setDataSource(mCachedAudioPath);
            mMediaPlayer.prepare();
            mMediaPlayer.setIsIdling(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mReviewAudioThread = new ReviewAudioThread(this);
        ReviewAudioHandler reviewAudioHandler = new ReviewAudioHandler(this);
        skbSeekBar.setOnSeekBarChangeListener(reviewAudioHandler);
        tbtnPlayPause.setOnCheckedChangeListener(reviewAudioHandler);
        btnDiscard.setOnClickListener(reviewAudioHandler);
        btnSave.setOnClickListener(reviewAudioHandler);

        final int endTime = mMediaPlayer.getDuration() / Time.ONE_SECOND_IN_MILLISECONDS;
        String strDurationTime = StringTimeCreate.getTimeUsingSeconds(endTime);
        txvEndTime.setText(strDurationTime);
        skbSeekBar.setMax(endTime);
//        skbSeekBar.setMin(0);

        getActivity().runOnUiThread(mReviewAudioThread);

        getDialog().setTitle("My Dialogue Title");
        setCancelable(false);
        return view;
    }

    public String getCachedAudioPath() {
        return mCachedAudioPath;
    }

    public ReviewAudioThread getReviewAudioThread() {
        return mReviewAudioThread;
    }

    public TextView getTxvCurrentTime() {
        return txvCurrentTime;
    }

    public TextView getTxvEndTime() {
        return txvEndTime;
    }

    public SeekBar getSkbSeekBar() {
        return skbSeekBar;
    }

    public ToggleButton getTbtnPlayPause() {
        return tbtnPlayPause;
    }

    public EditText getEtxtFileName() {
        return etxtFileName;
    }

    public Button getBtnDiscard() {
        return btnDiscard;
    }

    public Button getBtnSave() {
        return btnSave;
    }

}
