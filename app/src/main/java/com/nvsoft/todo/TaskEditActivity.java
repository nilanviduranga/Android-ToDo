package com.nvsoft.todo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

public class TaskEditActivity extends AppCompatActivity {

    EditText task;
    Button submit;
    ArrayList<String> itemList;
    int taskPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_edit);

        // Validate the user session
        SessionManager.validateSession(this);

        task = findViewById(R.id.editTaskName);
        submit = findViewById(R.id.taskSubmitButton);

        itemList = FileManager.readData(this);
        taskPosition = getIntent().getIntExtra("task_position", -1);
        String taskText = getIntent().getStringExtra("task_text");

        task.setText(taskText);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedTask = task.getText().toString();
                if (taskPosition != -1) {
                    itemList.set(taskPosition, updatedTask);
                    FileManager.writeData(itemList, getApplicationContext());
                }
                finish(); // Close this activity and return to MainActivity
            }
        });
    }
}
