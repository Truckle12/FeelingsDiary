package com.example.ssteffanus.feelingsdiary;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class TextEntryActivity extends AppCompatActivity {

    TextView title;
    EditText text;
    Button clear, submit;
    String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_entry_layout);
        title = (TextView)findViewById(R.id.title);
        text = (EditText)findViewById(R.id.editText);
        clear = (Button)findViewById(R.id.buttonClear);
        submit = (Button)findViewById(R.id.buttonSubmit);
        getSupportActionBar().setTitle("Create New Entry");

        mood = getIntent().getExtras().getString("MOOD");
        title.setText("Why Are You Feeling "+mood+"?");

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* pass text back to FeelingEntryActivity */
                Intent result = new Intent(TextEntryActivity.this, FeelingEntryActivity.class);
                result.putExtra("TEXT", text.getText().toString());
                result.putExtra("MOOD", mood);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }

}