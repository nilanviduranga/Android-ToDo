package com.nvsoft.todo;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager {
    String fileName = Session.username;
    public static String FILENAME = Session.username + ".dat";

    public static void writeData(ArrayList<String> item, Context context) {
        FILENAME = Session.username + ".dat";
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            oas.writeObject(item);
            oas.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> readData(Context context) {
        ArrayList<String> itemList = null;
        FILENAME = Session.username + ".dat";
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemList = (ArrayList<String>) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            itemList = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }
}
