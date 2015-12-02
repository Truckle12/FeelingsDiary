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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_entry_layout);
        title = (TextView)findViewById(R.id.title);
        text = (EditText)findViewById(R.id.editText);
        clear = (Button)findViewById(R.id.buttonClear);
        submit = (Button)findViewById(R.id.buttonSubmit);

        String mood = getIntent().getExtras().getString("MOOD");
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
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_entry, menu);
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