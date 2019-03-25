package com.example.abc.waitinglistmanagementapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // MainActivity controls the Courses List and invokes Add Course Functionality

    // some basic variables
    List<String> list;
    ListView listView;
    TextView addCourse;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialization of variables
        list = new ArrayList<>();
        addCourse = (TextView) findViewById(R.id.addCourse);
        listView = (ListView) findViewById(R.id.courseList);
        myDb = new DatabaseHelper(this);

        // following code invokes AddCourse Function to add a new Course in the list
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCourse();
            }
        });

        // following code handles the clicks on any course item in the list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCourse =parent.getItemAtPosition(position).toString();
                Cursor c = myDb.getItemId(selectedCourse);

                Intent intent = new Intent(MainActivity.this, ViewStudents.class);
                intent.putExtra("course", selectedCourse);
                startActivity(intent);

            }
        });

        displayCourse();
    }

    public void AddCourse()
    {
        startActivity(new Intent(MainActivity.this, AddCourseActivity.class));
    }

    // following function accesses the database and retrieve courses
    public void displayCourse()
    {
        Cursor cursor = myDb.getCourse();
        if (cursor.getCount() == 0)
        {
            return;
        }
        else
        {
            while (cursor.moveToNext())
            {
                list.add(cursor.getString(1)); // all courses are put into the list
            }
            listView.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list));
        }
    }

}
