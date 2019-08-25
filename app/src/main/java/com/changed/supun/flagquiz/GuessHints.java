package com.changed.supun.flagquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.changed.supun.flagquiz.Adapter.GridViewAnswerAdapter;
import com.changed.supun.flagquiz.Adapter.GridViewSuggestAdapter;
import com.changed.supun.flagquiz.Common.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class GuessHints extends AppCompatActivity {


    //String araylist to store the suggesting
    public List<String> suggestSource = new ArrayList<>();

    //GridViewAnswerAdapter variable
    public GridViewAnswerAdapter answerAdapter;

    //GridViewSuggestAdapter variable
    public GridViewSuggestAdapter suggestAdapter;

    //submit button
    public Button btnSubmit;

    //two gridviews for the answer and the suggesting letters
    public GridView gridViewAnswer,gridViewSuggest;

    //image view to display the flag
    public ImageView imgViewQuestion;

    //text view to display the corect wrong status
    public TextView statusText;

    //char array to store the answer
    public char[] answer;

    //string varibale to store the correct answer
    String correct_answer;

    //instance of the random class in order to create a random number
    Random random = new Random();

    //crating a instance of the database class
    Database database = new Database();

    //adding the country item to a list
    List<CountryItem> list;

    //varibale to store the random number
    static int flagnum1;

    //varibale to change the button label
    int setTag;

    int exit = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_hints);


        //inserting the list to an arraylist
        list = new ArrayList<>();


        //add all flags and names to the list
        for (int i = 0; i < new Database().answers.length; i++) {
            list.add(new CountryItem(new Database().answers[i], new Database().flags[i]));
        }


        //shuffle the list
        Collections.shuffle(list);

        //Init View
        initView();


        setTag = 0;
    }

    private void initView() {

        //initialization of girdviews
        gridViewAnswer = (GridView)findViewById(R.id.gridViewAnswer);
        gridViewSuggest = (GridView)findViewById(R.id.gridViewSuggest);

        //initilization of imageviews
        imgViewQuestion = (ImageView)findViewById(R.id.imgLogo);

        //Add SetupList Here
        newQuestion();

        //initilization of submit button
        btnSubmit = (Button)findViewById(R.id.btnSubmit);

        //initilization of textview
        statusText = (TextView) findViewById(R.id.statusText);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit++;
                if (exit == 2) {

                    newQuestion();

                }else {
                    String result="";
                    for(int i = 0; i< Common.user_submit_answer.length; i++)
                        result+=String.valueOf(Common.user_submit_answer[i]);

                    if (result.equals(correct_answer)) {

                        statusText.setText(R.string.word_correct);
                        statusText.setTextColor(Color.GREEN);
                        // Toast.makeText(getApplicationContext(),"Finish ! This is "+result,Toast.LENGTH_SHORT).show();

                        //setting the tag to one
                        btnSubmit.setText(R.string.btn_next);
                        setTag = 1;

                        //Reset
                        Common.count = 0;
                        Common.user_submit_answer = new char[correct_answer.length()];

                        //Set Adapters
                        GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(), getApplicationContext());
                        gridViewAnswer.setAdapter(answerAdapter);
                        answerAdapter.notifyDataSetChanged();

                        GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter(suggestSource, getApplicationContext(), GuessHints.this);
                        gridViewSuggest.setAdapter(suggestAdapter);
                        suggestAdapter.notifyDataSetChanged();


                    } else {
                        statusText.setText(R.string.word_wrong);
                        statusText.setTextColor(Color.RED);
                        //  Toast.makeText(GuessHints.this, "Incorrect!!!", Toast.LENGTH_SHORT).show();


                        //setting the tag to one
                        btnSubmit.setText(R.string.btn_next);
                        setTag = 1;

                        //Reset
                        Common.count = 0;
                        Common.user_submit_answer = new char[correct_answer.length()];

                        //Set Adapters
                        GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(), getApplicationContext());
                        gridViewAnswer.setAdapter(answerAdapter);
                        answerAdapter.notifyDataSetChanged();

                        GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter(suggestSource, getApplicationContext(), GuessHints.this);
                        gridViewSuggest.setAdapter(suggestAdapter);
                        suggestAdapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }

    //generating a new question
    private void newQuestion() {



        //setting the button text
        if (setTag == 1) {
            btnSubmit.setText(R.string.btn_submit);

            //resetting the textview status
            statusText.setText(" ");
        }

        // resettng the exit counter
        exit = 0;

        // generating a random number
        flagnum1 = random.nextInt(database.answers.length);


        // generating a random flag
        imgViewQuestion.setImageResource(list.get(flagnum1).getImage());

        //getting the flag name corresponding to the flag
        correct_answer = list.get(flagnum1).getName();
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/")+1);

        //logging
        String log = list.get(flagnum1).getName();
        Log.d("Guess Hints",log);

        answer = correct_answer.toCharArray();

        Common.user_submit_answer = new char[answer.length];

        //Add Answer character to List
        suggestSource.clear();
        for(char item:answer)
        {
            //Add logo name to list
            suggestSource.add(String.valueOf(item));
        }

        //Random add some character to list
        for(int i = answer.length;i<answer.length*2;i++)
            suggestSource.add(Common.alphabet_character[random.nextInt(Common.alphabet_character.length)]);

        //Sort random
        Collections.shuffle(suggestSource);

        //Set for GridView
        answerAdapter = new GridViewAnswerAdapter(setupNullList(),this);
        suggestAdapter = new GridViewSuggestAdapter(suggestSource,this,this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gridViewSuggest.setAdapter(suggestAdapter);
        gridViewAnswer.setAdapter(answerAdapter);


    }

    //clearing up the answer
    private char[] setupNullList() {
        char result[] = new char[answer.length];
        for(int i=0;i<answer.length;i++)
            result[i]=' ';
        return result;
    }
}