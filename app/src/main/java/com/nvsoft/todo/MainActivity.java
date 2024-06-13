package com.nvsoft.todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Manage Task");
                alert.setMessage("What you wont to do this Task?");
                alert.setCancelable(true);

                alert.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, TaskEditActivity.class);
                        intent.putExtra("task_position", position);
                        intent.putExtra("task_text", itemList.get(position));
                        startActivity(intent);
                    }
                });

                alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemList.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        FileManager.writeData(itemList,getApplicationContext());
                    }
                });

//                alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });


                AlertDialog alertDialog = alert.create();
                alertDialog.show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        itemList.addAll(FileManager.readData(this));
        arrayAdapter.notifyDataSetChanged();
    }
}
