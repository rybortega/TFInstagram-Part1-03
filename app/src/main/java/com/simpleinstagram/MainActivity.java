package com.simpleinstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private Button btnLogout;
    private Button btnPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                goLogin();
            }
        });
        btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPost();
            }
        });
    }

    private void goPost() {
        Log.d(TAG, "Navigating to the post page");
        Intent i = new Intent(this, PostActivity.class);
        startActivity(i);
        finish();
    }

    private void goLogin() {
        Log.d(TAG, "Navigating to the login page");
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
