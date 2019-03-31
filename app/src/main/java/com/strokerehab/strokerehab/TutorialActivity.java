package com.strokerehab.strokerehab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class TutorialActivity extends YouTubeBaseActivity {

    String videoId = "aJOTlE1K90k";
    YouTubePlayerView youtubePlayerView;
    Button button;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tutorial );

        youtubePlayerView = (YouTubePlayerView) findViewById( R.id.youtubePlayerView );
        button = (Button) findViewById( R.id.button );


        onInitializedListener = new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo( "Hce74cEAAaE" );
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youtubePlayerView.initialize( "AIzaSyCWUIM37X3lkI8hsOrozbU8asv6d6FaNwc", onInitializedListener );
            }
        } );
    }

//    public void playVideo(final String videoId, YouTubePlayerView youTubePlayerView) {
//        //initialize youtube player view
//        youTubePlayerView.initialize( "AIzaSyCWUIM37X3lkI8hsOrozbU8asv6d6FaNwc",
//                new YouTubePlayer.OnInitializedListener() {
//                    @Override
//                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
//                                                        YouTubePlayer youTubePlayer, boolean b) {
//                        youTubePlayer.cueVideo( videoId );
//                    }
//
//                    @Override
//                    public void onInitializationFailure(YouTubePlayer.Provider provider,
//                                                        YouTubeInitializationResult youTubeInitializationResult) {
//
//                    }
//                } );
//    }

}
