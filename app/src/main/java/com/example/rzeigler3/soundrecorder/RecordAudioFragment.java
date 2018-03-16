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

    private ToggleButton mTbtRecordPause;
    private Button mBtnStop;
    private TextView mTxvRecordTime;

    private final int LAYOUT_RESOURCE = R.layout.record_audio;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT_RESOURCE,container,false);

        mTbtRecordPause = view.findViewById(R.id.tbtRecordPause);
        mBtnStop = view.findViewById(R.id.btnStop);
        mTxvRecordTime = view.findViewById(R.id.txvRecordTime);

        mTbtRecordPause.setOnCheckedChangeListener( new RecordPauseHandler(getActivity().getExternalCacheDir().getAbsolutePath()) );
        mBtnStop.setOnClickListener( new StopHandler(getActivity().getExternalCacheDir().getAbsolutePath()) );

        return view;
    }

}
