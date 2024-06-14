package com.nvsoft.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DevInfoActivity extends AppCompatActivity {

    Button goToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_info);

        goToHome = findViewById(R.id.goto_home);
        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DevInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}