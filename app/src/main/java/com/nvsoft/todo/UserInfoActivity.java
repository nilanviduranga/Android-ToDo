package com.nvsoft.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    Button goToHome;
    Button signOut;

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

        goToHome = findViewById(R.id.go_home);
        signOut = findViewById(R.id.sign_out);

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

        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an AlertDialog.Builder and set the message, title, and buttons
                AlertDialog.Builder builder = new AlertDialog.Builder(UserInfoActivity.this);
                builder.setMessage("Are you sure you want to sign out?")
                        .setTitle("Sign Out Confirmation");

                // Set the positive button with a listener to handle the sign-out action
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Sign out the user
                        Session.username = null;
                        Session.user_password = null;
                        Intent intent = new Intent(UserInfoActivity.this, SignInActivity.class);
                        startActivity(intent);
                        // Optionally finish the current activity
                        finish();
                    }
                });

                // Set the negative button to simply dismiss the dialog
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog, do nothing
                        dialog.dismiss();
                    }
                });

                // Create and show the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }




}
