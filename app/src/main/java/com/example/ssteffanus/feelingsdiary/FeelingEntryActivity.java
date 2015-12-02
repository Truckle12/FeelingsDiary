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
    static final int TEXT_ENTRY_CODE = 1;
    String text = "";

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
                mood = "Happy";
                break;
            case R.id.imageButton_excited:
                mood = "Excited";
                break;
            case R.id.imageButton_bored:
                mood = "Bored";
                break;
            case R.id.imageButton_surprised:
                mood = "Surprised";
                break;
            case R.id.imageButton_calm:
                mood = "Calm";
                break;
            case R.id.imageButton_disappointed:
                mood = "Disappointed";
                break;
            case R.id.imageButton_sad:
                mood = "Sad";
                break;
            case R.id.imageButton_scared:
                mood = "Scared";
                break;
            case R.id.imageButton_angry:
                mood = "Angry";
                break;
        }

        /* sets class variable for entry's text */
        getEntryText(mood);

        /* pass mood and text back to main */
        Intent result = new Intent(FeelingEntryActivity.this, MainActivity.class);
        result.putExtra("MOOD", mood);
        result.putExtra("TEXT", text);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    private void getEntryText(String mood){

        Intent textIntent = new Intent(FeelingEntryActivity.this, TextEntryActivity.class);
        textIntent.putExtra("MOOD", mood);
        startActivityForResult(textIntent, TEXT_ENTRY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == TEXT_ENTRY_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                text = data.getExtras().getString("TEXT");
            }
        }
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
