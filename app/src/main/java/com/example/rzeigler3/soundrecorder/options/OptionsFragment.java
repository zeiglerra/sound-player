package com.example.rzeigler3.soundrecorder.options;

import android.app.Fragment;
import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.rzeigler3.soundrecorder.R;

import java.util.logging.Logger;

/**
 * Created by rzeigler3 on 3/27/2018.
 */

public class OptionsFragment extends Fragment {

    private SelectedOptions mSelectedOptions;
    private RadioGroup mRgContainer;
    private RadioGroup mRgEncoding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.options,container,false);

        mRgContainer = view.findViewById(R.id.rgContainer);
        mRgEncoding = view.findViewById(R.id.rgEncoder);

        SelectedOptions.getInstance().constructSelectedOptions(view);

        return view;
    }

}

class SelectedOptions {

    private static final SelectedOptions ourInstance = new SelectedOptions();

    private RadioGroup mRgContainer;
    private RadioGroup mRgEncoder;

    public static SelectedOptions getInstance() {
        return ourInstance;
    }

    private SelectedOptions() {
    }

    public void constructSelectedOptions(View optionsView) {
        mRgContainer = optionsView.findViewById(R.id.rgContainer);
        mRgEncoder = optionsView.findViewById(R.id.rgEncoder);
    }

    public int getContainerFormat() {
        int outputFormat;

        int checkedButtonID = mRgContainer.getCheckedRadioButtonId();

        switch (checkedButtonID) {
            case R.id.rBtnAACADTS:
                outputFormat = MediaRecorder.OutputFormat.AAC_ADTS;
                break;
            case R.id.rBtnAMRNBF:
                outputFormat = MediaRecorder.OutputFormat.AMR_NB;
                break;
            case R.id.rBtnAMRWBF:
                outputFormat = MediaRecorder.OutputFormat.AMR_WB;
                break;
            case R.id.rBtnMPEG2TS:
                outputFormat = MediaRecorder.OutputFormat.MPEG_2_TS;
                break;
            case R.id.rBtnMpeg4:
                outputFormat = MediaRecorder.OutputFormat.MPEG_4;
                break;
            case R.id.rBtn3GPP:
                outputFormat = MediaRecorder.OutputFormat.THREE_GPP;
                break;
            case R.id.rBtnWebVorbis:
                outputFormat = MediaRecorder.OutputFormat.WEBM;
                break;
            default:
                System.out.println("Output format radio button not found.");
                System.exit(1);
                outputFormat = -1;
                break;
        }

        return outputFormat;
    }

    public int getEncoderFormat() {
        int audioEncoder = 0;

        int checkedButtonId = mRgEncoder.getCheckedRadioButtonId();

        switch (checkedButtonId) {
            case R.id.rBtnACC:
//                outputFormat =
                break;
        }
        return audioEncoder;
    }

    public void setRadioGroupContainer(RadioGroup radioGroup) {
        mRgContainer = radioGroup;
    }

    public void setRadioGroupEncoder(RadioGroup radioGroup) {
        mRgEncoder = radioGroup;
    }

}
