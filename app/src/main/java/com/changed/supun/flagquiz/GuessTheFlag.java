package com.changed.supun.flagquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GuessTheFlag extends AppCompatActivity {

    //varibales to declare the image buttons
    private ImageButton imgFlag1;
    private ImageButton imgFlag2;
    private ImageButton imgFlag3;

    //varibales to declare the textviews
    private TextView countryName;
    private TextView stausText;

    //varibales to declare the buttons
    private Button buttonNext;


    Random random = new Random();
    //instance of the database class
    Database database = new Database();

    //list of country item
    List<CountryItem> list;

    // variables to store the random numbers
    static int flagnum1;
    static int flagnum2;
    static int flagnum3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //randomly generating numbers less than the size of the list


        //inserting the list to an arraylist
        list = new ArrayList<>();


        //add all flags and names to the list
        for (int i = 0; i < new Database().answers.length; i++) {
            list.add(new CountryItem(new Database().answers[i], new Database().flags[i]));
        }
        flagnum1 = random.nextInt(list.size() - 10);
        flagnum2 = random.nextInt(list.size() - 10);
        flagnum3 = random.nextInt(list.size() - 10);

        //shuffle the countries
        Collections.shuffle(list);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_flag);


        //initilizing the imageButtons
        imgFlag1 = (ImageButton) findViewById(R.id.imgButton1);
        imgFlag2 = (ImageButton) findViewById(R.id.imgButton2);
        imgFlag3 = (ImageButton) findViewById(R.id.imgButton3);

        //initializing the countryName TextView
        countryName = (TextView) findViewById(R.id.countryname);

        //initializing the countryName TextView
        stausText = (TextView) findViewById(R.id.status_guessTheFlag);

        //initializing the Button
        buttonNext = (Button) findViewById(R.id.nxtbutton);


        //generating a new question
        newQuestion(flagnum1, flagnum2, flagnum3);


        //onClickListener method for the imagebutton1
        imgFlag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(flagnum1).getName().equalsIgnoreCase(countryName.getText().toString())) {
                    String log = list.get(flagnum1).getName();
                    Log.d("Guess The Flag", log);
                    stausText.setText(R.string.word_correct);
                    stausText.setTextColor(Color.GREEN);

                } else {
                    stausText.setText(R.string.word_wrong);
                    stausText.setTextColor(Color.RED);
                }


            }
        });

        //onClickListener method for the imagebutton2
        imgFlag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(flagnum2).getName().equalsIgnoreCase(countryName.getText().toString())) {
                    String log = list.get(flagnum2).getName();
                    Log.d("Guess The Flag", log);
                    stausText.setText(R.string.word_correct);
                    stausText.setTextColor(Color.GREEN);

                } else {
                    stausText.setText(R.string.word_wrong);
                    stausText.setTextColor(Color.RED);
                }


            }
        });

        //onClickListener method for the imagebutton3
        imgFlag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(flagnum3).getName().equalsIgnoreCase(countryName.getText().toString())) {
                    String log = list.get(flagnum3).getName();
                    Log.d("Guess The Flag", log);
                    stausText.setText(R.string.word_correct);
                    stausText.setTextColor(Color.GREEN);

                } else {
                    stausText.setText(R.string.word_wrong);
                    stausText.setTextColor(Color.RED);
                }


            }
        });

        //OnclickListener method for the NextButton
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //incrementing the randomly generated numbers
                flagnum1++;
                flagnum2++;
                flagnum3++;

                if(flagnum1 == 255 || flagnum2 == 255 || flagnum3 == 255){
                    Random rand = new Random();
                    flagnum1 = rand.nextInt(110);
                    flagnum2 = rand.nextInt(110);
                    flagnum3 = rand.nextInt(110);
                }
                //calling the newQuestion method
                newQuestion(flagnum1, flagnum2, flagnum3);

            }
        });

    }

    private void newQuestion(int number, int number2, int number3) {

        //setting the image button
        imgFlag1.setImageResource(list.get(number).getImage());
        //setting the image button
        imgFlag2.setImageResource(list.get(number2).getImage());
        //setting the image button
        imgFlag3.setImageResource(list.get(number3).getImage());

        //getting the country names
        String country1 = list.get(number).getName();
        String country2 = list.get(number2).getName();
        String country3 = list.get(number3).getName();

        //inserting the country names to a arraylist
        ArrayList<String> countries = new ArrayList<String>();
        countries.add(country1);
        countries.add(country2);
        countries.add(country3);

        //shuffling the arraylist to get a random name
        Collections.shuffle(countries);

        //setting the random country name to the textview
        countryName.setText(countries.get(0));

        //resetting the status textview
        stausText.setText(" ");

    }
}
