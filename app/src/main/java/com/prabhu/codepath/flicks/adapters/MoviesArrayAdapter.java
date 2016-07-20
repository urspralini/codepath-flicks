package com.prabhu.codepath.flicks.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.prabhu.codepath.flicks.models.Movie;

import java.util.List;

/**
 * Created by pbabu on 7/20/16.
 */
public class MoviesArrayAdapter extends ArrayAdapter<Movie> {

    public MoviesArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }
}
