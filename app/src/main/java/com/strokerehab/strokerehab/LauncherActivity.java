package com.strokerehab.strokerehab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_launcher );
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep( 4000 );
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent mainIntent = new Intent( LauncherActivity.this, LoginActivity.class );
                    startActivity( mainIntent );
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
