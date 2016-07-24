package com.prabhu.codepath.flicks.activities;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.prabhu.codepath.flicks.R;
import com.prabhu.codepath.flicks.models.DetailMovie;
import com.prabhu.codepath.flicks.models.MovieTrailerResponse;
import com.prabhu.codepath.flicks.models.Youtube;
import com.prabhu.codepath.flicks.rest.MovieDBClient;
import com.prabhu.codepath.flicks.rest.MovieDBService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends YouTubeBaseActivity {
    public static final MovieDBService MOVIE_DB_SERVICE = MovieDBClient.getInstance()
            .getMovieDBService();
    public static final String MOVIE_ID_KEY = "movieId";
    private TextView mTvMovieTitle;
    private TextView mTvMovieReleaseDate;
    private TextView mTvMovieOverview;
    private RatingBar mRBMovie;
    private YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        final int movieId = getIntent().getIntExtra(MOVIE_ID_KEY, -1);
        mTvMovieTitle = (TextView)findViewById(R.id.tvDetailMovieTitle);
        mTvMovieReleaseDate = (TextView)findViewById(R.id.tvMovieReleaseDate);
        mTvMovieOverview = (TextView)findViewById(R.id.tvDetailMovieOverview);
        mRBMovie = (RatingBar)findViewById(R.id.rbDetailMovie);
        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.playerDetailMovie);
        fetchMovie(movieId);
    }


    public void fetchMovie(int movieId) {
        Call<DetailMovie> detailMovieCall = MovieDBClient.getInstance()
                .getMovieDBService()
                .getMovie(movieId);
        detailMovieCall.enqueue(new Callback<DetailMovie>() {
            @Override
            public void onResponse(Call<DetailMovie> call, Response<DetailMovie> response) {
                final DetailMovie movieDetail = response.body();
                populateUIFromModel(movieDetail);
            }

            @Override
            public void onFailure(Call<DetailMovie> call, Throwable t) {

            }
        });

    }

    private void populateUIFromModel(DetailMovie detailMovie) {
        mTvMovieTitle.setText(detailMovie.getTitle());
        mTvMovieReleaseDate.setText("Release Date:" + detailMovie.getReleaseDate());
        mTvMovieOverview.setText(detailMovie.getOverview());
        if(detailMovie.getVoteAverage() >= 5.0){
            mRBMovie.setRating(5.0f);
        }else {
            mRBMovie.setRating(detailMovie.getVoteAverage().floatValue());
        }
        final Call<MovieTrailerResponse> movieTrailersCall = MOVIE_DB_SERVICE.getMovieTrailers(detailMovie.getId());
        movieTrailersCall.enqueue(new Callback<MovieTrailerResponse>() {
            @Override
            public void onResponse(Call<MovieTrailerResponse> call, Response<MovieTrailerResponse> response) {
                MovieTrailerResponse movieTrailerResponse = response.body();
                final ArrayList<String> youtubeMovieIds = new ArrayList<>();
                for(Youtube youtube : movieTrailerResponse.getYoutube()) {
                    youtubeMovieIds.add(youtube.getSource());
                }
                youTubePlayerView.initialize(VideoViewerActivity.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.cueVideos(youtubeMovieIds);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
            }

            @Override
            public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {

            }
        });
    }
}
