package com.jxs5476.showtracker;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddShow extends AppCompatActivity {
    Show temp;
    ShowDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_show);
        Bundle extras = getIntent().getExtras();
        String value;
        value = String.valueOf(extras.getInt("id"));
        db = ShowDatabase.getInstance(getApplicationContext());
        temp = db.showDao().getShowbyId(value);
        if(temp != null){
            TextView mNameTextView = findViewById(R.id.show_name);
            mNameTextView.setText(temp.getName());

            mNameTextView = findViewById(R.id.show_rating);
            mNameTextView.setText(String.valueOf(temp.getRating()));

            mNameTextView = findViewById(R.id.show_review);
            mNameTextView.setText(String.valueOf(temp.getReview()));

            mNameTextView = findViewById(R.id.show_progress);
            mNameTextView.setText(String.valueOf(temp.getProgress()));
        }
        else{
            View name = findViewById(R.id.name_group);
            name.setVisibility(View.VISIBLE);
            TextView mNameTextView = findViewById(R.id.show_name);
            mNameTextView.setText("Show Name");

            mNameTextView = findViewById(R.id.show_rating);
            mNameTextView.setText("Rating");

            mNameTextView = findViewById(R.id.show_review);
            mNameTextView.setText("Rating out of 10");

            mNameTextView = findViewById(R.id.show_progress);
            mNameTextView.setText("Episode number");
        }

    }

    public void onClickCurrent(View view) {
        View rating = findViewById(R.id.rating_group);
        View review = findViewById(R.id.review_group);
        View progress = findViewById(R.id.progress_group);
        View name = findViewById(R.id.name_group);
        rating.setVisibility(View.VISIBLE);
        review.setVisibility(View.VISIBLE);
        progress.setVisibility(View.VISIBLE);
    }

    public void onClickPrev(View view) {
        View rating = findViewById(R.id.rating_group);
        View review = findViewById(R.id.review_group);
        View progress = findViewById(R.id.progress_group);
        View name = findViewById(R.id.name_group);
        rating.setVisibility(View.VISIBLE);
        review.setVisibility(View.VISIBLE);
        progress.setVisibility(View.VISIBLE);
    }

    public void onClickWatch(View view) {
        View rating = findViewById(R.id.rating_group);
        View review = findViewById(R.id.review_group);
        View progress = findViewById(R.id.progress_group);
        View name = findViewById(R.id.name_group);
        rating.setVisibility(View.GONE);
        review.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
    }

    public void onClickSub(View view) {
        View please = findViewById(R.id.please);
        if(!MainActivity.DataHolder.getData().equals("")){
            please.setVisibility(View.GONE);
        EditText name = findViewById(R.id.name_in);
        EditText rating = findViewById(R.id.rating_in);
        EditText review = findViewById(R.id.review_in);
        EditText progress = findViewById(R.id.progress_in);
        RadioGroup rg = (RadioGroup) findViewById(R.id.groupDeterminer);
        final String value =
                ((RadioButton)findViewById(rg.getCheckedRadioButtonId()))
                        .getText().toString();

        Show newShow = new Show();
        if(name.getText().toString().equals("")){
            newShow.setId(temp.getId());
            newShow.setName(temp.getName());
            newShow.setRating(Integer.parseInt(rating.getText().toString()));
            newShow.setReview(review.getText().toString());
            newShow.setProgress(progress.getText().toString());
            if(value.equals("Currently Watching")){
                newShow.setFinished(false);
                newShow.setWatching(true);
            }
            if(value.equals("Want to Watch")){
                newShow.setFinished(false);
                newShow.setWatching(false);
            }
            if(value.equals("Finished")){
                newShow.setFinished(true);
                newShow.setWatching(false);
            }

            db.showDao().updateShow(newShow);
        }
        else{
            newShow.setName(name.getText().toString());

            if(value.equals("Currently Watching")){
                newShow.setFinished(false);
                newShow.setWatching(true);
                newShow.setRating(Integer.parseInt(rating.getText().toString()));
                newShow.setReview(review.getText().toString());
                newShow.setProgress(progress.getText().toString());
            }
            if(value.equals("Want to Watch")){
                newShow.setFinished(false);
                newShow.setWatching(false);
            }
            if(value.equals("Finished")){
                newShow.setFinished(true);
                newShow.setWatching(false);
                newShow.setRating(Integer.parseInt(rating.getText().toString()));
                newShow.setReview(review.getText().toString());
                newShow.setProgress(progress.getText().toString());
            }
            db.showDao().insertShow(newShow);
        }
        this.finish();
    }
        else{

            please.setVisibility(View.VISIBLE);
        }
}
}