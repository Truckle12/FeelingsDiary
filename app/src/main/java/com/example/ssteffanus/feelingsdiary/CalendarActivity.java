package com.example.ssteffanus.feelingsdiary;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ssteffanus on 11/6/2015.
 */
public class CalendarActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        final CalendarView calendarView=(CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                HashMap<String,ArrayList<EntryClass>> entries =  MainActivity.mJournal.getEntries();

                String dateStr;

                if (dayOfMonth < 10) {
                    dateStr = (month+1)+" 0"+dayOfMonth+" "+year;
                } else {
                    dateStr = (month+1) + " " + dayOfMonth + " " + year;
                }

                if (entries == null || entries.get(dateStr) == null) {
                    Toast.makeText(getApplicationContext(), "No entry exists on this day", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle b = new Bundle();
                    b.putString("dateStr", dateStr);
                    Intent dIntent = new Intent(CalendarActivity.this, DaySelectActivity.class);
                    dIntent.putExtras(b);
                    startActivity(dIntent);
                }

            }
        });
    }
}
