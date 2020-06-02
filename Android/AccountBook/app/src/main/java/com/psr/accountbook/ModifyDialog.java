package com.psr.accountbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class ModifyDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View DialogView = inflater.inflate(R.layout.dialog_modify, null);
        builder.setView(DialogView)
                .setPositiveButton(R.string.modify, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et_mod_name = DialogView.findViewById(R.id.et_mod_name);
                        EditText et_mod_expense = DialogView.findViewById(R.id.et_mod_expense);
                        EditText et_mod_quota = DialogView.findViewById(R.id.et_mod_quota);
                        String name = getString(R.string.untitled);
                        if (!et_mod_name.getText().toString().equals("")) {
                            name = et_mod_name.getText().toString();
                        }
                        double expense = 0.0;
                        double quota = 0.0;
                        if (!et_mod_expense.getText().toString().equals("")) {
                            expense = Double.parseDouble(et_mod_expense.getText().toString());
                        }
                        if (!et_mod_quota.getText().toString().equals("")) {
                            quota = Double.parseDouble(et_mod_quota.getText().toString());
                        }
                        MainActivity activity = (MainActivity)getActivity();
                        activity.item.modItem(activity.ItemId, name, expense, quota);

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ModifyDialog.this.getDialog().cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
