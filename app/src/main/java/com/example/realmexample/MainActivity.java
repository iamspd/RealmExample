package com.example.realmexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realmexample.model.Student;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    // Widgets
    private EditText mStudentName, mStudentMarks;
    private Button mSubmit;
    private TextView mDataLog;

    // Variables
    private static final String ACTIVITY_NAME = MainActivity.class.getSimpleName();
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRealm = Realm.getDefaultInstance();

        findAllViews();
    }

    private void findAllViews() {
        mStudentName = findViewById(R.id.etStudentName);
        mStudentMarks = findViewById(R.id.etStudentMarks);
        mSubmit = findViewById(R.id.btnSubmit);
        mDataLog = findViewById(R.id.tvDataLog);
    }

    public void dbInsert(final String sName, final int sMarks){
        mRealm.executeTransactionAsync(new Realm.Transaction() {
                                           @Override
                                           public void execute(Realm bgRealm) {
                                               Student objStudent = bgRealm.createObject(Student.class);
                                               objStudent.setStudentName(sName);
                                               objStudent.setStudentMarks(sMarks);
                                           }
                                       }, new Realm.Transaction.OnSuccess() {
                                           @Override
                                           public void onSuccess() {
                                               // Original queries and Realm objects are automatically updated.
                                               Toast.makeText(MainActivity.this, "Data inserted " +
                                                       "successfully.", Toast.LENGTH_LONG).show();
                                               Log.v(ACTIVITY_NAME, "Data Inserted");
                                           }
                                       }, new Realm.Transaction.OnError() {
                                           @Override
                                           public void onError(Throwable error) {
                                                Log.e(ACTIVITY_NAME, error.getMessage());
                                           }
                                       });
    }

    public void showData(){
        RealmResults<Student> showStudents = mRealm.where(Student.class).findAll();

        String data = "";
        mRealm.beginTransaction();
        for (Student student : showStudents){
            data += student.toString();
        }

        mDataLog.setText(data);
    }
}