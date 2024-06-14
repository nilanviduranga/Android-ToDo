package com.nvsoft.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {

    TextView userNameDisplay;
    TextView userEmailDisplay;
    String userName = Session.username;
    String userEmail;

    Button editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        // Validate the user session
        SessionManager.validateSession(this);

        // Assuming R.id.username_textview and R.id.email_textview are defined in activity_user_info layout
        userNameDisplay = findViewById(R.id.username_textview);
        userEmailDisplay = findViewById(R.id.email_textview);
        editProfile = findViewById(R.id.edit_profile_button);

        // Get the username from Session or intent extras
         // Assuming Session.username holds the username

        // Fetch the user's email using UserManager
        userEmail = UserManager.getUserEmail(UserInfoActivity.this, userName);

        // Set values to TextViews
        userNameDisplay.setText(userName);
        userEmailDisplay.setText(userEmail);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( UserInfoActivity.this, UserInfoEditActivity.class);
                intent.putExtra("username", userName);
                intent.putExtra("email", userEmail);
                startActivity(intent);
            }
        });

    }



}
