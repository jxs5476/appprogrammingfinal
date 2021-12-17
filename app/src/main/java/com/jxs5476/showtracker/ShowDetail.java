package com.jxs5476.showtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ShowDetail extends AppCompatActivity {
    private final static String TAG = "MainActivity";
    Show temp;
    ShowDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        Bundle extras = getIntent().getExtras();
        String value;
        value = String.valueOf(extras.getInt("id"));
        db= ShowDatabase.getInstance(getApplicationContext());
        temp = db.showDao().getShowbyId(value);

        TextView mNameTextView = findViewById(R.id.show_name);
        mNameTextView.setText(String.valueOf(temp.getName()));

        if(temp.isFinished() || temp.isWatching()){
            mNameTextView = findViewById(R.id.show_progress);
            mNameTextView.setText(temp.getProgress());
            mNameTextView = findViewById(R.id.show_rating);
            mNameTextView.setText(String.valueOf(temp.getRating()));
            mNameTextView = findViewById(R.id.show_review);
            mNameTextView.setText(temp.getReview());
        }

    }
    public void addButtonClick(View view){
        Intent intent = new Intent(getApplicationContext(), AddShow.class);
        intent.putExtra("id", (int)temp.getId());
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        db= ShowDatabase.getInstance(getApplicationContext());
    }

    public void onShareClick(View view){
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String msg = "I gave " + temp.getName() + " A " + temp.getRating() + "/10 rating";
        shareIntent.putExtra(Intent.EXTRA_TEXT, msg);
        startActivity(Intent.createChooser(shareIntent, "Share your thoughts"));
    }

    public void onExportClick(View view){
        Intent exportIntent = new Intent();
        exportIntent.setAction(Intent.ACTION_CREATE_DOCUMENT);
        exportIntent.setType("text/plain");
        exportIntent.putExtra(Intent.EXTRA_TITLE, temp.getName());
        String msg = "I gave " + temp.getName() + " A " + temp.getRating() + "/10 rating";
        exportIntent.putExtra(Intent.EXTRA_TEXT, msg);
        startActivity(Intent.createChooser(exportIntent,temp.getName()));
    }

}