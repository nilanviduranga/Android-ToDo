package com.nvsoft.todo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserInfoEditActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText emailEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_edit);

        // Validate the user session
        SessionManager.validateSession(this);

        usernameEditText = findViewById(R.id.edit_username_edittext);
        emailEditText = findViewById(R.id.edit_email_edittext);
        saveButton = findViewById(R.id.save_button);

        // Retrieve username and email from Intent extras
        String username = getIntent().getStringExtra("username");
        String email = getIntent().getStringExtra("email");

        // Set current username and email in EditText fields
        usernameEditText.setText(username);
        emailEditText.setText(email);

        // Handle Save button click
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get updated username and email from EditText fields
                String updatedUsername = usernameEditText.getText().toString().trim();
                String updatedEmail = emailEditText.getText().toString().trim();

                // Update username and email in UserManager (if necessary)
                UserManager.updateUserInfo(UserInfoEditActivity.this, username, updatedUsername, updatedEmail);

                // Navigate back to UserInfoActivity
                Intent intent = new Intent(UserInfoEditActivity.this, UserInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
