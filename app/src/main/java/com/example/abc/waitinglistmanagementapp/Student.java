package com.example.abc.waitinglistmanagementapp;

class Student {

    private String name, course, priority;

    public Student(String name, String course, String priority) {
        this.name = name;
        this.course = course;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getPriority() {
        return priority;
    }
}
