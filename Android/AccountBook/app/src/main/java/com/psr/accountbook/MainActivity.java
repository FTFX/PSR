package com.psr.accountbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int ItemId;
    int ItemPosition;
    ItemViewModel item;
    List<Item> allItemsInList;
    List<Item> searchResult;
    RecyclerView rv_items;
    ItemViewAdapter itemViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv_costs = findViewById(R.id.tv_costs);
        rv_items = findViewById(R.id.rv_items);
        itemViewAdapter = new ItemViewAdapter(this);
        rv_items.setLayoutManager(new LinearLayoutManager(this));
        rv_items.setAdapter(itemViewAdapter);

        item = ViewModelProviders.of(this).get(ItemViewModel.class);
        item.getAllItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(final List<Item> items) {
                allItemsInList = items;
                searchResult = items;
                itemViewAdapter.setAllItems(items);
                itemViewAdapter.notifyDataSetChanged();
                tv_costs.setText(String.valueOf(calculateExpenses(items)));
            }
        });

        Button btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment addDialog = new AddDialog();
                addDialog.show(getSupportFragmentManager(), "Add");
            }
        });

        Button btn_src = findViewById(R.id.btn_search);
        btn_src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment searchDialog = new SearchDialog();
                searchDialog.show(getSupportFragmentManager(), "Search");
            }
        });

        Button btn_ref = findViewById(R.id.btn_refresh);
        btn_ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchResult = allItemsInList;
                itemViewAdapter.setAllItems(allItemsInList);
                itemViewAdapter.notifyDataSetChanged();
                tv_costs.setText(String.valueOf(calculateExpenses(allItemsInList)));
            }
        });
    }

    public double calculateExpenses(List<Item> allItems) {
        double sum = 0.0;
        for (Item i : allItems) {
            sum += i.getExpense();
        }
        return sum;
    }
}