package com.changed.supun.flagquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AdvancedLevel extends AppCompatActivity {

    //String varibles to store the flag name inputs from user
    String flag1;
    String flag2;
    String flag3;


    //image view varibale to initialize the image views
    ImageView flagView1;
    ImageView flagView2;
    ImageView flagView3;

    //textview  variabale to initialize the score
    TextView score;
    //textviews to show the correct answer
    TextView correctAnswer1;
    TextView correctAnswer2;
    TextView correctAnswer3;
    TextView status;

    Button buttonSubmit;

    //variabales to initialize the edittext fields
    EditText flagInput1;
    EditText flagInput2;
    EditText flagInput3;

    Random random = new Random();
    //crating a instance of the database class
    Database database = new Database();
    //adding the country item to a list
    List<CountryItem> list;

    //variables tos store the random numbers
    static int flagnum1;
    static int flagnum2;
    static int flagnum3;

    //variabale to count the no of clicks
    int exit = 0;

    //variabale to change the button label
    int setTag = 0;

    //variabale to store the score of the game
    int scoreNum1;
    int scoreNum2;
    int scoreNum3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //initializing the scores to zero
        scoreNum1 = 0;
        scoreNum2 = 0;
        scoreNum3 = 0;



        //inserting the list to an arraylist
        list = new ArrayList<>();


        //add all flags and names to the list
        for (int i = 0; i < new Database().answers.length; i++) {
            list.add(new CountryItem(new Database().answers[i], new Database().flags[i]));
        }

        //randomly generating numbers less than the size of the list
        flagnum1 = random.nextInt(list.size() - 3);
        flagnum2 = random.nextInt(list.size() - 4);
        flagnum3 = random.nextInt(list.size() - 5);

        //shuffle the list
        Collections.shuffle(list);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        //initializing the image views
        flagView1 = (ImageView) findViewById(R.id.imgview1);
        flagView2 = (ImageView) findViewById(R.id.imgview2);
        flagView3 = (ImageView) findViewById(R.id.imgview3);

        //initializing the textviews
        score = (TextView) findViewById(R.id.textViewScore);
        correctAnswer1 = (TextView) findViewById(R.id.correctAnswer1);
        correctAnswer2 = (TextView) findViewById(R.id.correctAnswer2);
        correctAnswer3 = (TextView) findViewById(R.id.correctAnswer3);
        status = (TextView) findViewById(R.id.status);


        //initializing the Edittext inputs
        flagInput1 = (EditText) findViewById(R.id.editText1);
        flagInput2 = (EditText) findViewById(R.id.editText2);
        flagInput3 = (EditText) findViewById(R.id.editText3);

        //initializing the Button
        buttonSubmit = (Button) findViewById(R.id.buttonAdvanced);

        //setting the scoreview to zero
        score.setText(Integer.toString(scoreNum1));

        //generating a new question
        newQuestion(flagnum1, flagnum2, flagnum3);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit++;
                if (exit == 4) { // when user clicks the forth time new question will be generated
                    if(flagnum1 == 255 || flagnum2 == 255 || flagnum3 == 255){
                        Random rand = new Random();
                        flagnum1 = rand.nextInt(112);
                        flagnum2 = rand.nextInt(112);
                        flagnum3 = rand.nextInt(112);
                    }
                    newQuestion(flagnum1, flagnum2, flagnum3);

                } else {
                    //if it's not the forth click this block will execute
                    if (exit < 3) {
                        //checking whether the answer is correct and making changes accordingly
                        if (list.get(flagnum1).getName().equalsIgnoreCase(flagInput1.getText().toString())) {
                            correctAnswer1.setText(R.string.word_correct);
                            correctAnswer1.setTextColor(Color.BLUE);
                            flagInput1.setTextColor(Color.GREEN);

                            //avoiding repeating the score
                            if (scoreNum1 == 0){

                                scoreNum1++;
                            }else {
                                scoreNum1 += 0;
                            }


                          //  score.setText(Integer.toString(scorenum));

                            //logging
                            String log = list.get(flagnum1).getName().toString();
                            Log.d("Test", log);


                        } else {
                            //if answer is wrong setting the text color
                            flagInput1.setTextColor(Color.RED);

                            //logging
                            String log = list.get(flagnum1).getName().toString();
                            Log.d("Advanced Level", log);

                        }
                        //checking whether the answer is correct and making changes accordingly
                        if (list.get(flagnum2).getName().equalsIgnoreCase(flagInput2.getText().toString())) {
                            correctAnswer2.setText(R.string.word_correct);
                            correctAnswer2.setTextColor(Color.BLUE);
                            flagInput2.setTextColor(Color.GREEN);

                            //avoiding repeating the score
                            if (scoreNum2 == 0) {
                                scoreNum2++;
                            }else {
                                scoreNum2 += 0;
                            }
                            //score.setText(Integer.toString(scorenum));

                            //logging
                            String log = list.get(flagnum2).getName().toString();
                            Log.d("Advanced Level", log);

                        } else {
                            //if answer is wrong setting the text color
                            flagInput2.setTextColor(Color.RED);

                            //logging
                            String log = list.get(flagnum2).getName().toString();
                            Log.d("Advanced Level", log);
                        }

                        //checking whether the answer is correct and making changes accordingly
                        if (list.get(flagnum3).getName().equalsIgnoreCase(flagInput3.getText().toString())) {
                            correctAnswer3.setText(R.string.word_correct);
                            correctAnswer3.setTextColor(Color.BLUE);
                            flagInput3.setTextColor(Color.GREEN);

                            //avoiding repeating the score
                            if (scoreNum3 == 0){

                                scoreNum3++;
                            }else {
                                scoreNum3 += 0;
                            }

                           // score.setText(Integer.toString(scorenum));

                            //logging
                            String log = list.get(flagnum3).getName().toString();
                            Log.d("Advanced Level", log);


                        } else {
                            //if answer is wrong setting the text color
                            flagInput3.setTextColor(Color.RED);

                            //logging
                            String log = list.get(flagnum3).getName().toString();
                            Log.d("Advanced Level", log);
                        }

                        //adding the total score
                        int totalScore = scoreNum1 + scoreNum2 + scoreNum3;

                        //logging
                        Log.d(Integer.toString(totalScore),"TotalScore");

                        //Setting the score the textview
                        score.setText(Integer.toString(totalScore));

                    } else {
                        //setting flag names to string varibales
                        flag1 = list.get(flagnum1).getName();
                        flag2 = list.get(flagnum2).getName();
                        flag3 = list.get(flagnum3).getName();

                        //displaying the correct answer

                        if (correctAnswer1.getText().toString().equalsIgnoreCase("correct")) {
                            correctAnswer1.setText(R.string.word_correct);
                        } else {
                            correctAnswer1.setText(flag1);
                            correctAnswer1.setTextColor(Color.BLUE);
                        }
                        if (correctAnswer2.getText().toString().equalsIgnoreCase("correct")) {
                            correctAnswer2.setText(R.string.word_correct);
                        } else {
                            correctAnswer2.setText(flag2);
                            correctAnswer2.setTextColor(Color.BLUE);
                        }
                        if (correctAnswer3.getText().toString().equalsIgnoreCase("correct")) {
                            correctAnswer3.setText(R.string.word_correct);
                        } else {
                            correctAnswer3.setText(flag3);
                            correctAnswer3.setTextColor(Color.BLUE);
                        }

                        if (correctAnswer1.getText().toString().equalsIgnoreCase("correct") && correctAnswer2.getText().toString().equalsIgnoreCase("correct") &&
                                correctAnswer3.getText().toString().equalsIgnoreCase("correct")) {

                            status.setText(R.string.word_correct);
                            status.setTextColor(Color.BLUE);

                        } else {

                            status.setText(R.string.word_wrong);
                            status.setTextColor(Color.RED);
                        }

                        //Changing the Button text
                        buttonSubmit.setText(R.string.btn_next);
                        setTag = 1;
                        flagnum1++;
                        flagnum2++;
                        flagnum3++;


                    }

                }
            }
        });


    }


    //new question method
    private void newQuestion(int number, int number2, int number3) {

        //setting the image button
        flagView1.setImageResource(list.get(number).getImage());
        //setting the image button
        flagView2.setImageResource(list.get(number2).getImage());
        //setting the image button
        flagView3.setImageResource(list.get(number3).getImage());
        //setting the counter to zero again
        exit = 0;

        //resetting the text views
        correctAnswer1.setText("");
        correctAnswer2.setText("");
        correctAnswer3.setText("");
        status.setText("");

        //resetting the input fields
        flagInput1.setTextColor(Color.DKGRAY);
        flagInput2.setTextColor(Color.DKGRAY);
        flagInput3.setTextColor(Color.DKGRAY);


        //setting the button text
        if (setTag == 1) {
            buttonSubmit.setText(R.string.btn_submit);
        }


    }
}
