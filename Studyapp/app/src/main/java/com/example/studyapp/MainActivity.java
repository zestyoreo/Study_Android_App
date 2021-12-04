package com.example.studyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DataBase DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = DataBase.getInstance(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawer, toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        // Set the text for each tab.
        tabLayout.addTab(tabLayout.newTab().setText("Plans"));
        tabLayout.addTab(tabLayout.newTab().setText("Exams"));
        tabLayout.addTab(tabLayout.newTab().setText("Assignments"));
        tabLayout.addTab(tabLayout.newTab().setText("Lectures"));
        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Use PagerAdapter to manage page views in fragments.
        final ViewPager2 viewPager = (ViewPager2) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), getLifecycle(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        // Setting a listener for clicks.
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
                super.onPageSelected(position);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_home:
                // Handle the camera import action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                recreate();
                return true;
            case R.id.nav_cal:
                // Handle the gallery action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                //redirect to calendar activity
                Log.i("Activity", " inside Calender");
                Intent intent = new Intent(this, Calendar.class);
                //start activity
                startActivity(intent);
                Log.i("Activity", " Calender started");
                return true;
            default:
                return false;
        }
    }
    public void Refresh(View v){
        recreate();
    }
    public void InsertPlan(View v){
        Date start;
        String startT;
        Date end;
        String endT;
        try {
            startT = ((EditText) findViewById(R.id.StartDate)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Starting Date format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            startT = startT + " " +((EditText) findViewById(R.id.StartTime)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Starting Time format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            endT = ((EditText) findViewById(R.id.EndDate)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Ending Date format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            endT = endT + " " +((EditText) findViewById(R.id.EndTime)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Ending Time format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            start = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US).parse(startT);
        }catch(Exception e){
            Toast.makeText(this, "Error while parsing starting date!",Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            end = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US).parse(endT);
        }catch(Exception e){
            Toast.makeText(this, "Error while parsing ending date!",Toast.LENGTH_SHORT).show();
            return;
        }
        DB.insertData(((EditText)findViewById(R.id.EventTitle)).getText().toString(),((EditText)findViewById(R.id.EventDesc)).getText().toString(),start,end,0 );
    }
    public void InsertAssignment(View v){
        Date start;
        String startT;
        Date end;
        String endT;
        try {
            startT = ((EditText) findViewById(R.id.StartDate)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Starting Date format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            startT = startT + " " +((EditText) findViewById(R.id.StartTime)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Starting Time format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            endT = ((EditText) findViewById(R.id.EndDate)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Ending Date format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            endT = endT + " " +((EditText) findViewById(R.id.EndTime)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Ending Time format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            start = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US).parse(startT);
        }catch(Exception e){
            Toast.makeText(this, "Error while parsing starting date!",Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            end = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US).parse(endT);
        }catch(Exception e){
            Toast.makeText(this, "Error while parsing ending date!",Toast.LENGTH_SHORT).show();
            return;
        }
        DB.insertData(((EditText)findViewById(R.id.EventTitle)).getText().toString(),((EditText)findViewById(R.id.EventDesc)).getText().toString(),start,end,1 );
    }
    public void InsertExam(View v){
        Date start;
        String startT;
        Date end;
        String endT;
        try {
            startT = ((EditText) findViewById(R.id.StartDate)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Starting Date format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            startT = startT + " " +((EditText) findViewById(R.id.StartTime)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Starting Time format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            endT = ((EditText) findViewById(R.id.EndDate)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Ending Date format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            endT = endT + " " +((EditText) findViewById(R.id.EndTime)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Ending Time format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            start = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US).parse(startT);
        }catch(Exception e){
            Toast.makeText(this, "Error while parsing starting date!",Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            end = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US).parse(endT);
        }catch(Exception e){
            Toast.makeText(this, "Error while parsing ending date!",Toast.LENGTH_SHORT).show();
            return;
        }
        DB.insertData(((EditText)findViewById(R.id.EventTitle)).getText().toString(),((EditText)findViewById(R.id.EventDesc)).getText().toString(),start,end,2 );
    }
    public void InsertLec(View v){
        Date start;
        String startT;
        Date end;
        String endT;
        try {
            startT = ((EditText) findViewById(R.id.StartDate)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Starting Date format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            startT = startT + " " +((EditText) findViewById(R.id.StartTime)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Starting Time format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            endT = ((EditText) findViewById(R.id.EndDate)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Ending Date format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            endT = endT + " " +((EditText) findViewById(R.id.EndTime)).getText().toString();
        }catch(Exception e){
            Toast.makeText(this, "Ending Time format Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            start = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US).parse(startT);
        }catch(Exception e){
            Toast.makeText(this, "Error while parsing starting date!",Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            end = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US).parse(endT);
        }catch(Exception e){
            Toast.makeText(this, "Error while parsing ending date!",Toast.LENGTH_SHORT).show();
            return;
        }
        DB.insertData(((EditText)findViewById(R.id.EventTitle)).getText().toString(),((EditText)findViewById(R.id.EventDesc)).getText().toString(),start,end,3 );
    }
}