package com.psr.books;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String Username = null;
    Database db = null;
    UserDao user = null;
    TextView tv_all_users = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = getIntent().getStringExtra("username");

        StringBuilder Greetings = chooseAProperGreetingsAccordingToTOD();
        TextView tv_greetings = findViewById(R.id.tv_greetings);
        tv_greetings.setText(Greetings);
    }

    protected StringBuilder chooseAProperGreetingsAccordingToTOD() {
        StringBuilder Greetings = new StringBuilder();
        Calendar c = Calendar.getInstance();
        int TimeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(TimeOfDay >= 0 && TimeOfDay < 6){
            Greetings.append("Good Evening, ");
        }else if(TimeOfDay >= 6 && TimeOfDay < 12){
            Greetings.append("Good Morning, ");
        }else if(TimeOfDay >= 12 && TimeOfDay < 16){
            Greetings.append("Good Afternoon, ");
        }else if(TimeOfDay >= 18 && TimeOfDay < 24){
            Greetings.append("Good Evening, ");
        }
        System.out.println(Username);
        Greetings.append(Username).append(".");

        // Ready to show all users
        db = Room.databaseBuilder(this, Database.class, "user_database")
                .allowMainThreadQueries()
                .build();
        user = db.getDao();
        tv_all_users = findViewById(R.id.tv_all_users);
        updateView(tv_all_users, user.getAllUsers());

        // Add user button
        Button btn_add = findViewById(R.id.btn_add_user);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment addFragment = new AddUserDialog();
                addFragment.show(getSupportFragmentManager(), "Add User");
            }
        });

        // Mod user button
        Button btn_mod = findViewById(R.id.btn_mod_user);
        btn_mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment modFragment = new ModUserDialog();
                modFragment.show(getSupportFragmentManager(), "Modify User");
            }
        });

        // Del user button
        Button btn_del = findViewById(R.id.btn_del_user);
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment delFragment = new DelUserDialog();
                delFragment.show(getSupportFragmentManager(), "Delete User");
            }
        });

        return Greetings;
    }

    private void updateView(TextView tv_all_users, List<User> userList) {
        StringBuilder info = new StringBuilder();
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            info.append("ID: ").append(user.getId()).append("\t")
                    .append("Username: ").append(user.getUsername()).append("\t")
                    .append("Password: ").append(user.getPassword()).append("\n");
        }
        tv_all_users.setText(info);
    }

    public void addUser(String username, String password) {
        user.addUser(new User(username, password));
        updateView(tv_all_users, user.getAllUsers());
    }

    public void modUser(int id, String username, String password) {
        User target = new User(username, password);
        target.setId(id);
        user.modUser(target);
        updateView(tv_all_users, user.getAllUsers());
    }

    public void delUser(int id) {
        User target = new User("", "");
        target.setId(id);
        user.delUser(target);
        updateView(tv_all_users, user.getAllUsers());
    }

    public void srcUser(String username) {
        List<User> userList = user.getAllUsers();
        for(User i : userList) {
            if (i.getUsername() == username) {

            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
