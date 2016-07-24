package com.prabhu.codepath.flicks.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.prabhu.codepath.flicks.R;
import com.prabhu.codepath.flicks.models.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE_ID_KEY = "movieId";
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        final String movieId = getIntent().getStringExtra(MOVIE_ID_KEY);
    }
}
