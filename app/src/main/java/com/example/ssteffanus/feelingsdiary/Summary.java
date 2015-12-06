package com.example.ssteffanus.feelingsdiary;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ShelbySilverstein on 11/23/15.
 */
public class Summary  extends Activity {
    TextView title;
    TextView editText;
    TextView diagnosisText;
    Bundle getInt;
    String emotion;
    String TAG ="Summary.java";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.summary_layout);
        title = (TextView) findViewById(R.id.title_summ);
        editText = (TextView) findViewById(R.id.editText_summ);
        diagnosisText = (TextView) findViewById(R.id.diagnosis_summ);


        getInt= getIntent().getExtras();
        emotion = (String) getInt.getString("mood");
        Log.i(TAG, "got the intent in Summary- This is the EMOTION : " + emotion);

        //Display the emotion on the screen
        switch(emotion){
            case "Happy":
                editText.setText("HAPPY");
                break;
            case "Sad":
                editText.setText("SAD");
                break;
            case "Excited":
                editText.setText("EXCITED");
                break;
            case "Bored":
                editText.setText("BORED");
                break;
            case "Surprised":
                editText.setText("SURPRISED");
                break;
            case "Calm":
                editText.setText("CALM");
                break;
            case "Disappointed":
                editText.setText("DISAPPOINTED");
                break;
            case "Scared":
                editText.setText("SCARED");
                break;
            case "Angry":
                editText.setText("ANGRY");
                break;
            default:
                editText.setText("ERROR");

        }

        diagnose(emotion);
    }

    //Tell the user some advice about their emotion
    public void diagnose(String emotion){
        switch(emotion){
            case "Happy":
                diagnosisText.setText(R.string.diagnosis_happy);
                break;
            case "Sad":
                diagnosisText.setText(R.string.diagnosis_sad);
                break;
            case "Excited":
                diagnosisText.setText(R.string.diagnosis_excited);
                break;
            case "Bored":
                diagnosisText.setText(R.string.diagnosis_bored);
                break;
            case "Surprised":
                diagnosisText.setText(R.string.diagnosis_surprised);
                break;
            case "Calm":
                diagnosisText.setText(R.string.diagnosis_calm);
                break;
            case "Disappointed":
                diagnosisText.setText(R.string.diagnosis_disappointed);
                break;
            case "Scared":
                diagnosisText.setText(R.string.diagnosis_scared);
                break;
            case "Angry":
                diagnosisText.setText(R.string.diagnosis_angry);
                break;
            default:
                diagnosisText.setText("ERROR!");

        }
    }
}
