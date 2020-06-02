package com.psr.accountbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

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
    ItemViewModel item;
    TableLayout table;
    int ItemId;
    List<Item> allItemsInList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv_costs = findViewById(R.id.tv_costs);

        item = ViewModelProviders.of(this).get(ItemViewModel.class);
        item.getAllItemsLiveData().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(final List<Item> items) {
                allItemsInList = items;
                table = findViewById(R.id.tb_items);
                int childCount = table.getChildCount();
                if (childCount > 1) {
                    table.removeViews(1, childCount - 1);
                }

                for (final Item i : items) {
                    TextView tv_name = new TextView(MainActivity.this);
                    tv_name.setText(i.getName());
                    TextView tv_expense = new TextView(MainActivity.this);
                    tv_expense.setText(String.format(Locale.US, "%.2f", i.getExpense()));
                    TextView tv_quota = new TextView(MainActivity.this);
                    tv_quota.setText(String.format(Locale.US, "%.2f", i.getQuota()));
                    TableRow row = new TableRow(MainActivity.this);
                    row.addView(tv_name);
                    row.addView(tv_expense);
                    row.addView(tv_quota);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ItemId = i.getId();
                            DialogFragment menuDialog = new MenuDialog();
                            menuDialog.show(getSupportFragmentManager(), "Menu");
                        }
                    });
                    table.addView(row);
                }
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
                int childCount = table.getChildCount();
                if (childCount > 1) {
                    table.removeViews(1, childCount - 1);
                }

                for (final Item i : allItemsInList) {
                    TextView tv_name = new TextView(MainActivity.this);
                    tv_name.setText(i.getName());
                    TextView tv_expense = new TextView(MainActivity.this);
                    tv_expense.setText(String.format(Locale.US, "%.2f", i.getExpense()));
                    TextView tv_quota = new TextView(MainActivity.this);
                    tv_quota.setText(String.format(Locale.US, "%.2f", i.getQuota()));
                    TableRow row = new TableRow(MainActivity.this);
                    row.addView(tv_name);
                    row.addView(tv_expense);
                    row.addView(tv_quota);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ItemId = i.getId();
                            DialogFragment menuDialog = new MenuDialog();
                            menuDialog.show(getSupportFragmentManager(), "Menu");
                        }
                    });
                    table.addView(row);
                }
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