package com.example.abc.waitinglistmanagementapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Student> {

    Context context;
    int resource;
    List<Student> list;

    public CustomAdapter(Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, null);

        TextView stdName = view.findViewById(R.id.stdName);
        TextView stdCourse = view.findViewById(R.id.stdCourse);
        TextView stdPriority = view.findViewById(R.id.stdPriority);

        Student student = list.get(position);

        stdName.setText(student.getName());
        stdCourse.setText(student.getCourse());
        stdPriority.setText(student.getPriority());

        return view;
    }
}
