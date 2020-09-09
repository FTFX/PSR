package com.psr.accountbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import java.util.Locale;

public class ModifyDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View DialogView = inflater.inflate(R.layout.dialog_modify, null);
        final MainActivity activity = (MainActivity)getActivity();
        final EditText et_mod_name = DialogView.findViewById(R.id.et_mod_name);
        final EditText et_mod_expense = DialogView.findViewById(R.id.et_mod_expense);
        final EditText et_mod_quota = DialogView.findViewById(R.id.et_mod_quota);

        Item currentItem = activity.searchResult.get(activity.ItemPosition);
        et_mod_name.setText(currentItem.getName());
        et_mod_expense.setText(String.format(Locale.US, "%.2f", currentItem.getExpense()), TextView.BufferType.EDITABLE);
        et_mod_quota.setText(String.format(Locale.US, "%.2f", currentItem.getQuota()), TextView.BufferType.EDITABLE);
        
        builder.setView(DialogView)
                .setPositiveButton(R.string.modify, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
