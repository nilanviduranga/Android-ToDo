package com.nvsoft.todo;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class UserManager {

    public static final String FILENAME = "userinfo.dat";

    public static boolean addUser(Context context, String email, String password) {
        HashMap<String, String> users = readData(context);
        if (users.containsKey(email)) {
            return false; // User already exists
        } else {
            users.put(email, password);
            writeData(users, context);
            return true;
        }
    }

    public static boolean validateUser(Context context, String email, String password) {
        HashMap<String, String> users = readData(context);
        return users.containsKey(email) && users.get(email).equals(password);
    }

    private static void writeData(HashMap<String, String> users, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            oas.writeObject(users);
            oas.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HashMap<String, String> readData(Context context) {
        HashMap<String, String> users = new HashMap<>();
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (HashMap<String, String>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
}
