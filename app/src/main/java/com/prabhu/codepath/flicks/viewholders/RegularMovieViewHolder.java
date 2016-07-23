package com.prabhu.codepath.flicks.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prabhu.codepath.flicks.R;

/**
 * Created by pbabu on 7/22/16.
 */
public class RegularMovieViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivMovieImage;
    public TextView tvMovieTitle;
    public TextView tvMovieOverview;

    public RegularMovieViewHolder(View itemView) {
        super(itemView);
        ivMovieImage = (ImageView)itemView.findViewById(R.id.ivMovieImage);
        tvMovieTitle = (TextView)itemView.findViewById(R.id.tvMovieTitle);
        tvMovieOverview = (TextView)itemView.findViewById(R.id.tvMovieOverview);
    }
}
