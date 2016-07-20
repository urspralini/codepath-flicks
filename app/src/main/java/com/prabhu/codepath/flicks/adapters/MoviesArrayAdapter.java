package com.prabhu.codepath.flicks.adapters;

import android.content.Context;
import android.graphics.Movie;
import android.widget.ArrayAdapter;

/**
 * Created by pbabu on 7/20/16.
 */
public class MoviesArrayAdapter extends ArrayAdapter<Movie> {

    public MoviesArrayAdapter(Context context, int resource) {
        super(context, resource);
    }
}
