package com.example.abc.waitinglistmanagementapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditStudentActivity extends AppCompatActivity {

    EditText editStdName, editStdCourse, editStdPriority;
    Button saveStudentButton, deleteStudentButton, cancelEditButton;
    DatabaseHelper myDb;
    int selectedId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        editStdName = (EditText) findViewById(R.id.editStdName);
        editStdCourse = (EditText) findViewById(R.id.editStdCourse);
        editStdPriority = (EditText) findViewById(R.id.editStdPriority);
        saveStudentButton = (Button) findViewById(R.id.saveStudentButton);
        deleteStudentButton= (Button) findViewById(R.id.deleteStudentButton);
        cancelEditButton = (Button) findViewById(R.id.cancelEditButton);
        myDb = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();
        selectedId = receivedIntent.getIntExtra("id", -1);

        setValues(selectedId);
        saveStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.updateStudent(selectedId, editStdName.getText().toString(), editStdCourse.getText().toString(), editStdPriority.getText().toString());
                Toast.makeText(EditStudentActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditStudentActivity.this, MainActivity.class));
            }
        });

        deleteStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.deleteStudent(selectedId);
                Toast.makeText(EditStudentActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditStudentActivity.this, MainActivity.class));
            }
        });
    }

    public void setValues(int selectedId)
    {

        Cursor cursor = myDb.getDataAt(selectedId);
        if (cursor.getCount() == 0)
        {
            return;
        }
        else
        {
            while (cursor.moveToNext())
            {
                editStdName.setText(cursor.getString(1));
                editStdCourse.setText(cursor.getString(2));
                editStdPriority.setText(cursor.getString(3));
            }
        }
    }

}
