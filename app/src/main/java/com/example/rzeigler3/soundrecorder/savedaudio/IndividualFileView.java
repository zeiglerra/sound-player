package com.example.rzeigler3.soundrecorder.savedaudio;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rzeigler3.soundrecorder.R;
import com.example.rzeigler3.soundrecorder.utils.AudioDuration;
import com.example.rzeigler3.soundrecorder.utils.MainMediaPlayer;

import java.io.File;

/**
 * Created by rzeigler3 on 3/18/2018.
 */

public class IndividualFileView {

    private MediaPlayer mMediaPlayer = MainMediaPlayer.getInstance();

    private SavedAudioFragment mSavedAudioFragment;
    private File mAudioFile;
    private LayoutInflater mInflater;
    private LinearLayout mLrlFileList;
    private View mVIndividualFileView;
    private TextView txvFileName;
    private TextView txvDuration;
    private TextView txvExtension;
    private Button btnDelete;

    public IndividualFileView(SavedAudioFragment savedAudioFragment, File audioFile) {
        mSavedAudioFragment = savedAudioFragment;
        mInflater = savedAudioFragment.getInflater();
        mLrlFileList = savedAudioFragment.getLrlFileList();
        mAudioFile = audioFile;

        mVIndividualFileView = mInflater.inflate(R.layout.individual_file,mLrlFileList,false);

        txvFileName = mVIndividualFileView.findViewById(R.id.txvFileName);
        txvDuration = mVIndividualFileView.findViewById(R.id.txvDuration);
        txvExtension = mVIndividualFileView.findViewById(R.id.txvExtension);
        btnDelete = mVIndividualFileView.findViewById(R.id.btnDelete);

        String strDuration = AudioDuration.getDurationString(mAudioFile);
        txvDuration.setText(strDuration);

        String fileName = audioFile.getName();
        String fileNameNoExt = fileName.substring(0,fileName.lastIndexOf("."));
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);

        txvFileName.setText(fileNameNoExt);
        txvExtension.setText(fileExt.toUpperCase());

        IndividualFileHandler individualFileHandler = new IndividualFileHandler(this);
        mVIndividualFileView.setOnClickListener(individualFileHandler);
        btnDelete.setOnClickListener(individualFileHandler);

        mLrlFileList.addView(mVIndividualFileView);
    }

    public SavedAudioFragment getSavedAudioFragment() {
        return mSavedAudioFragment;
    }

    public View getIndividualFileView() {
        return mVIndividualFileView;
    }

    public File getAudioFile() {
        return mAudioFile;
    }

    public TextView getTxvFileName() {
        return txvFileName;
    }

    public TextView getTxvDuration() {
        return txvDuration;
    }

    public TextView getTxvExtension() {
        return txvExtension;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }
}
