package com.strokerehab.strokerehab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

    EditText muserName;
    EditText mPassWord;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        muserName = findViewById( R.id.mobile_field );
        mPassWord = findViewById( R.id.password_field );

        loginButton = findViewById( R.id.login_button );

        loginButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( "asda", muserName.getText().toString() + "" + mPassWord.getText().toString() + "" );
                if (muserName.getText().toString().equals( "7840844065" ) && mPassWord.getText().toString().equals( "1234" )) {
                    startActivity( new Intent( LoginActivity.this, MainActivity.class ) );
                }
            }
        } );


    }
}
