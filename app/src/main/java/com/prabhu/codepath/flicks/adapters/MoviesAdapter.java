package com.prabhu.codepath.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prabhu.codepath.flicks.R;
import com.prabhu.codepath.flicks.models.Movie;
import com.prabhu.codepath.flicks.viewholders.PopularMovieViewHolder;
import com.prabhu.codepath.flicks.viewholders.RegularMovieViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by pbabu on 7/20/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int BACKDROP_IMAGE_WIDTH = 1280;
    public static final int POTRAIT_IMAGE_WIDTH = 342;
    private final List<Movie> mMovieList;
    private final Context mContext;
    private OnItemClickListener listener;

    public MoviesAdapter(Context mContext, List<Movie> mMovieList) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
    }

    public enum ViewHolderType {
        POPULAR,
        REGULAR
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        Movie movie = mMovieList.get(position);
        if(movie.isPopular()) {
            return ViewHolderType.POPULAR.ordinal();
        }else {
            //regular movie
            return ViewHolderType.REGULAR.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemMovieView;
        final RecyclerView.ViewHolder viewHolder;
        if(viewType == ViewHolderType.POPULAR.ordinal()) {
            itemMovieView = layoutInflater.inflate(R.layout.list_item_popular_movie, parent, false);
            viewHolder = new PopularMovieViewHolder(itemMovieView);
        }else {
            itemMovieView = layoutInflater.inflate(R.layout.list_item_movie, parent, false);
            viewHolder = new RegularMovieViewHolder(itemMovieView);
        }
        itemMovieView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    final int position = viewHolder.getLayoutPosition();
                    final Movie movie = mMovieList.get(position);
                    listener.onItemClick(movie);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int imageWidth;
        String imagePathSuffix;
        Movie movie = mMovieList.get(position);
        final int orientation = mContext.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE ||
                (holder.getItemViewType() == ViewHolderType.POPULAR.ordinal())){
            imageWidth = BACKDROP_IMAGE_WIDTH;
            imagePathSuffix = movie.getBackdropPath();
        }else {
            imageWidth = POTRAIT_IMAGE_WIDTH;
            imagePathSuffix = movie.getPosterPath();
        }
        final String movieImageUri = String.format("https://image.tmdb.org/t/p/w%d%s",
                imageWidth,
                imagePathSuffix);
        if(holder.getItemViewType() == ViewHolderType.POPULAR.ordinal()) {
            PopularMovieViewHolder popularMovieViewHolder = (PopularMovieViewHolder)holder;
            Picasso.with(mContext).load(movieImageUri)
                    .transform(new RoundedCornersTransformation(10,10))
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.placeholder_error_image)
                    .into(popularMovieViewHolder.ivPopularMovieImage);
        }else {
            RegularMovieViewHolder regularMovieViewHolder = (RegularMovieViewHolder)holder;
            regularMovieViewHolder.tvMovieTitle.setText(movie.getTitle());
            regularMovieViewHolder.tvMovieOverview.setText(movie.getOverview());
            Picasso.with(mContext).load(movieImageUri)
                    .transform(new RoundedCornersTransformation(10,10))
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.placeholder_error_image)
                    .into(regularMovieViewHolder.ivMovieImage);
        }
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void clear() {
        mMovieList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Movie> movies){
        mMovieList.addAll(movies);
        notifyDataSetChanged();
    }

}
