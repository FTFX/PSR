package com.psr.accountbook;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder> {
    private List<Item> allItems = new ArrayList<>();
    private Context mainActivity;

    public ItemViewAdapter(Context context) {
        this.mainActivity = context;
    }

    public void setAllItems(List<Item> allItems) {
        this.allItems = allItems;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.view_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        Item item = allItems.get(position);
        holder.tv_item.setText(item.getName());
        holder.tv_expense.setText(String.format(Locale.US, "%.2f", item.getExpense()));
        holder.tv_quota.setText(String.format(Locale.US, "%.2f", item.getQuota()));
        if (item.getExpense() > item.getQuota()) {
            holder.tv_item.setTextColor(Color.RED);
            holder.tv_expense.setTextColor(Color.RED);
            holder.tv_quota.setTextColor(Color.RED);
        } else {
            holder.tv_item.setTextColor(Color.BLACK);
            holder.tv_expense.setTextColor(Color.BLACK);
            holder.tv_quota.setTextColor(Color.BLACK);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment menuDialog = new MenuDialog();
                MainActivity activity = (MainActivity)mainActivity;
                activity.ItemId = allItems.get(position).getId();
                activity.ItemPosition = position;
                menuDialog.show(activity.getSupportFragmentManager(), "Menu");
            }
        });
    }

    @Override
    public int getItemCount() {
        return allItems.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item;
        TextView tv_expense;
        TextView tv_quota;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(R.id.tv_item);
            tv_expense = itemView.findViewById(R.id.tv_expense);
            tv_quota = itemView.findViewById(R.id.tv_quota);
        }
    }
}
