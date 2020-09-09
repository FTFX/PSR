package com.psr.accountbook;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {
    private LiveData<List<User>> allUsers;
    private UserDao userDao;
    private boolean result = false;

    UserRepository(Context context) {
        Database database = Database.getInstance(context.getApplicationContext());
        userDao = database.getUserDao();
        allUsers = userDao.getAllUsers();
    }

    LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void addUser(String name, String password) {
        new AddUserOperation(userDao).execute(new User(name, password));
    }

    public void delUser(int UserId) {
        User target = new User("1", "1");
        target.setId(UserId);
        new DeleteUserOperation(userDao).execute(target);
    }

    public void modUser(int UserId, String name, String password) {
        User target = new User(name, password);
        target.setId(UserId);
        new UpdateUserOperation(userDao).execute(target);
    }

    public boolean valUser(String name, String password) throws ExecutionException, InterruptedException {
        new ValidateUserOperation(userDao, name, password, this).execute().get();
        return result;
    }

    public boolean valUser(String name) throws ExecutionException, InterruptedException {
        new IsUserExistOperation(userDao, name, this).execute().get();
        return result;
    }

    static class AddUserOperation extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public AddUserOperation(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.addUser(users);
            return null;
        }
    }

    static class UpdateUserOperation extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public UpdateUserOperation(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.modUser(users);
            return null;
        }
    }

    static class DeleteUserOperation extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public DeleteUserOperation(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delUser(users);
            return null;
        }
    }

    static class ValidateUserOperation extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;
        private String name;
        private String password;
        private UserRepository userRepo;

       public ValidateUserOperation(UserDao userDao, String name, String password, UserRepository userRepo) {
           this.userDao = userDao;
           this.name = name;
           this.password = password;
           this.userRepo = userRepo;
       }

        @Override
       protected Void doInBackground(Void... voids) {
           if (userDao.valUser(name, password) != 0) {
               userRepo.result = true;
           } else {
               userRepo.result = false;
           }
           return null;
       }
    }

    static class IsUserExistOperation extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;
        private String name;
        private UserRepository userRepo;

        public IsUserExistOperation(UserDao userDao, String name, UserRepository userRepo) {
            this.userDao = userDao;
            this.name = name;
            this.userRepo = userRepo;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (userDao.valUser(name) != 0) {
                userRepo.result = true;
            } else {
                userRepo.result = false;
            }
            return null;
        }
    }
}
