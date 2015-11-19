package com.example.ssteffanus.feelingsdiary;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.ListView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.*;
import android.widget.ArrayAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private NavigationDrawerFragment mNavigationDrawerFragment;
    public static Bitmap defaultImage;
    private CharSequence mTitle;
    public static JournalClass mJournal = new JournalClass();
    static final int FEELING_ENTRY_CODE = 0;
    private String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defaultImage = BitmapFactory.decodeResource(getResources(),R.drawable.default_image);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.calendar:
                openCalendar();
                return true;
			case R.id.entry:
                createEntry();
                return true;
            case R.id.quit:
                exitRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void openCalendar() {
        Intent calendarIntent = new Intent(this, CalendarActivity.class);
        startActivity(calendarIntent);
    }
    private void exitRequested() {
        super.onBackPressed();
    }

	 private void createEntry() {
        DateFormat df = new SimpleDateFormat("MM dd, yyyy");
        DateFormat tf = new SimpleDateFormat("HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        String time = tf.format(Calendar.getInstance().getTime());

        /* Launch FeelingEntryActivty activity here */
         Intent getMood = new Intent(this, FeelingEntryActivity.class);
         startActivityForResult(getMood, FEELING_ENTRY_CODE);
         /* class variable mood should be now be set in OnActivityResult */


        HashMap<String, ArrayList<EntryClass>> mEntries = mJournal.getEntries();
        if (mEntries == null) {
            ArrayList<EntryClass> entryArrayList = new ArrayList<EntryClass>();
            entryArrayList.add(new EntryClass(date, time, mood));
            HashMap<String,ArrayList<EntryClass>> entryHash = new HashMap<String,ArrayList<EntryClass>>();
            entryHash.put(date,entryArrayList);
            mJournal.setEntries(entryHash);
        } else if (mEntries.get(date) == null) {
            ArrayList<EntryClass> entryArrayList = new ArrayList<EntryClass>();
            entryArrayList.add(new EntryClass(date, time, mood));
            mEntries.put(date,entryArrayList);
        } else {
            mEntries.get(date).add(new EntryClass(date,time,mood));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == FEELING_ENTRY_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                mood = data.getExtras().getString("MOOD");
            }
        }
    }
	

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
