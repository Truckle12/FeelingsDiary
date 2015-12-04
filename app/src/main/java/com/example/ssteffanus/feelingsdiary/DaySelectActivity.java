package com.example.ssteffanus.feelingsdiary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ssteffanus on 11/15/2015.
 */

public class DaySelectActivity extends Activity{

    public String dateStr;
    public String TAG ="Testing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_select_layout);
        Bundle b = getIntent().getExtras();
        dateStr = b.getString("dateStr");
        TextView mTextView = (TextView) findViewById(R.id.textView2);
        mTextView.setText(convertDateToText(dateStr));

        ArrayList<EntryClass> mEntries = MainActivity.mJournal.getEntries().get(dateStr);
        if (mEntries != null) {
            int numEntries = mEntries.size();
            LinearLayout mLayout = (LinearLayout) findViewById(R.id.daySelectLinearLayout);

            LinearLayout[] mInnerLayouts = new LinearLayout[numEntries];
            ImageView[] mImageViews = new ImageView[numEntries];
            Button[] mButtons = new Button[numEntries];

            LinearLayout.LayoutParams mParamsInner = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mParamsInner.setMargins(0,0,0,20);
            LinearLayout.LayoutParams mParamsImage = new LinearLayout.LayoutParams(0,170);
            LinearLayout.LayoutParams mParamsButton = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT);
            mParamsImage.weight = 2;
            mParamsButton.weight = 1;

            for (int i=0; i<numEntries; i++) {
                EntryClass e = mEntries.get(i);

                mInnerLayouts[i] = new LinearLayout(this);
                mInnerLayouts[i].setOrientation(LinearLayout.HORIZONTAL);

                /* case e.getMoodString() { ....} */

                mImageViews[i] = new ImageView(getApplicationContext());
                mImageViews[i].setImageBitmap(MainActivity.defaultImage);

               // Log.e(TAG,"date: "+ convertDateToText(dateStr));
                mButtons[i] = new Button(getApplicationContext());
                mButtons[i].setText(e.getEntryTime());
                mButtons[i].setOnClickListener(myButtonListener);

                mInnerLayouts[i].addView(mButtons[i], mParamsButton);
                mInnerLayouts[i].addView(mImageViews[i], mParamsImage);
                mLayout.addView(mInnerLayouts[i], mParamsInner);

            }
        }
    }
    View.OnClickListener myButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
            Button button = (Button) v;
            String time = button.getText().toString();
            Intent intent = new Intent(DaySelectActivity.this, EntrySelectActivity.class);
            Bundle b = new Bundle();
            b.putString("dateStr", dateStr);
            b.putString("timeStr", time);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }
    };

    public static String convertDateToText(String numDate){
        String textDate = "";
        String[] splitStr = numDate.split("\\s+");
        switch (splitStr[0]){
            case "1": textDate += "January"; break;
            case "2": textDate += "February"; break;
            case "3": textDate += "March"; break;
            case "4": textDate += "April"; break;
            case "5": textDate += "May"; break;
            case "6": textDate += "June"; break;
            case "7": textDate += "July"; break;
            case "8": textDate += "August"; break;
            case "9": textDate += "September"; break;
            case "10": textDate += "October"; break;
            case "11": textDate += "November"; break;
            case "12": textDate += "December"; break;
        }
        if (splitStr[1].charAt(0) == '0') {
            textDate += " " + splitStr[1].charAt(1);
        } else {
            textDate += " " + splitStr[1];
        }
        textDate += ", " + splitStr[2];

        return textDate;
    }
}

