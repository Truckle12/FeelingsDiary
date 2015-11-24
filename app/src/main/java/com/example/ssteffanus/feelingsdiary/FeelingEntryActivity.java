package com.example.ssteffanus.feelingsdiary;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.view.View;
import android.content.Intent;
import android.app.Activity;

public class FeelingEntryActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton IB_happy, IB_excited, IB_bored, IB_surprised, IB_calm, IB_disappointed, IB_sad, IB_scared, IB_angry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feeling_entry_layout);

        IB_happy = (ImageButton)findViewById(R.id.imageButton_happy);
        IB_excited = (ImageButton)findViewById(R.id.imageButton_excited);
        IB_bored = (ImageButton)findViewById(R.id.imageButton_bored);
        IB_surprised = (ImageButton)findViewById(R.id.imageButton_surprised);
        IB_calm = (ImageButton)findViewById(R.id.imageButton_calm);
        IB_disappointed = (ImageButton)findViewById(R.id.imageButton_disappointed);
        IB_sad = (ImageButton)findViewById(R.id.imageButton_sad);
        IB_scared = (ImageButton)findViewById(R.id.imageButton_scared);
        IB_angry = (ImageButton)findViewById(R.id.imageButton_angry);


        IB_happy.setOnClickListener(this);
        IB_excited.setOnClickListener(this);
        IB_bored.setOnClickListener(this);
        IB_surprised.setOnClickListener(this);
        IB_calm.setOnClickListener(this);
        IB_disappointed.setOnClickListener(this);
        IB_sad.setOnClickListener(this);
        IB_scared.setOnClickListener(this);
        IB_angry.setOnClickListener(this);
    }

    public void onClick(View v) {
        String mood = "";
        switch(v.getId()){
            case R.id.imageButton_happy:
                mood = "happy";
                break;
            case R.id.imageButton_excited:
                mood = "excited";
                break;
            case R.id.imageButton_bored:
                mood = "bored";
                break;
            case R.id.imageButton_surprised:
                mood = "surprised";
                break;
            case R.id.imageButton_calm:
                mood = "calm";
                break;
            case R.id.imageButton_disappointed:
                mood = "disappointed";
                break;
            case R.id.imageButton_sad:
                mood = "sad";
                break;
            case R.id.imageButton_scared:
                mood = "scared";
                break;
            case R.id.imageButton_angry:
                mood = "angry";
                break;
        }

        Intent result = new Intent(FeelingEntryActivity.this, MainActivity.class);
        result.putExtra("MOOD", mood);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feeling_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
