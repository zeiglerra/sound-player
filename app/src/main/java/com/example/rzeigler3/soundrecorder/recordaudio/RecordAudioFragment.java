package com.example.rzeigler3.soundrecorder.recordaudio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.rzeigler3.soundrecorder.R;

/**
 * Created by rzeigler3 on 3/15/2018.
 */

public class RecordAudioFragment extends Fragment {

    private final int LAYOUT_RESOURCE = R.layout.record_audio;

    private ToggleButton mTbtRecordPause;
    private TextView mTxvRecordTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT_RESOURCE,container,false);

        mTbtRecordPause = view.findViewById(R.id.tbtRecordPause);
        mTxvRecordTime = view.findViewById(R.id.txvRecordTime);

        RecordAudioHandler recordAudioHandler = new RecordAudioHandler(this);
        mTbtRecordPause.setOnCheckedChangeListener(recordAudioHandler);

        return view;
    }

    public ToggleButton getTbtRecordPause() {
        return mTbtRecordPause;
    }

    public TextView getTxvRecordTime() {
        return mTxvRecordTime;
    }

}
