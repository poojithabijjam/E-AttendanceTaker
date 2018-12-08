package com.poojithabijjam.firebasedemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StudAttndncDetails extends AppCompatActivity {
double count_p=0.0;
double count_a=0.0;
double x;
ArrayList<String> xvalues;
ArrayList<Boolean> yvalues;
    LineChart lineChart;

    ArrayList<String> xAxes = new ArrayList<>();
    ArrayList<Entry> yAxes = new ArrayList<>();
    ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
    int m=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_attndnc_details);
    final Student s = (Student) getIntent().getExtras().get("stud");
        ((TextView) findViewById(R.id.namestud)).setText(s.getName());
        ((TextView) findViewById(R.id.rollnumstud)).setText(s.getRollNum());

        ArrayList<String> attnc = new ArrayList<String>();
        xvalues=new ArrayList<>();
        yvalues=new ArrayList<>();
        lineChart = (LineChart)findViewById(R.id.Lchart);

        for (Map.Entry<String, Boolean> att : s.attendence.entrySet()) {
//            mymap.put(att.getKey(),att.getValue());
            xvalues.add(att.getKey());
            yvalues.add(att.getValue());
            if (att.getValue()) {
                count_p++;
               x=(((count_p)/(count_a+count_p))*100);
               x=(Math.round(x*100.0)/100.0);
                attnc.add(att.getKey() + ": Present"+" "+x);
            }
            else {
                count_a++;
               x=(((count_p)/(count_a+count_p))*100);
                x=(Math.round(x*100.0)/100.0);
                attnc.add(att.getKey() + ": Absent"+" "+x);
            }
        }

        ListView attanance = findViewById(R.id.attendance);
        AttendanceAdapter adapter = new AttendanceAdapter(this, R.layout.viewlist, attnc);
        attanance.setAdapter(adapter);
        for(String p :xvalues){
            xAxes.add(p);
        }
        for(boolean q:yvalues)
        {
            int k=q?1:0;
            yAxes.add(new Entry(k,m++));
        }
        String[] xaxes = new String[xAxes.size()];

        for(int i=0;i<xAxes.size();i++)
        {
            xaxes[i] = xAxes.get(i).toString();
        }

        LineDataSet lineDataSet = new LineDataSet(yAxes,"Values");
        lineDataSet.setDrawCircles(true);
        lineDataSet.setColor(Color.BLUE);

        lineDataSets.add(lineDataSet);

        lineChart.setData(new LineData(xaxes,lineDataSets));

        lineChart.setVisibleXRangeMaximum(65f);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);

    }
}
