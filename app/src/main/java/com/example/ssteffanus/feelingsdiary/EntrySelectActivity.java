package com.example.ssteffanus.feelingsdiary;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ssteffanus on 11/15/2015.
 */
public class EntrySelectActivity extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.entry_select_layout);
            ImageView image = (ImageView)findViewById(R.id.imageView);
            image.setImageBitmap(MainActivity.defaultImage);

            Bundle b = getIntent().getExtras();

            String date = b.getString("dateStr");
            String time = b.getString("timeStr");
            EntryClass mEntry = null;

            ArrayList<EntryClass> mEntries = MainActivity.mJournal.getEntries().get(date);
            for (int i=0; i<mEntries.size(); i++) {
                if (mEntries.get(i).getEntryTime().equals(time)) {
                    mEntry = mEntries.get(i);

                    TextView text = (TextView) findViewById(R.id.textView1);
                    EditText text2 = (EditText) findViewById(R.id.editText1);
                    text.setText(date+" @ "+time);
                    text2.setText(mEntry.getEntryText());

                }
            }

        }
}

