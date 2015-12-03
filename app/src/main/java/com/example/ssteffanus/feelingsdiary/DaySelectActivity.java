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
        mTextView.setText(dateStr);

        ArrayList<EntryClass> mEntries = MainActivity.mJournal.getEntries().get(dateStr);
        if (mEntries != null) {
            int numEntries = mEntries.size();
            LinearLayout mLayout = (LinearLayout) findViewById(R.id.daySelectLinearLayout);

            RelativeLayout[] mRLayoutsOuter = new RelativeLayout[numEntries];
            RelativeLayout[] mRLayoutsInner = new RelativeLayout[numEntries];

            ImageView[] mImageViews = new ImageView[numEntries];
            Button[] mButtons = new Button[numEntries];

            RelativeLayout.LayoutParams mParamsOuter = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams mParamsImage = new RelativeLayout.LayoutParams(320,170);
            RelativeLayout.LayoutParams mParamsButton = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            RelativeLayout.LayoutParams[] mParamsInner = new RelativeLayout.LayoutParams[numEntries];

            for (int i=0; i<numEntries; i++) {
                EntryClass e = mEntries.get(i);
                mRLayoutsOuter[i]  = new RelativeLayout(this);
                mRLayoutsInner[i] = new RelativeLayout(this);

                /* case e.getMoodString() { ....} */

                mImageViews[i] = new ImageView(getApplicationContext());
                mImageViews[i].setImageBitmap(MainActivity.defaultImage);

                mParamsInner[i] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mParamsInner[i].addRule(RelativeLayout.RIGHT_OF, mImageViews[i].getId());

                mButtons[i] = new Button(getApplicationContext());
                mButtons[i].setText(e.getEntryTime());
                mButtons[i].setOnClickListener(myButtonListener);

                mRLayoutsInner[i].addView(mButtons[i], mParamsButton);
                mRLayoutsOuter[i].addView(mImageViews[i], mParamsImage);
                mRLayoutsOuter[i].addView(mRLayoutsInner[i], mParamsInner[i]);
                mLayout.addView(mRLayoutsOuter[i], mParamsOuter);

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
}

