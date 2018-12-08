package com.poojithabijjam.firebasedemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAttendanceActivity extends AppCompatActivity {
    DatabaseReference myRef;
    ViewAttendanceAdapter adapter;
    ArrayList<Student> studs = new ArrayList<>();
    ListView listView;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);
        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.show();

        listView = findViewById(R.id.lv);
        myRef = FirebaseDatabase.getInstance().getReference("studs");

        adapter = new ViewAttendanceAdapter(this, R.layout.viewlist, studs);
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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent studDetails = new Intent(ViewAttendanceActivity.this, StudAttndncDetails.class);
                studDetails.putExtra("stud", studs.get(i));
                startActivity(studDetails);
            }
        });


    }

}
