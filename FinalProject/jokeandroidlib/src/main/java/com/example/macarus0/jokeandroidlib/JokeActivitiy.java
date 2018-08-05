package com.example.macarus0.jokeandroidlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class JokeActivitiy extends AppCompatActivity {

    public static final String JOKE_ID = "joke_id";

    TextView mJokeTextView;
    TextView mReactionTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        mJokeTextView = findViewById(R.id.joke_text);
        mReactionTextView = findViewById(R.id.joke_reaction);

        Intent intent = getIntent();
        String joke = intent.getStringExtra(JOKE_ID);
        if(joke != null) {
            mJokeTextView.setText(joke);
            mReactionTextView.setText("rofl!");
        } else {
            mJokeTextView.setText("No joke provided");
            mReactionTextView.setText(":<");
        }


    }
}
