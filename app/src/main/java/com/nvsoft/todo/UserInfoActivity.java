package com.nvsoft.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {

    TextView userNameDisplay;
    TextView userEmailDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        // Assuming R.id.username_textview and R.id.email_textview are defined in activity_user_info layout
        userNameDisplay = findViewById(R.id.username_textview);
        userEmailDisplay = findViewById(R.id.email_textview);

        // Get the username from Session or intent extras
        String userName = Session.username; // Assuming Session.username holds the username

        // Fetch the user's email using UserManager
        String userEmail = UserManager.getUserEmail(UserInfoActivity.this, userName);

        // Set values to TextViews
        userNameDisplay.setText(userName);
        userEmailDisplay.setText(userEmail);

    }

}
