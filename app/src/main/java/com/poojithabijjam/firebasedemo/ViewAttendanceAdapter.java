package com.poojithabijjam.firebasedemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ViewAttendanceAdapter extends ArrayAdapter<Student> {
    Context context;
    int resource;
    List<Student> stud_list;
    LayoutInflater inflater;
    View view;

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

    public ViewAttendanceAdapter(@NonNull ViewAttendanceActivity context, int resource, List<Student> stud_list) {
        super(context, resource, stud_list);
        this.context = context;
        this.resource = resource;
        this.stud_list = stud_list;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, parent, false);
        }
        TextView name = view.findViewById(R.id.tview);
        final Student stud = stud_list.get(position);

        name.setText(stud.getName());

        return view;
    }
}
