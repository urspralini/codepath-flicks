package com.prabhu.codepath.flicks.activities;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.prabhu.codepath.flicks.R;

import java.util.ArrayList;

public class VideoViewerActivity extends YouTubeBaseActivity {

    public static final String YOUTUBE_API_KEY = "AIzaSyCu-firjZ-LLhOr6N8jK9aD7Zi9HODRPQI";
    public static final String YOUTUBE_MOVIE_KEYS = "YOUTUBE-MOVIE-ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_view);
        final ArrayList<String> youtubeMovieIds = getIntent().getStringArrayListExtra(YOUTUBE_MOVIE_KEYS);
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView)findViewById(R.id.player);
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideos(youtubeMovieIds);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

}
