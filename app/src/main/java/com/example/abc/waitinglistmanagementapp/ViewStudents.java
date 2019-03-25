package com.example.abc.waitinglistmanagementapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewStudents extends AppCompatActivity {

    // needed variables
    TextView addStudent;
    List<Student> list;
    ListView listView;
    DatabaseHelper myDb;
    String selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        // initialization of variables
        myDb = new DatabaseHelper(this);
        addStudent = (TextView) findViewById(R.id.addStudent);
        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.studentList);

        Intent receivedIntent = getIntent();
        selectedCourse = receivedIntent.getStringExtra("course"); // to get the name of caller course

        // jumping to AddStudentActivity
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewStudents.this, AddStudentActivity.class));
            }
        });

        // following code deals with what happens on clicking on any list item (student)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student std = (Student) parent.getItemAtPosition(position);
                Cursor c = myDb.getItemId(std.getName());
                int itemId = -1;
                while (c.moveToNext())
                {
                    itemId = c.getInt(0);
                }
                if (itemId > -1)
                {
                    Intent intent = new Intent(ViewStudents.this, EditStudentActivity.class);
                    intent.putExtra("id", itemId);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(ViewStudents.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        displayStudents(selectedCourse);
    }

    // following function retrieves data from database and displays on list
    public void displayStudents(String selectedCourse)
    {

        Cursor cursor = myDb.getData(selectedCourse);
        if (cursor.getCount() == 0)
        {
            return;
        }
        else
        {
            while (cursor.moveToNext())
            {
                list.add(new Student(cursor.getString(1), " Course : "+ cursor.getString(2), " Priority : "+cursor.getString(3)));
            }
            listView.setAdapter(new CustomAdapter(ViewStudents.this, R.layout.student_layout, list));
        }
    }
}
