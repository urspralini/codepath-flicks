package com.prabhu.codepath.flicks.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.prabhu.codepath.flicks.R;
import com.prabhu.codepath.flicks.adapters.MoviesAdapter;
import com.prabhu.codepath.flicks.models.DetailMovie;
import com.prabhu.codepath.flicks.models.Movie;
import com.prabhu.codepath.flicks.rest.MovieDBClient;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE_ID_KEY = "movieId";
    private Movie mMovie;
    private ImageView mIvMovieImage;
    private TextView mTvMovieTitle;
    private TextView mTvMovieReleaseDate;
    private TextView mTvMovieOverview;
    private RatingBar mRBMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        final int movieId = getIntent().getIntExtra(MOVIE_ID_KEY, -1);
        mIvMovieImage = (ImageView)findViewById(R.id.ivDetailMovieImage);
        mTvMovieTitle = (TextView)findViewById(R.id.tvDetailMovieTitle);
        mTvMovieReleaseDate = (TextView)findViewById(R.id.tvMovieReleaseDate);
        mTvMovieOverview = (TextView)findViewById(R.id.tvDetailMovieOverview);
        mRBMovie = (RatingBar)findViewById(R.id.rbDetailMovie);
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
        final String movieImageUri = String.format("https://image.tmdb.org/t/p/w%d%s",
                MoviesAdapter.BACKDROP_IMAGE_WIDTH,
                detailMovie.getBackdropPath());
        Picasso.with(this).load(movieImageUri)
                .transform(new RoundedCornersTransformation(10,10))
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.placeholder_error_image)
                .into(mIvMovieImage);
    }
}
