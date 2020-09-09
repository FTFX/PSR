package com.psr.accountbook;

import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
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
                        List<Item> searchResult = new ArrayList<>();
                        for (Item i : allItems) {
                            if (i.getName().equals(et_src_name.getText().toString())) {
                                searchResult.add(i);
                            }
                        }
                        activity.searchResult = searchResult;
                        activity.itemViewAdapter.setAllItems(searchResult);
                        activity.itemViewAdapter.notifyDataSetChanged();
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
