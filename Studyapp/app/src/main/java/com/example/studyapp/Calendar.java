package com.example.studyapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Calendar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM-yyyy", Locale.getDefault());
    DataBase db;
    TextView Cal_month;
    TextView Cal_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawer, toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }


        compactCalendar = (CompactCalendarView) findViewById(R.id.compact_calendar_view);
        db = DataBase.getInstance(this);
        Cal_month = (TextView) findViewById(R.id.month_text_view);
        Cal_date = (TextView) findViewById(R.id.date_text_view);
        //compactCalendar.setUseThreeLetterAbbreviation(true);
        LinkedList<event> E;
        try {
            E = db.getAll();
        } catch (ParseException e) {
            E = new LinkedList<event>();
        }
        // add a random event
        Date date = new Date();
        Cal_month.setText(dateFormat.format(date.getTime()));

        String date_clicked = date.toString().substring(0,10);
        Cal_date.setText(date_clicked);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        //long time = date.getTime();
        //Log.i("time",String.valueOf(time));
        for (int i = 0; i < E.size(); i++) {
            event z = E.get(i);
            LocalDateTime localDates = LocalDateTime.parse(z.getStart().toString(), formatter);
            long timeInMilliseconds = localDates.atOffset(ZoneOffset.ofHoursMinutes(0,0)).toInstant().toEpochMilli();
            // Log.i("Time", "Date in milli :: FOR API >= 26 >>> " + timeInMilliseconds);

            Event ev1 = new Event(Color.RED,timeInMilliseconds, z);

            compactCalendar.addEvent(ev1);

        }


        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                String date_clicked = dateClicked.toString().substring(0,10);
                Cal_date.setText(date_clicked);
                List<Event> d = compactCalendar.getEvents(dateClicked);
                String s = "You have the following Events : \n";
                int Np = 0,Ne = 0, Na = 0,Nl = 0;
                for(int i = 0;i<d.size();i++){
                    Event k = d.get(i);
                    event o = (event) k.getData();
                    s = s + o.getTitle() + "\n";
                    switch(o.getType()){
                        case 0:
                            //PAEL
                            Np++;
                            break;
                        case 1:
                            Na++;
                            break;
                        case 2:
                            Ne++;
                            break;
                        case 3:
                            Nl++;
                            break;
                    }

                }
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                // add database queries and stuff to set text views here
                TextView np = findViewById(R.id.num_plans_text);
                TextView ne = findViewById(R.id.num_exams_text);
                TextView na = findViewById(R.id.num_assignments_text);
                TextView nl = findViewById(R.id.num_lectures_text);
                np.setText(String.valueOf(Np));
                ne.setText(String.valueOf(Ne));
                na.setText(String.valueOf(Na));
                nl.setText(String.valueOf(Nl));


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Cal_month.setText(dateFormat.format(firstDayOfNewMonth));
            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_home:
                drawer.closeDrawer(GravityCompat.START);
                //redirect to calendar activity
                Log.i("Activity", " inside Calender");
                Intent intent = new Intent(this, MainActivity.class);
                //start activity
                startActivity(intent);
                Log.i("Activity", " Calender started");

                return true;
            case R.id.nav_cal:
                drawer.closeDrawer(GravityCompat.START);
                recreate();
                return true;
            default:
                return false;
        }
    }
}