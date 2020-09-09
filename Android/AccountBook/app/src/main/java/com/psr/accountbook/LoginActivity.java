package com.psr.accountbook;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    UserViewModel user;

    // Global Flags
    VideoView BackgroundVideo = null;
    MediaPlayer GlobalMPFlag = null;
    int CurrentVideoPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = ViewModelProviders.of(this).get(UserViewModel.class);

        final EditText et_username = findViewById(R.id.et_username);
        final EditText et_password = findViewById(R.id.et_password);
        final TextView tv_info = findViewById(R.id.tv_info);

        // Set up Background Video
        BackgroundVideo = (VideoView) findViewById(R.id.login_video);
        Uri BgVideoUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bg1);
        BackgroundVideo.setVideoURI(BgVideoUri);
        BackgroundVideo.start();
        BackgroundVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // Save as global flag so we can use it to save the playback position in onPause()
                GlobalMPFlag = mp;
                mp.setLooping(true);
                // Jump to the position whereas last onPause()
                mp.seekTo(CurrentVideoPosition);
            }
        });

        // Login button
        Button ButtonLogin = findViewById(R.id.btn_login);
        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                try {
                    if(isPasswordCorrect(username, password)) {
                        login(username);
                    } else {
                        tv_info.setText(R.string.password_incorrect);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Registry button
        Button ButtonRegistry = findViewById(R.id.btn_registry);
        ButtonRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                try {
                    if (!isUserExist(username)) {
                        registry(username, password);
                        login(username);
                    } else {
                        tv_info.setText(R.string.user_already_exist);
                    }
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void login(String username) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private void registry(String username, String password) {
        user.addUser(username, password);
    }

    private boolean isUserExist(String username) throws ExecutionException, InterruptedException {
        return user.valUser(username);
    }

    private boolean isPasswordCorrect(String username, String password) throws ExecutionException, InterruptedException {
        return user.valUser(username, password);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Save the video playback position
        CurrentVideoPosition = GlobalMPFlag.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BackgroundVideo.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlobalMPFlag.release();
        GlobalMPFlag = null;
    }

}
