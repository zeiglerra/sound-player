package com.example.rzeigler3.soundrecorder.savedaudio;

import android.os.FileObserver;
import android.support.annotation.Nullable;

import com.example.rzeigler3.soundrecorder.savedaudio.SavedAudioFragment;

/**
 * Created by rzeigler3 on 3/18/2018.
 */

public class AudioDirectoryObserver extends FileObserver {

    private SavedAudioFragment mSavedAudioFragment;

    static final int mMask = (FileObserver.CREATE |
            FileObserver.DELETE |
            FileObserver.MODIFY |
            FileObserver.MOVED_FROM |
            FileObserver.MOVED_TO);

    public AudioDirectoryObserver(SavedAudioFragment savedAudioFragment, String path) {
        super(path,mMask);
        mSavedAudioFragment = savedAudioFragment;
    }

    @Override
    public void onEvent(int i, @Nullable String s) {
        System.out.println("Directory change!");
        mSavedAudioFragment.refresh();
    }
}
