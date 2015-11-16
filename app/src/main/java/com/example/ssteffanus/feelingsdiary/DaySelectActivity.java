package com.example.ssteffanus.feelingsdiary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ssteffanus on 11/15/2015.
 */

public class DaySelectActivity extends Activity implements View.OnClickListener{

    public String dateStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_select_layout);
        Bundle b = getIntent().getExtras();
        dateStr = b.getString("dateStr");
        TextView mTextView = (TextView) findViewById(R.id.textView2);
        mTextView.setText(dateStr);

        ArrayList<EntryClass> mEntries = MainActivity.myJournal.getEntries().get(dateStr);
        if (mEntries != null) {
            int numEntries = mEntries.size();
            LinearLayout mLayout = (LinearLayout) findViewById(R.id.daySelectLinearLayout);
            RelativeLayout[] mRLayouts = new RelativeLayout[numEntries];


            for (int i=0; i<numEntries; i++) {
                EntryClass e = mEntries.get(i);

                /* case e.getMoodString() { ....} */
                RelativeLayout.LayoutParams mParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                mParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

                ImageView mImage = new ImageView(getApplicationContext());
                mImage.setImageBitmap(MainActivity.defaultImage);
                Button mButton = new Button(getApplicationContext());
                mButton.setText(e.getEntryTime());

                mRLayouts[i].addView(mImage, mParams);
                mParams.addRule(RelativeLayout.RIGHT_OF, mImage.getId());
                mRLayouts[i].addView(mButton, mParams);

                mLayout.addView(mRLayouts[i]);

            }
        }
    }

    public void onClick(View v) {
        Button button = (Button) v;
        String time = button.getText().toString();
        Intent intent = new Intent(DaySelectActivity.this, EntrySelectActivity.class);
        Bundle b = new Bundle();
        b.putString("dateStr",dateStr);
        b.putString("timeStr", time); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        finish();
    }
}

