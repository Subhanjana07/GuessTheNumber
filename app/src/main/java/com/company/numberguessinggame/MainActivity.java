package com.company.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    RadioButton radioButton1,radioButton2,radioButton3;
    Button start;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Number Guessing Game");

        textView = findViewById(R.id.textView7);

        String bulletPoints = "<ul>" +
                "<li>The number I keep in my mind should match the number you are choosing</li>" +
                "&nbsp;<li>Never forget that you have 10 chances only</li>" +
                "</ul>";
        textView.setText(Html.fromHtml(bulletPoints, Html.FROM_HTML_MODE_COMPACT));

        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        start =  findViewById(R.id.button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,GameActivity.class);

                if(!radioButton1.isChecked() && !radioButton2.isChecked() && !radioButton3.isChecked())
                {
                    Snackbar.make(view,"Please select a number of digits first",Snackbar.LENGTH_LONG).show();
                }

                else{
                    if(radioButton1.isChecked())
                    {
                        intent.putExtra("two",true);
                    }
                    if(radioButton2.isChecked())
                    {
                        intent.putExtra("three",true);
                    }if(radioButton3.isChecked())
                    {
                        intent.putExtra("four",true);
                    }
                    startActivity(intent);
                }
            }
        });


    }
}