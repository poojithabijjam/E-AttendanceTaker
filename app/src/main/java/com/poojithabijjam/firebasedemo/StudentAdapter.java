package com.poojithabijjam.firebasedemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    Context context;
    int resource;
    List<Student> stud_list;
    LayoutInflater inflater;
    View view;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    String today = null;
    boolean checked=false;
    CheckBox cb;

    public StudentAdapter(@NonNull TakeAttActivity context, int resource, List<Student> stud_list, String today) {
        super(context, resource, stud_list);
        this.context = context;
        this.resource = resource;
        this.stud_list = stud_list;
        this.today = today;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, parent, false);
        }

        cb = (CheckBox) view.findViewById(R.id.chkbx);
        TextView name = view.findViewById(R.id.tv);
        final Student stud = stud_list.get(position);

        name.setText(stud.getName());
        if( stud_list.get(position).getAttendence().get(today)!=null && stud_list.get(position).getAttendence().get(today) )
        {
            cb.setChecked(stud.getAttendence().get(today));
        }
cb.setSelected(false);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b){
                checked=b;
                stud_list.get(position).attendence.put(today, b);
                myRef.child("studs/" + String.valueOf(stud_list.get(position).getRollNum())).setValue(stud_list.get(position));
            }
        });

        return view;
    }

    @Override
    public int getCount() {
        return stud_list.size();
    }

}
