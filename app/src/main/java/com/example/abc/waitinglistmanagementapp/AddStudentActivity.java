package com.example.abc.waitinglistmanagementapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {

    // needed variables
    EditText enterStdName, enterStdCourse, enterStdPriority;
    Button addStudentButton, cancelStudentButton;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // initialization of variables
        enterStdName = (EditText) findViewById(R.id.enterStdName);
        enterStdCourse = (EditText) findViewById(R.id.enterStdCourse);
        enterStdPriority = (EditText) findViewById(R.id.enterStdPriority);
        addStudentButton = (Button) findViewById(R.id.addStudentButton);
        cancelStudentButton = (Button) findViewById(R.id.cancelStudentButton);


        // handling onClick events
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewStudent();
            }
        });
        cancelStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddStudentActivity.this, MainActivity.class));
            }
        });
    }

    // following code adds a new student to the database
    public void AddNewStudent()
    {
        myDb = new DatabaseHelper(this);
        boolean result = myDb.insertStudent(enterStdName.getText().toString(),
                enterStdCourse.getText().toString(),
                enterStdPriority.getText().toString());

        if (result == true)
        {
            Toast.makeText(this, "Successfully Added !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddStudentActivity.this, MainActivity.class));

        }
        else
        {
            Toast.makeText(this, "Failed !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddStudentActivity.this, MainActivity.class));

        }
    }
}
