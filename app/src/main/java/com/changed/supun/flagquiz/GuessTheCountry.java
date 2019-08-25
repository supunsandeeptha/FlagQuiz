package com.changed.supun.flagquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GuessTheCountry extends AppCompatActivity {

    private Button guessthecountry_button; //declaring  the button variable
    private ImageView iv_flag;  //declaring the imageview variable
    private TextView txtview_status; //decraling the textview variable
    private TextView txtview_countryname;  //decraling the textview variable

    Database database = new Database();
    Random rnd = new Random(); // new random variable t decleration
    int turn = 0;
    List<CountryItem> list;

    //variabale to count the no of clicks
    int exit = 0;
    //variabale to change the button label
    int setTag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //inserting the list to an arraylist
        list = new ArrayList<>();


        //add all flags and names to the list
        for (int i = 0; i < new Database().answers.length; i++) {
            list.add(new CountryItem(new Database().answers[i], new Database().flags[i]));
        }

        turn = rnd.nextInt(list.size() - 2);
        //shuffle the countries
        Collections.shuffle(list);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_country);

        //initializing the imageview
        iv_flag = (ImageView) findViewById(R.id.imgview_1);

        //initializing the button
        guessthecountry_button = (Button) findViewById(R.id.guessthecountry_button);


        //initilizing the textview
        txtview_status = (TextView) findViewById(R.id.txtview_status);

        //initializng the countryname display tetview
        txtview_countryname = (TextView) findViewById(R.id.textview_countryname);

        //initilizing the spinner
        final Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.countrynames, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        newQuestion(turn);

        guessthecountry_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //incrementing the counter
                exit++;
                if (exit == 2) { //if user clicks twice it will provide a new question
                    if (turn == 255){
                        Random rand = new Random();
                        turn = rand.nextInt(250);
                    }
                    newQuestion(turn);

                } else { // if not this block will execute

                    //check if the answer is correct
                    if (spinner.getSelectedItem().toString().equalsIgnoreCase(list.get(turn).getName())) {
                        String log = list.get(turn).getName();

                        //logging

                        Log.d("Guess the Country", log);

                        //setting te text view and color
                        txtview_status.setText(R.string.word_correct);
                        txtview_status.setTextColor(Color.GREEN);
                        //incrementing the turn
                        turn++;
                        //changing the button label
                        guessthecountry_button.setText(R.string.btn_next);
                        setTag = 1;


                    } else {
                        txtview_status.setText(R.string.word_wrong);
                        String log = list.get(turn).getName();
                        Log.d("Guess the Country", log);
                        txtview_status.setTextColor(Color.RED);
                        txtview_countryname.setTextColor(Color.BLUE);
                        txtview_countryname.setText(list.get(turn).getName());
                        turn++;
                        guessthecountry_button.setText(R.string.btn_next);
                        setTag = 1;


                    }

                }
            }
        });
    }


    private void newQuestion(int number) {


        //setting the image to the image view
        iv_flag.setImageResource(list.get(number).getImage());


        //setting the button text
        if (setTag == 1) {
            guessthecountry_button.setText(R.string.btn_submit);
        }

        //resetting the textviews
        txtview_countryname.setText("");
        txtview_status.setText("");

        //resetting the counter
        exit = 0;

    }
}
