package com.strokerehab.strokerehab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.strokerehab.strokerehab.Util.CustomWebViewClient;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chat );
        centerTitle();

        WebView mywebview = (WebView) findViewById( R.id.wv_chat_page );
        mywebview.loadUrl( "http://stroke-rehab-chat.herokuapp.com/" );


        mywebview.setWebViewClient( new CustomWebViewClient() );
        WebSettings webSettings = mywebview.getSettings();
        webSettings.setJavaScriptEnabled( true );
        webSettings.setPluginState( WebSettings.PluginState.ON );
    }

    private void centerTitle() {
        ArrayList<View> textViews = new ArrayList<>();

        getWindow().getDecorView().findViewsWithText( textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT );

        if (textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if (textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get( 0 );
            } else {
                for (View v : textViews) {
                    if (v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }

            if (appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams( params );
                appCompatTextView.setTextAlignment( View.TEXT_ALIGNMENT_CENTER );
            }
        }
    }

}
