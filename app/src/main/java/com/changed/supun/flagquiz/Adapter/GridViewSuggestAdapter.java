package com.changed.supun.flagquiz.Adapter;



import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;


import com.changed.supun.flagquiz.Common.Common;
import com.changed.supun.flagquiz.GuessHints;


import java.util.List;

public class GridViewSuggestAdapter extends BaseAdapter {

    private List<String> suggestSource;
    private Context context;
    private GuessHints guessHints;

    public GridViewSuggestAdapter(List<String> suggestSource, Context context, GuessHints guessHints) {
        this.suggestSource = suggestSource;
        this.context = context;
        this.guessHints = guessHints;
    }


    @Override
    public int getCount() {
        return suggestSource.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null) {

            if (suggestSource.get(position).equals("null")) {

                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85, 85));
                button.setPadding(8, 8, 8, 8);
                button.setBackgroundColor(Color.DKGRAY);
            } else {

                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85, 85));
                button.setPadding(8, 8, 8, 8);
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.YELLOW);
                button.setText(suggestSource.get(position));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //if user selects the correct letter
                        if (String.valueOf(guessHints.answer).contains(suggestSource.get(position))){

                            char comapare = suggestSource.get(position).charAt(0);

                            for (int i = 0; i < guessHints.answer.length; i++){

                                if (comapare == guessHints.answer[i]){

                                    Common.user_submit_answer [i]= comapare;
                                }



                            }

                            //update UI
                            GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(Common.user_submit_answer,context);
                            guessHints.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            //remove from suggest source
                            guessHints.suggestSource.set(position,"null");
                            guessHints.suggestAdapter = new GridViewSuggestAdapter(guessHints.suggestSource,context,guessHints);
                            guessHints.gridViewSuggest.setAdapter(guessHints.suggestAdapter);
                            guessHints.suggestAdapter.notifyDataSetChanged();

                        }else {  // if user selects wrong answer

                            guessHints.suggestSource.set(position,"null");
                            guessHints.suggestAdapter = new GridViewSuggestAdapter(guessHints.suggestSource,context,guessHints);
                            guessHints.gridViewSuggest.setAdapter(guessHints.suggestAdapter);
                            guessHints.suggestAdapter.notifyDataSetChanged();

                        }
                    }
                });
            }
        } else
            button = (Button) convertView;
        return button;
    }
}
