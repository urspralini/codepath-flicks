package com.prabhu.codepath.flicks.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.prabhu.codepath.flicks.R;

/**
 * Created by pbabu on 7/22/16.
 */
public class PopularMovieViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivPopularMovieImage;

    public PopularMovieViewHolder(View itemView) {
        super(itemView);
        ivPopularMovieImage = (ImageView)itemView.findViewById(R.id.ivPopularMovieImage);
    }

}
