package com.strokerehab.strokerehab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorDashboardActivity extends AppCompatActivity {
    LinearLayout graphLL, chatLL, patientsLL;
    SeekBar effortSeekBar;
    Integer currentValue;
    FirebaseDatabase database;
    DatabaseReference mDatabaseRef;
    ImageView logoutImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        graphLL = findViewById(R.id.ll_real_time_graph);
        chatLL = findViewById(R.id.ll_chat);
        patientsLL = findViewById(R.id.ll_guide);

        database = FirebaseDatabase.getInstance();
        mDatabaseRef = database.getReference();

        effortSeekBar = findViewById(R.id.sb_effort);
        currentValue = effortSeekBar.getProgress();

        logoutImageView = findViewById(R.id.iv_logout);

        effortSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(final SeekBar seekBar, int progress, boolean fromUser) {
                currentValue = progress;
                Toast.makeText(DoctorDashboardActivity.this, "" + currentValue, Toast.LENGTH_SHORT).show();

                try {
                    mDatabaseRef.child("devices").child("effort").setValue(currentValue);
                } catch (Exception e) {
                    Log.e("TAG", e.getMessage());
                }

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        graphLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDashboardActivity.this, GraphActivity.class));
            }
        });

        chatLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDashboardActivity.this, ChatActivity.class));
            }
        });

        logoutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDashboardActivity.this, LoginActivity.class));
                finishAffinity();
            }
        });
    }

}
