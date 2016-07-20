package com.prabhu.codepath.flicks.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.prabhu.codepath.flicks.R;
import com.prabhu.codepath.flicks.models.Movie;
import com.prabhu.codepath.flicks.models.MovieDBApiResponse;
import com.prabhu.codepath.flicks.rest.MovieDBClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesListActivity extends AppCompatActivity {

    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        Call<MovieDBApiResponse> nowPlayingMoviesCall = MovieDBClient.getInstance()
                .getMovieDBService()
                .getNowPlayingMovies();
        nowPlayingMoviesCall.enqueue(new Callback<MovieDBApiResponse>() {
            @Override
            public void onResponse(Call<MovieDBApiResponse> call, Response<MovieDBApiResponse> response) {
                movies = response.body().getResults();
            }

            @Override
            public void onFailure(Call<MovieDBApiResponse> call, Throwable t) {

            }
        });
    }
}
