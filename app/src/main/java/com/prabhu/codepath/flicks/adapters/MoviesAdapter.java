package com.prabhu.codepath.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prabhu.codepath.flicks.R;
import com.prabhu.codepath.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by pbabu on 7/20/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private final List<Movie> mMovieList;
    private final Context mContext;

    public MoviesAdapter(Context mContext, List<Movie> mMovieList) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivMovieImage;
        public TextView tvMovieTitle;
        public TextView tvMovieOverview;

        public ViewHolder(View itemView) {
            super(itemView);
            ivMovieImage = (ImageView)itemView.findViewById(R.id.ivMovieImage);
            tvMovieTitle = (TextView)itemView.findViewById(R.id.tvMovieTitle);
            tvMovieOverview = (TextView)itemView.findViewById(R.id.tvMovieOverview);
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemMovieView = layoutInflater.inflate(R.layout.list_item_movie, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemMovieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int imageWidth;
        final int orientation = mContext.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            imageWidth = 780;
        }else {
            imageWidth = 342;
        }
        Movie movie = mMovieList.get(position);
        holder.tvMovieTitle.setText(movie.getTitle());
        holder.tvMovieOverview.setText(movie.getOverview());
        final String movieImageUri = String.format("https://image.tmdb.org/t/p/w%d%s",
                imageWidth,
                movie.getPosterPath());
        Picasso.with(mContext).load(movieImageUri).into(holder.ivMovieImage);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

}
