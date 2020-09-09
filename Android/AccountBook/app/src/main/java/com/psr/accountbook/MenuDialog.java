package com.psr.accountbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

public class MenuDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.operation)
                .setItems(R.array.operations, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity activity = (MainActivity)getActivity();
                        if (which == 0) {
                            new ModifyDialog().show(activity.getSupportFragmentManager(), "Edit");
                        } else {
                            activity.item.delItem(activity.ItemId);
                        }
                    }
                });
        return builder.create();
    }
}
