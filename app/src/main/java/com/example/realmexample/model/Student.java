package com.example.realmexample.model;

import io.realm.RealmObject;

public class Student extends RealmObject {
    private String studentName;
    private int studentMarks;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentMarks() {
        return studentMarks;
    }

    public void setStudentMarks(int studentMarks) {
        this.studentMarks = studentMarks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", studentMarks=" + studentMarks +
                '}';
    }
}
