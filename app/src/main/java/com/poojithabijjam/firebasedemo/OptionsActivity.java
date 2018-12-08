package com.poojithabijjam.firebasedemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class OptionsActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;

    private AlarmManager alarmMgr;
    private Context context;
    private PendingIntent alarmIntent;

   // Uri personPhoto = acct.getPhotoUrl();

    FirebaseAuth mAuth;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ImageView iv = (ImageView) findViewById(R.id.iv);
     //  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//      Toast.makeText(getApplicationContext(),user.getDisplayName(),Toast.LENGTH_SHORT).show();
        //Intent intent=new Intent();
        GoogleSignInAccount account =( GoogleSignInAccount)getIntent().getExtras().get("account");
        Toast.makeText(OptionsActivity.this,"options"+account.getDisplayName(),Toast.LENGTH_SHORT).show();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 20);

//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();

       /* alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        if (alarmMgr!= null) {
            alarmMgr.cancel(alarmIntent);
        }
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

*/
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
       // View header = navigationView.inflateHeaderView(R.layout.nav_header);
        View header = navigationView.getHeaderView(0);

        TextView mFullNameTextView = (TextView) header.findViewById(R.id.fullname);
        //TextView emailTextView=(TextView)header.findViewById(R.id.email);
        mFullNameTextView.setText(account.getDisplayName());
//        iv.setImageResource(R.mipmap.ic_pic);
//emailTextView.setText(account.getEmail());
navigationView.getMenu().getItem(2).setChecked(true);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        int id=menuItem.getItemId();
                        switch (id)
                        {
                            case R.id.takeatt:
                                Intent intent1=new Intent(OptionsActivity.this,TakeAttActivity.class);
                                startActivity(intent1);
                                break;
                            case R.id.addstud:
                                Intent intent2=new Intent(OptionsActivity.this,AddStudentActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.viewatt:
                                Intent intent3=new Intent(OptionsActivity.this,ViewAttendanceActivity.class);
                                startActivity(intent3);
                                break;
                            case R.id.abt:
                                Intent intent4=new Intent(OptionsActivity.this,AboutActivity.class);

                                                                startActivity(intent4);
                                                                break;




                        }
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });



    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
/*    protected void onStart() {
        super.onStart();

        //if the user is not logged in
        //opening the login activity
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, GoogleActivity.class));
        }
    }*/
}
