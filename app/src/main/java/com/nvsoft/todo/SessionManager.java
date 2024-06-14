package com.nvsoft.todo;

import android.app.Activity;
import android.content.Intent;

public class SessionManager {
    // Method to validate the user session
    public static void validateSession(Activity activity) {
        String username = Session.username;
        String password = Session.user_password;

        if (username != null && password != null) {
            if (!UserManager.validateUser(activity, username, password)) {
                redirectToSignIn(activity);
            }
        } else {
            // Handle the case where username or password is null
            redirectToSignIn(activity);
        }
    }

    // Private method to redirect to SignInActivity
    private static void redirectToSignIn(Activity activity) {
        Intent intent = new Intent(activity, SignInActivity.class);
        activity.startActivity(intent);
        activity.finish(); // Finish the current activity
    }
}
