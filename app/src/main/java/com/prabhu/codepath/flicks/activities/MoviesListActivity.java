package com.prabhu.codepath.flicks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.prabhu.codepath.flicks.R;
import com.prabhu.codepath.flicks.adapters.MoviesAdapter;
import com.prabhu.codepath.flicks.components.DividerItemDecoration;
import com.prabhu.codepath.flicks.listeners.EndlessRecyclerViewScrollListener;
import com.prabhu.codepath.flicks.models.Movie;
import com.prabhu.codepath.flicks.models.MovieDBApiResponse;
import com.prabhu.codepath.flicks.rest.MovieDBClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesListActivity extends AppCompatActivity implements MoviesAdapter.OnItemClickListener {
    private List<Movie> movies;
    private MoviesAdapter moviesAdapter;
    private SwipeRefreshLayout swipeContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        movies = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(this, movies);
        RecyclerView rvMovies = (RecyclerView)findViewById(R.id.rvMovies);
        swipeContainer = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchMovies(1);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        //call the movies database api for the first time
        fetchMovies(1);
        rvMovies.setAdapter(moviesAdapter);
        moviesAdapter.setOnItemClickListener(this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvMovies.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        rvMovies.addItemDecoration(itemDecoration);
        rvMovies.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                fetchMovies(page);
            }
        });
    }


    public void fetchMovies(int page){
        Call<MovieDBApiResponse> nowPlayingMoviesCall = MovieDBClient.getInstance()
                .getMovieDBService()
                .getNowPlayingMovies(page);
        nowPlayingMoviesCall.enqueue(new Callback<MovieDBApiResponse>() {
            @Override
            public void onResponse(Call<MovieDBApiResponse> call, Response<MovieDBApiResponse> response) {
                //moviesAdapter.clear();
                moviesAdapter.addAll(response.body().getResults());
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<MovieDBApiResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(Movie movie) {
        final Integer movieId = movie.getId();
        Intent detailIntent = new Intent(this, MovieDetailActivity.class);
        detailIntent.putExtra(MovieDetailActivity.MOVIE_ID_KEY, movieId);
        startActivity(detailIntent);
    }
}
