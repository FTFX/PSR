package com.psr.accountbook;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    private ItemRepository itemRepo;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemRepo = new ItemRepository(application);
    }

    public LiveData<List<Item>> getAllItems() {
        return itemRepo.getAllItems();
    }

    public void addItem(String name, double expense, double quota) {
        itemRepo.addItem(name, expense, quota);
    }

    public void delItem(int ItemId) {
        Item target = new Item("1", 1, 1);
        target.setId(ItemId);
        itemRepo.delItem(ItemId);
    }

    public void modItem(int ItemId, String name, double expense, double quota) {
        Item target = new Item(name, expense, quota);
        target.setId(ItemId);
        itemRepo.modItem(ItemId, name, expense, quota);
    }

    public void getItem(String name) {
        itemRepo.getItem(name);
    }

}
