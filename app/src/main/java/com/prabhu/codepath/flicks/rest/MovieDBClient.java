package com.prabhu.codepath.flicks.rest;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by pbabu on 7/20/16.
 */
public class MovieDBClient {
    private static final String BASE_URL = "https://api.themoviedb.org/";
    private final MovieDBService movieDBService;
    private static final MovieDBClient INSTANCE = new MovieDBClient();
    private MovieDBClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        movieDBService = retrofit.create(MovieDBService.class);
    }

    public static MovieDBClient getInstance() {
        return INSTANCE;
    }

    public MovieDBService getMovieDBService() {
        return movieDBService;
    }
}
