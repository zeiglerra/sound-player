package com.example.rzeigler3.soundrecorder.savedaudio;

import android.app.FragmentManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;

import com.example.rzeigler3.soundrecorder.utils.MainMediaPlayer;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rzeigler3 on 3/19/2018.
 */

public class IndividualFileHandler implements View.OnClickListener {

    private static View currentlySelectedView;
    private static int notSelectedColor = Color.BLUE;
    private static int selectedColor = Color.GREEN;

    private SavedAudioFragment mSavedAudioFragment;
    private IndividualFileView mIndividualFileView;
    private FragmentManager mFragmentManager;
    private View mVIndividualFileView;
    private Button mBtnDelete;
    private File mAudioFile;

    private static final MediaPlayer mMediaPlayer = MainMediaPlayer.getInstance();

    public IndividualFileHandler(IndividualFileView individualFileView) {
        mSavedAudioFragment = individualFileView.getSavedAudioFragment();
        mVIndividualFileView = individualFileView.getIndividualFileView();
        mFragmentManager = individualFileView.getSavedAudioFragment().getFragmentManager();
        mBtnDelete = individualFileView.getBtnDelete();
        mAudioFile = individualFileView.getAudioFile();
    }

    public static void setViewSelectedColor(int color) {
        selectedColor = color;
    }

    public static void setViewNotSelectedColor(int color) {
        notSelectedColor = color;
    }

    private static void colorSelectedView(View newSelectedView) {

        if (currentlySelectedView != null) {
            currentlySelectedView.setBackgroundColor(notSelectedColor);
        }
        newSelectedView.setBackgroundColor(selectedColor);
        currentlySelectedView = newSelectedView;

    }

    @Override
    public void onClick(View v) {

        if (v == mVIndividualFileView) {
            mVIndividualFileView.setBackgroundColor(Color.BLUE);
            playFile();
        }

        if (v == mBtnDelete) {
            System.out.println("Delete clicked!");
            DeleteFileDialogue deleteFileDialogue = DeleteFileDialogue.newInstance(mAudioFile);
            deleteFileDialogue.show(mFragmentManager,"Delete");
        }

    }

    private void playFile() {

        mMediaPlayer.stop();
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(mAudioFile.getAbsolutePath());
            mMediaPlayer.prepare();
            colorSelectedView(mVIndividualFileView);
        } catch (IOException e) {
            Logger.getLogger(IndividualFileHandler.class.getName()).log(Level.SEVERE, null, e);
        }
        mMediaPlayer.start();
        System.out.println(mAudioFile.getName() + " is playing.");

    }

    public static void setOnCompletion() {
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                System.out.println("The onCompletion was fired.");
                if (currentlySelectedView != null) {
                    currentlySelectedView.setBackgroundColor(notSelectedColor);
                }
            }
        });
    }


}
