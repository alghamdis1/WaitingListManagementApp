package com.example.abc.waitinglistmanagementapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCourseActivity extends AppCompatActivity {

    // needed variables
    EditText enterCourseName;
    Button addCourseButton;
    Button cancelCourseButton;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        // initialization
        enterCourseName = (EditText) findViewById(R.id.enterCourseName);
        addCourseButton = (Button) findViewById(R.id.addCourseButton);
        cancelCourseButton = (Button) findViewById(R.id.cancelCourseButton);

        myDb = new DatabaseHelper(this);

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewCourse();
            }
        });

        // cancel button takes user to the MainActivity
        cancelCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddCourseActivity.this, MainActivity.class));
            }
        });
    }

    // Adding new course to the list
    public void AddNewCourse()
    {
        String s = enterCourseName.getText().toString();
        boolean result = myDb.insertCourse(s);
        if (result == true)
        {
            Toast.makeText(this, "Successfully Added !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddCourseActivity.this, MainActivity.class));
        }
        else
        {
            Toast.makeText(this, "Failed !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddCourseActivity.this, MainActivity.class));

        }
    }
}
