package com.changed.supun.flagquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button guessTheCountry; //variable for the button guess the activity
    private Button guessHints; //variable for the button guess hints  activity
    private Button guessTheFlag; //variable for the button guess the flag activity
    private Button advancedLevel; //variable for the advancedLevel activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing the button guessTheCountry
        guessTheCountry = (Button) findViewById(R.id.button_guesscountry);
        guessTheCountry.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                openGuessTheCountry();

            }




        });

        //initializing the guess-hints button
        guessHints = (Button)findViewById(R.id.button_guesshints);
        guessHints.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                openGuessHints();
            }


        });

        //initializing the guess the flag button
        guessTheFlag = (Button)findViewById(R.id.button_guesstheflag);
        guessTheFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openGuessTheFlag();
            }
        });


        //initializing the advanced level button

        advancedLevel = (Button)findViewById(R.id.button_advancedlevel);
        advancedLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedLevel();
            }
        });

    }


    private void openGuessTheCountry() {

        // new activity will be opened
        Intent intent = new Intent(this,GuessTheCountry.class);
        startActivity(intent);

    }

    private void openGuessHints() {

        // new activity will be opened
        Intent intent = new Intent(this,GuessHints.class);
        startActivity(intent);
    }

    private void openGuessTheFlag(){

        // new activity will be opened
        Intent intent = new Intent(this,GuessTheFlag.class);
        startActivity(intent);
    }

    private void openAdvancedLevel(){

        // new activity will be opened
        Intent intent = new Intent(this,AdvancedLevel.class);
        startActivity(intent);
    }
}
