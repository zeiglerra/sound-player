package com.example.rzeigler3.soundrecorder;

import android.widget.CompoundButton;

/**
 * Created by rzeigler3 on 3/15/2018.
 */

public class RecordPauseHandler implements CompoundButton.OnCheckedChangeListener {

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        System.out.println("Button ID is: " + compoundButton.getId());

        if (b == true) {
            System.out.println("We should be recording.");
        } else {
            System.out.println("Stop recording.");
        }

    }

}
