package com.nvsoft.todo;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class UserManager {

    public static final String FILENAME = "userinfo.dat";

    public static boolean addUser(Context context, String username, String email, String password) {
        HashMap<String, User> users = readData(context);
        if (users.containsKey(username)) {
            return false; // User already exists
        } else {
            users.put(username, new User(username, email, password));
            writeData(users, context);
            return true;
        }
    }

    public static boolean validateUser(Context context, String username, String password) {
        HashMap<String, User> users = readData(context);
        return users.containsKey(username) && users.get(username).getPassword().equals(password);
    }

    private static void writeData(HashMap<String, User> users, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HashMap<String, User> readData(Context context) {
        HashMap<String, User> users = new HashMap<>();
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (HashMap<String, User>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
    public static String getUserEmail(Context context, String username) {
        HashMap<String, User> users = readData(context);
        if (users.containsKey(username)) {
            return users.get(username).getEmail();
        } else {
            return null; // or handle appropriately if user does not exist
        }
    }

    public static boolean updateUserInfo(Context context, String oldUsername, String newUsername, String newEmail) {
        HashMap<String, User> users = readData(context);
        if (users.containsKey(oldUsername)) {
            User user = users.get(oldUsername);
            user.setUsername(newUsername);

            String oldFileName = oldUsername + ".dat";
            String newFileName = newUsername + ".dat";

            boolean renamed = renameFile(context, oldFileName, newFileName);

            Session.username = newUsername;



            user.setEmail(newEmail);
            if (!oldUsername.equals(newUsername)) {
                users.remove(oldUsername);
                users.put(newUsername, user);
            }
            writeData(users, context);
            return true;
        } else {
            return false; // User does not exist
        }
    }

    public static boolean renameFile(Context context, String oldFileName, String newFileName) {
        File oldFile = new File(context.getFilesDir(), oldFileName);
        File newFile = new File(context.getFilesDir(), newFileName);

        // Rename the file
        if (oldFile.exists()) {
            return oldFile.renameTo(newFile);
        } else {
            // Handle case where the old file doesn't exist
            return false;
        }
    }





}
