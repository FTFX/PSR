package com.psr.accountbook;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    private ItemDao itemDao;
    private LiveData<List<Item>> allItemsLiveData;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        Database database = Database.getInstance(application);
        itemDao = database.getItemDao();
        allItemsLiveData = itemDao.getAllItems();
    }

    public LiveData<List<Item>> getAllItemsLiveData() {
        return allItemsLiveData;
    }

    public void addItem(String name, double expense, double quota) {
        new AddItemOperation(itemDao).execute(new Item(name, expense, quota));
    }

    public void delItem(int ItemId) {
        Item target = new Item("1", 1, 1);
        target.setId(ItemId);
        new DeleteItemOperation(itemDao).execute(target);
    }

    public void modItem(int ItemId, String name, double expense, double quota) {
        Item target = new Item(name, expense, quota);
        target.setId(ItemId);
        new UpdateItemOperation(itemDao).execute(target);
    }

    public void getItem(String name) {
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
