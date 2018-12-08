package com.poojithabijjam.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudentActivity extends AppCompatActivity {
    DatabaseReference myRef;
    EditText name;
    EditText rollno;
    Button add;
    Student s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        myRef = FirebaseDatabase.getInstance().getReference();

        name = (EditText) findViewById(R.id.name);
        rollno = (EditText) findViewById(R.id.rollno);
        add = (Button) findViewById(R.id.addbtn);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = new Student(rollno.getText().toString(), name.getText().toString());
                try {
                    if ((rollno.getText().toString().isEmpty()) || (name.getText().toString().isEmpty())) {
                        Toast.makeText(getApplicationContext(), "Enter valid details", Toast.LENGTH_SHORT).show();
                    } else {

                        myRef.child("studs/" + String.valueOf(s.getRollNum())).setValue(s);
                        Toast.makeText(getApplicationContext(), "student added", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Enter valid details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
