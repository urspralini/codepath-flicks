package com.prabhu.codepath.flicks.rest;

import com.prabhu.codepath.flicks.models.MovieDBApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pbabu on 7/20/16.
 */
public interface MovieDBService {

    @GET("3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Call<MovieDBApiResponse> getNowPlayingMovies();
}
