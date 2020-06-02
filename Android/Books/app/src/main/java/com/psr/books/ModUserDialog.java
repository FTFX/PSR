package com.psr.books;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;
import androidx.room.Room;

import java.util.Objects;

public class ModUserDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View DialogView = inflater.inflate(R.layout.dialog_mod, null);
        builder.setView(DialogView)
                .setPositiveButton(R.string.modify, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et_mod_id = DialogView.findViewById(R.id.et_mod_id);
                        EditText et_mod_username = DialogView.findViewById(R.id.et_mod_username);
                        EditText et_mod_password = DialogView.findViewById(R.id.et_mod_password);
                        MainActivity activity = (MainActivity)getActivity();
                        activity.modUser(Integer.parseInt(et_mod_id.getText().toString()), et_mod_username.getText().toString(), et_mod_password.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ModUserDialog.this.getDialog().cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
