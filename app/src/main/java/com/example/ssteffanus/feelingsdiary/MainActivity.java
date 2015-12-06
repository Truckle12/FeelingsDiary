package com.example.ssteffanus.feelingsdiary;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.app.ActivityManager.RunningTaskInfo;
import java.util.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private NavigationDrawerFragment mNavigationDrawerFragment;
    public static Bitmap defaultImage;
    public static Bitmap happyImage, sadImage, excitedImage, calmImage, disappointedImage, angryImage, surprisedImage, boredImage, scaredImage;
    private CharSequence mTitle;
    public static JournalClass mJournal = new JournalClass();
    static final int FEELING_ENTRY_CODE = 0;
    private String EntryMood; //most recently selected mood
    private StringBuffer EntryText; //most recently entered text entry

    private String date, time;
    public String TAG ="Testing";
    Boolean firstOpen= true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaultImage = BitmapFactory.decodeResource(getResources(),R.drawable.default_image);
        happyImage = BitmapFactory.decodeResource(getResources(),R.drawable.happy_puppy);
        sadImage = BitmapFactory.decodeResource(getResources(),R.drawable.sad_puppy);
        angryImage = BitmapFactory.decodeResource(getResources(),R.drawable.angry_puppy);
        scaredImage = BitmapFactory.decodeResource(getResources(),R.drawable.scared_puppy);
        excitedImage = BitmapFactory.decodeResource(getResources(),R.drawable.excited_puppy);
        calmImage = BitmapFactory.decodeResource(getResources(),R.drawable.calm_puppy);
        disappointedImage = BitmapFactory.decodeResource(getResources(),R.drawable.disappointed_puppy);
        surprisedImage = BitmapFactory.decodeResource(getResources(),R.drawable.surprised_puppy);
        boredImage = BitmapFactory.decodeResource(getResources(),R.drawable.bored_puppy);


        defaultImage = BitmapFactory.decodeResource(getResources(),R.drawable.default_image);
        //firstOpen = true;
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        final ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createEntry();
            }
        });


    }
    
    private void openCalendar() {
        Intent calendarIntent = new Intent(this, CalendarActivity.class);
        startActivity(calendarIntent);
    }
    private void exitRequested() {
        super.onBackPressed();
    }


    @Override
    protected  void onStart(){
        super.onStart();
        if(authenticate() != true){
            Intent intent_login = new Intent(this, LoginActivity.class);
            startActivity(intent_login);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        getSupportActionBar().setTitle("Home");
        if(authenticate() != true){
                Intent intent_login = new Intent(this, LoginActivity.class);
                startActivity(intent_login);
        }
    }

    private boolean authenticate() {
        SharedPreferences preferences = getSharedPreferences("credentials", MODE_PRIVATE);
        String username = preferences.getString("username", "defaultvalue");
        String password = preferences.getString("password", "defaultvalue");
        String loggedin = preferences.getString("loggedin", "defaultvalue");
        if (username.toString().equals("defaultvalue")) {
            Intent intent_register = new Intent(this, RegisterActivity.class);
            startActivity(intent_register);
            return true;
        }else if(loggedin.toString().equals("false")){
            return false;
        }
        return true;
    }


    private void finishEntry() {
        Log.e(TAG, "mood value: "+EntryMood);
        Log.e(TAG, "text value: "+EntryText);

        HashMap<String, ArrayList<EntryClass>> mEntries = mJournal.getEntries();
        if (mEntries == null) {
            ArrayList<EntryClass> entryArrayList = new ArrayList<EntryClass>();
            entryArrayList.add(new EntryClass(date, time, EntryMood, EntryText));
            HashMap<String,ArrayList<EntryClass>> entryHash = new HashMap<String,ArrayList<EntryClass>>();
            entryHash.put(date,entryArrayList);
            mJournal.setEntries(entryHash);
        } else if (mEntries.get(date) == null) {
            ArrayList<EntryClass> entryArrayList = new ArrayList<EntryClass>();
            entryArrayList.add(new EntryClass(date, time, EntryMood, EntryText));
            mEntries.put(date,entryArrayList);
        } else {
            mEntries.get(date).add(new EntryClass(date,time,EntryMood, EntryText));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == FEELING_ENTRY_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                EntryMood = data.getExtras().getString("MOOD");
                EntryText = new StringBuffer(data.getExtras().getString("TEXT"));
                finishEntry();
            }
        }
    }

    private void createEntry() {
        DateFormat df = new SimpleDateFormat("MM dd yyyy");
        DateFormat tf = new SimpleDateFormat("HH:mm");
        date = df.format(Calendar.getInstance().getTime());
        time = tf.format(Calendar.getInstance().getTime());

        /* Launch FeelingEntryActivty activity here */
        Intent getMood = new Intent(this, FeelingEntryActivity.class);
        startActivityForResult(getMood, FEELING_ENTRY_CODE);
         /* class variable mood should be now be set in OnActivityResult */

    }


    //SET UP THE ON CLICKS HERE
    // 0 = profile,
    // 1 = Settings
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Log.i(TAG,"the position is " + position);
        // update the main content by replacing fragments
        if(firstOpen){ // When app runs the position will always be 0- need to fix this problem
            firstOpen = false;
        }else if(position == 0){ // open entry
            createEntry();
        }else if (position == 1){ // open Calender
            openCalendar();
        }else if(position == 2){ // Settings
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
        }else if (position == 3){ // status
            HashMap<String,ArrayList<EntryClass>> moods = mJournal.getEntries();
            if(moods != null) {
                String emotion = findEmotion(moods);
                Intent intent = new Intent(this, Summary.class);
                Log.i(TAG, "setting up Intent in MainActivity  and the emotion is " + emotion);
                intent.putExtra("mood", emotion);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "No entries exist yet", Toast.LENGTH_SHORT).show();
            }

        }else if( position == 4 ){  //LOGIN
            SharedPreferences preferences = getSharedPreferences("credentials", MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = preferences.edit();
            prefEditor.putString("loggedin", "false");
            prefEditor.commit();
            Intent intent_login = new Intent(this, LoginActivity.class);
            startActivity(intent_login);
        }

    }

    public String findEmotion(HashMap<String, ArrayList<EntryClass>> mood){
        HashMap<String,Integer> finish = new HashMap<String, Integer>();
        int maxEntry = 0;
        String maxString = null;
        Integer integer =0;
        int conv =0;
        for(String s: mood.keySet()){
            Log.i(TAG, "the key is " +s);
            if(s != null) {
                ArrayList<EntryClass> arr = mood.get(s);
                for (int j = 0; j < arr.size(); j++) {
                    Log.i(TAG, "the entry is " + arr.get(j));
                    String moodEntry = arr.get(j).getEntryMood();
                    if(moodEntry != null) {
                        Log.i(TAG, "the string is " + moodEntry);
                        if (!finish.containsKey(moodEntry)) {
                            finish.put(moodEntry, 1);
                        } else { //increment the count
                            Log.i(TAG, "the integer is " + finish.get(moodEntry));
                            integer = finish.get(moodEntry);
                            //conv = integer.intValue();
                            //conv = conv + 1;
                            integer = integer +1;
                            finish.put(moodEntry, integer);
                        }
                    }
                }
            }
        }

        for(String s: finish.keySet()){
            Log.i(TAG,"s in finish hash is :"+s);
            Log.i(TAG,"count in finish hash is :"+finish.get(s));
            if (finish.get(s).intValue() > maxEntry){
                Log.i(TAG, "YESSS!");
                maxEntry= finish.get(s);
                maxString = s;
                Log.i(TAG,"maxString is "+maxString);

            }
        }
        Log.i(TAG,"returning maxString "+maxString);
        return maxString;
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle= getString(R.string.open_entry);
                break;
            case 2:
                mTitle= getString(R.string.open_calendar);
                break;
            case 3:
                mTitle = getString(R.string.title_section3); //status
                break;
            case 4:
                mTitle = getString(R.string.title_section2); //settings
                break;
            case 6:
                mTitle ="Log Out";
                break;
        }
    }
    
}
