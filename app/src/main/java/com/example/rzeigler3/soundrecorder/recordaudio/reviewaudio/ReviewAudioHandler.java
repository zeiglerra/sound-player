package com.example.rzeigler3.soundrecorder.recordaudio.reviewaudio;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.example.rzeigler3.soundrecorder.R;
import com.example.rzeigler3.soundrecorder.utils.Copy;
import com.example.rzeigler3.soundrecorder.utils.MainMediaPlayer;
import com.example.rzeigler3.soundrecorder.utils.Time;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rzeigler3 on 3/18/2018.
 */

public class ReviewAudioHandler implements CompoundButton.OnCheckedChangeListener,
                                           View.OnClickListener,
                                           SeekBar.OnSeekBarChangeListener {

    private final static MainMediaPlayer mMediaPlayer = MainMediaPlayer.getInstance();
    private final String EXTENSION = ".mpeg4";

    private ReviewAudioDialogue mReviewAudioDialogue;
    private ReviewAudioThread mReviewAudioThread;
    private String mCachedAudioPath;
    private ToggleButton mTbtnPlayPause;
    private EditText mETxtFileName;
    private Button mBtnDiscard;
    private Button mBtnSave;

    public ReviewAudioHandler(ReviewAudioDialogue reviewAudioDialogue) {
        mReviewAudioDialogue = reviewAudioDialogue;
        mReviewAudioThread = reviewAudioDialogue.getReviewAudioThread();
        mCachedAudioPath = reviewAudioDialogue.getCachedAudioPath();
        mTbtnPlayPause = reviewAudioDialogue.getTbtnPlayPause();
        mETxtFileName = reviewAudioDialogue.getEtxtFileName();
        mBtnDiscard = reviewAudioDialogue.getBtnDiscard();
        mBtnSave = reviewAudioDialogue.getBtnSave();

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mTbtnPlayPause.setChecked(false);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if (compoundButton == mTbtnPlayPause) {
            if (b == true) {
                mMediaPlayer.start();
            } else {
                mMediaPlayer.pause();
            }
        }

    }

    @Override
    public void onClick(View view) {

        if (view == mBtnDiscard) {
            mMediaPlayer.setIsIdling(true);
            mMediaPlayer.reset();
            mReviewAudioDialogue.getDialog().dismiss();
        }

        if (view == mBtnSave) {
            mMediaPlayer.setIsIdling(true);
            mMediaPlayer.reset();
            File cachedFile = new File(mCachedAudioPath);
            String destFileName = mETxtFileName.getText().toString() + EXTENSION;
            String saveDirPath = mReviewAudioDialogue.getActivity().getString(R.string.saved_audio_directory);
            File saveFile = new File(saveDirPath,destFileName);

            System.out.println("Source (cache) path: " + cachedFile.getAbsolutePath());
            System.out.println("Destination path: " + saveFile.getAbsolutePath());

            try {
                Copy.copy(cachedFile,saveFile);
            } catch (IOException e) {
                System.out.println("Copy from " + cachedFile.getAbsolutePath()
                        + " to " + saveFile.getAbsolutePath() + " failed.");
                Logger.getLogger(ReviewAudioHandler.class.getName()).log(Level.SEVERE,null,e);
            }

            if (saveFile.exists()) {
                System.out.println("File was copied successfully.");
            } else {
                System.out.println("FAILURE TO COPY");
            }

            mReviewAudioDialogue.getDialog().dismiss();
        }

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mMediaPlayer.seekTo(seekBar.getProgress() * Time.ONE_SECOND_IN_MILLISECONDS);
    }
}
