package com.nvsoft.todo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

public class TaskAddActivity extends AppCompatActivity {

    EditText task;
    Button submit;
    ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);

        task = findViewById(R.id.newTaskName);
        submit = findViewById(R.id.taskSubmitButton);

        itemList = FileManager.readData(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = task.getText().toString();
                itemList.add(itemName);
                FileManager.writeData(itemList, getApplicationContext());
                finish();  // Close this activity and return to MainActivity
            }
        });
    }
}
