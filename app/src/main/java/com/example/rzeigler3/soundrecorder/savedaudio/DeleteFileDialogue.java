package com.example.rzeigler3.soundrecorder.savedaudio;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.io.File;

/**
 * Created by rzeigler3 on 3/20/2018.
 */

public class DeleteFileDialogue extends DialogFragment {

    private final static String FILE_NAME = "fileName";

    private File mAudioFile;

    public static DeleteFileDialogue newInstance(File audioFile) {
        DeleteFileDialogue deleteFileDialogue = new DeleteFileDialogue();
        deleteFileDialogue.setAudioFileToDelete(audioFile);
        return deleteFileDialogue;
    }

    public void setAudioFileToDelete(File file) {
        mAudioFile = file;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setTitle("Delete File \"" + mAudioFile.getName() + "\"?");
        alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String origFileName = mAudioFile.getName();
                if (mAudioFile.delete()) {
                    System.out.println(origFileName + " was deleted.");
                } else {
                    System.out.println(origFileName + " COULD NOT BE DELETED!");
                }
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("Canceled deletion of " + mAudioFile.getName());
                getDialog().dismiss();
            }
        });

        return alertDialogBuilder.create();
    }
}
