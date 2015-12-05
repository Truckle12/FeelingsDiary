package com.example.ssteffanus.feelingsdiary;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ssteffanus on 11/15/2015.
 */
public class EntrySelectActivity extends Activity {
    public String TAG ="Testing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_select_layout);
        ImageView image = (ImageView)findViewById(R.id.imageView);


        Bundle b = getIntent().getExtras();

        String date = b.getString("dateStr");
        String time = b.getString("timeStr");
        EntryClass mEntry = null;

        ArrayList<EntryClass> mEntries = MainActivity.mJournal.getEntries().get(date);
        for (int i=0; i<mEntries.size(); i++) {
            if (mEntries.get(i).getEntryTime().equals(time)) {
                mEntry = mEntries.get(i);

                switch (mEntry.getEntryMood()) {
                    case "Happy": image.setImageBitmap(MainActivity.happyImage); break;
                    case "Sad" : image.setImageBitmap(MainActivity.sadImage); break;
                    case "Calm": image.setImageBitmap(MainActivity.calmImage);break;
                    case "Disappointed": image.setImageBitmap(MainActivity.disappointedImage);break;
                    case "Scared": image.setImageBitmap(MainActivity.scaredImage);break;
                    case "Excited": image.setImageBitmap(MainActivity.excitedImage);break;
                    case "Bored": image.setImageBitmap(MainActivity.boredImage); break;
                    case "Angry": image.setImageBitmap(MainActivity.angryImage); break;
                    case "Surprised": image.setImageBitmap(MainActivity.surprisedImage); break;
                }

                TextView text = (TextView) findViewById(R.id.textView1);
                TextView text2 = (TextView) findViewById(R.id.textView2);
                text.setText(DaySelectActivity.convertDateToText(date)+" @ "+time);
                text2.setText(mEntry.getEntryText());

            }
        }

    }
}

