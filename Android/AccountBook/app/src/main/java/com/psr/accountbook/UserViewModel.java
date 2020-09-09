package com.psr.accountbook;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepo;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepository(application);
    }

    public LiveData<List<User>> getAllUser() {
        return userRepo.getAllUsers();
    }

    public void addUser(String name, String password) {
        userRepo.addUser(name, password);
    }

    public void delUser(int ItemId) {
        userRepo.delUser(ItemId);
    }

    public void modUser(int ItemId, String name, String password) {
        userRepo.modUser(ItemId, name, password);
    }

    public boolean valUser(String name, String password) throws ExecutionException, InterruptedException {
       return userRepo.valUser(name, password);
    }

    public boolean valUser(String name) throws ExecutionException, InterruptedException {
        return userRepo.valUser(name);
    }
}
