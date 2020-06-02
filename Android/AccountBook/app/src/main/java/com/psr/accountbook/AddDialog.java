package com.psr.accountbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View DialogView = inflater.inflate(R.layout.dialog_add, null);
        builder.setView(DialogView)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et_add_name = DialogView.findViewById(R.id.et_add_name);
                        EditText et_add_expense = DialogView.findViewById(R.id.et_add_expense);
                        EditText et_add_quota = DialogView.findViewById(R.id.et_add_quota);
                        final MainActivity activity = (MainActivity)getActivity();
                        activity.item.addItem(et_add_name.getText().toString(),
                                Double.parseDouble(et_add_expense.getText().toString()),
                                Double.parseDouble(et_add_quota.getText().toString()));
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
