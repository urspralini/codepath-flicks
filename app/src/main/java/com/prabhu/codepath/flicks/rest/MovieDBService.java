package com.prabhu.codepath.flicks.rest;

import com.prabhu.codepath.flicks.models.DetailMovie;
import com.prabhu.codepath.flicks.models.MovieDBApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pbabu on 7/20/16.
 */
public interface MovieDBService {

    @GET("3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Call<MovieDBApiResponse> getNowPlayingMovies(@Query("page") int page);

    @GET("3/movie/upcoming?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Call<MovieDBApiResponse> getUpcomingMovies(@Query("page") int page);

    @GET("3/movie/top_rated?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Call<MovieDBApiResponse> getTopRatedMovies(@Query("page") int page);

    @GET("3/movie/popular?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Call<MovieDBApiResponse> getPopularMovies(@Query("page") int page);

    @GET("3/movie/{id}?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Call<DetailMovie> getMovie(@Path("id") int movieId);
}
