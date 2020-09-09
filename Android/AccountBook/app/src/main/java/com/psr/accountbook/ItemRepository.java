package com.psr.accountbook;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

class ItemRepository {
    private LiveData<List<Item>> allItems;
    private ItemDao itemDao;

    ItemRepository(Context context) {
        Database database = Database.getInstance(context.getApplicationContext());
        itemDao = database.getItemDao();
        allItems = itemDao.getAllItems();
    }

    LiveData<List<Item>> getAllItems() {
        return allItems;
    }

    void addItem(String name, double expense, double quota) {
        new AddItemOperation(itemDao).execute(new Item(name, expense, quota));
    }

    void delItem(int ItemId) {
        Item target = new Item("1", 1, 1);
        target.setId(ItemId);
        new DeleteItemOperation(itemDao).execute(target);
    }

    void modItem(int ItemId, String name, double expense, double quota) {
        Item target = new Item(name, expense, quota);
        target.setId(ItemId);
        new UpdateItemOperation(itemDao).execute(target);
    }

    void getItem(String name) {
        //return new GetItemOperation(itemDao).execute(name);
    }

    static class AddItemOperation extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;

        public AddItemOperation(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.addItem(items);
            return null;
        }
    }

    static class UpdateItemOperation extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;

        public UpdateItemOperation(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.modItem(items);
            return null;
        }
    }

    static class DeleteItemOperation extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;

        public DeleteItemOperation(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.delItem(items);
            return null;
        }
    }

    static class GetItemOperation extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        public GetItemOperation(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String... name) {
            return itemDao.getItem(name);
        }
    }


}
