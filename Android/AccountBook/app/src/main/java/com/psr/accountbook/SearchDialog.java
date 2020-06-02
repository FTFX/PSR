package com.psr.accountbook;

import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class SearchDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View DialogView = inflater.inflate(R.layout.dialog_search, null);
        builder.setView(DialogView)
                .setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et_src_name = DialogView.findViewById(R.id.et_src_name);
                        final MainActivity activity = (MainActivity)getActivity();
                        List<Item> allItems = activity.allItemsInList;
                        TableLayout table = activity.table;
                        int childCount = table.getChildCount();
                        if (childCount > 1) {
                            table.removeViews(1, childCount - 1);
                        }
                        for (Item i : allItems) {
                            if (i.getName().equals(et_src_name.getText().toString())) {
                                TextView tv_name = new TextView(activity);
                                tv_name.setText(i.getName());
                                TextView tv_expense = new TextView(activity);
                                tv_expense.setText(String.format(Locale.US, "%.2f", i.getExpense()));
                                TextView tv_quota = new TextView(activity);
                                tv_quota.setText(String.format(Locale.US, "%.2f", i.getQuota()));
                                TableRow row = new TableRow(activity);
                                row.addView(tv_name);
                                row.addView(tv_expense);
                                row.addView(tv_quota);
                                row.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        DialogFragment menuDialog = new MenuDialog();
                                        menuDialog.show(activity.getSupportFragmentManager(), "Search");
                                    }
                                });
                                table.addView(row);
                            }
                        }

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SearchDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
