package com.company.numberguessinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView textViewLast,textViewChance,textViewHint;
    EditText guess;
    Button confirm;
    Boolean twoDigits,threeDigits,fourDigits;

    Random random = new Random();
    int r,chancesRemaining = 10,guessCount = 0;
    ArrayList<Integer> guessList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Number Guessing Game");

        textViewLast = findViewById(R.id.textView);
        textViewChance = findViewById(R.id.textView2);
        textViewHint = findViewById(R.id.textView4);
        guess =  findViewById(R.id.editTextNumber);
        confirm = findViewById(R.id.button2);

        guess.setInputType(InputType.TYPE_CLASS_NUMBER);

        textViewLast.setVisibility(View.INVISIBLE);
        textViewChance.setVisibility(View.INVISIBLE);
        textViewHint.setVisibility(View.INVISIBLE);

        twoDigits = getIntent().getBooleanExtra("two",false);
        threeDigits = getIntent().getBooleanExtra("three",false);
        fourDigits = getIntent().getBooleanExtra("four",false);

        if(twoDigits){
            r = random.nextInt(90)+10;
        }
        if(threeDigits){
            r = random.nextInt(900)+100;
        }
        if(fourDigits){
            r = random.nextInt(9000)+1000;
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guessnum = guess.getText().toString();
                if(guessnum.equals(""))
                {
                    Toast.makeText(GameActivity.this, "Please enter a valid guess", Toast.LENGTH_SHORT).show();
                }
                else {
                    int userGuess = Integer.parseInt(guessnum);
                    chancesRemaining--;
                    guessCount++;
                    guessList.add(userGuess);
                    textViewLast.setText("Your last guess : "+userGuess);
                    textViewChance.setText("Your remaining chances : "+chancesRemaining);

                    Log.d("GuessingGame", "Random number to guess: " + r);
                    Log.d("GuessingGame", "Random number to guess: " + userGuess);

                    if(r == userGuess)
                    {

                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setCancelable(false);
                        builder.setTitle("Number Guessing Game");
                        builder.setMessage("Congratulations! My guess was "+r+"\n\n You guessed my number in "+guessCount+" attempts \n\n Your guesses were "+guessList+" \n\n Would you like to play again?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();

                    }
                    if (r>userGuess)
                    {
                        textViewHint.setText("Hint : Increase your guess");
                    }
                    if(r<userGuess)
                    {
                        textViewHint.setText("Hint : Decrease your guess");
                    }
                    if(chancesRemaining == 0)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setCancelable(false);
                        builder.setTitle("Game Over");
                        builder.setMessage("Sorry you ran out of chances \n\n My guess was "+r+" \n\n Your guesses were "+guessList+" \n\n Would you like to play again?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();
                    }

                    guess.setText("");

                    textViewLast.setVisibility(View.VISIBLE);
                    textViewChance.setVisibility(View.VISIBLE);
                    textViewHint.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}