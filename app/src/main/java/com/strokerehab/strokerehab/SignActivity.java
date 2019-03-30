package com.strokerehab.strokerehab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class SignActivity extends AppCompatActivity {

    TextView registerTextView, loginTextView;
    EditText nameEditText, emailEditText, phoneEditText, passwordEditText, cityEditText, fieldEditText,licenseEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign );

        centerTitle();

        registerTextView = findViewById(R.id.tv_reg_submit);
        loginTextView = findViewById(R.id.tv_login);

        nameEditText = findViewById(R.id.et_reg_name);
        emailEditText = findViewById(R.id.et_reg_email);
        phoneEditText = findViewById(R.id.et_reg_mob);
        passwordEditText = findViewById(R.id.et_reg_password);
        cityEditText = findViewById(R.id.et_reg_city);
        fieldEditText = findViewById(R.id.et_reg_field);
        licenseEditText = findViewById(R.id.et_reg_license);

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignActivity.this, LoginActivity.class));
                finishAffinity();
            }
        });
    }

    private void centerTitle() {
        ArrayList<View> textViews = new ArrayList<>();

        getWindow().getDecorView().findViewsWithText(textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT);

        if (textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if (textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get(0);
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
                appCompatTextView.setLayoutParams(params);
                appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        }
    }
}
