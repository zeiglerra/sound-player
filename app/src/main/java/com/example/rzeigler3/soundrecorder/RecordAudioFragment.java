package com.example.rzeigler3.soundrecorder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by rzeigler3 on 3/15/2018.
 */

public class RecordAudioFragment extends Fragment {

    private MainActivity mMainActivity;
    private ToggleButton tbtRecordPause;
    private Button btnStop;
    private TextView txvRecordTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.record_audio,container,false);

        tbtRecordPause = (ToggleButton) view.findViewById(R.id.tbtRecordPause);

        tbtRecordPause.setOnCheckedChangeListener( new RecordPauseHandler() );

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMainActivity = (MainActivity) context;
    }
}
