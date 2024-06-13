package com.nvsoft.todo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button newTaskAdd;
    ListView taskList;
    ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newTaskAdd = findViewById(R.id.add_new_task_button);
        taskList = findViewById(R.id.taskListView);

        newTaskAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskAddActivity.class);
                startActivity(intent);
            }
        });

        itemList = FileManager.readData(this);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList);
        taskList.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        itemList.addAll(FileManager.readData(this));
        arrayAdapter.notifyDataSetChanged();
    }
}
