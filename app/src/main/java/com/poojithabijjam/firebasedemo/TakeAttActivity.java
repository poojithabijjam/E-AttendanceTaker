package com.poojithabijjam.firebasedemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TakeAttActivity extends AppCompatActivity {
    ListView listView;
    DatabaseReference myRef;
    StudentAdapter adapter;
    ArrayList<Student> studs = new ArrayList<>();
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        listView = findViewById(R.id.listv);
        myRef = FirebaseDatabase.getInstance().getReference("studs");
        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.show();

        String today = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime());
        ((TextView) findViewById(R.id.tvDate)).setText(today);

        adapter = new StudentAdapter(TakeAttActivity.this, R.layout.takelist, studs, today);
        listView.setAdapter(adapter);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studs.clear();
                for (DataSnapshot student : dataSnapshot.getChildren()) {
                    studs.add((Student) student.getValue(Student.class));
                }
                listView.setAdapter(adapter);
                mProgressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mProgressDialog.dismiss();
                        }
        });


    }

}