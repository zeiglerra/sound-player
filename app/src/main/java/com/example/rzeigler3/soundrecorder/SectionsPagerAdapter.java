package com.example.rzeigler3.soundrecorder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.example.rzeigler3.soundrecorder.options.OptionsFragment;
import com.example.rzeigler3.soundrecorder.savedaudio.SavedAudioFragment;
import com.example.rzeigler3.soundrecorder.recordaudio.RecordAudioFragment;

/**
 * Created by Rick on 3/15/2018.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new OptionsFragment();
                break;
            case 1:
                fragment = new RecordAudioFragment();
                break;
            case 2:
                fragment = new SavedAudioFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
