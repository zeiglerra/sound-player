package com.example.rzeigler3.soundrecorder.savedaudio;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.rzeigler3.soundrecorder.R;
import com.example.rzeigler3.soundrecorder.utils.MainMediaPlayer;
import com.example.rzeigler3.soundrecorder.utils.MyFilenameFilter;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rzeigler3 on 3/15/2018.
 */

public class SavedAudioFragment extends Fragment {

    private final static MainMediaPlayer mMainMediaPlayer = MainMediaPlayer.getInstance();

    private LinearLayout mLrlFileList;
    private LayoutInflater mInflater;
    private AudioDirectoryObserver mAudioDirectoryObserver;
    private File mSaveDir;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saved_audio,container,false);

        mLrlFileList = view.findViewById(R.id.linearList);
        mInflater = inflater;

        String saveDirPath = getString(R.string.saved_audio_directory);
        mSaveDir = new File(saveDirPath);
        if (! mSaveDir.exists()) {
            if (mSaveDir.mkdir()) {
                System.out.println(mSaveDir.getAbsolutePath() + " successfully created.");
            } else {
                System.out.println(mSaveDir.getAbsolutePath() + " could NOT be created.");
                Logger.getLogger(SavedAudioFragment.class.getName()).log(Level.SEVERE,null, new Exception());
            }
        } else {
            System.out.println(mSaveDir.getAbsolutePath() + " already exists.");
        }

        mAudioDirectoryObserver = new AudioDirectoryObserver(this,mSaveDir.getAbsolutePath());
        mAudioDirectoryObserver.startWatching();

        IndividualFileHandler.setViewNotSelectedColor(getResources().getColor(R.color.colorDefault));
        IndividualFileHandler.setViewSelectedColor(getResources().getColor(R.color.colorPrimary));

        IndividualFileHandler.setOnCompletion();

        addFilesToLinearLayout();

        return view;
    }

    public void refresh() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    private void addFilesToLinearLayout() {

        File[] mpeg4Files = mSaveDir.listFiles(new MyFilenameFilter(".mpeg4"));

        for (File f : mpeg4Files) {
            new IndividualFileView(this, f);
        }

    }

    public LinearLayout getLrlFileList() {
        return mLrlFileList;
    }

    public LayoutInflater getInflater() {
        return mInflater;
    }

}
