package com.example.rzeigler3.soundrecorder.junk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

/**
 * Created by rzeigler3 on 3/16/2018.
 */

public class MyDialogueFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle("My Alert Dialogue");
        alertDialogBuilder.setMessage("This is a message.");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Positive", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "You clicked positive!", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setNegativeButton("Negative", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "You clicked negative!", Toast.LENGTH_SHORT).show();
            }
        });

        return  alertDialogBuilder.create();
    }

}
