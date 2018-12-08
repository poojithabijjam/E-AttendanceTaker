package com.poojithabijjam.firebasedemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttendanceAdapter extends ArrayAdapter<String> {
    Context context;
    int resource;
    ArrayList<String> attnc;
    LayoutInflater inflater;
    View view;

    public AttendanceAdapter(@NonNull StudAttndncDetails context, int resource, ArrayList<String> attnc) {
        super(context, resource, attnc);
        this.context = context;
        this.resource = resource;
        this.attnc = attnc;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, parent, false);
        }

        ((TextView)view.findViewById(R.id.tview)).setText(attnc.get(position));

        return view;
    }
}
