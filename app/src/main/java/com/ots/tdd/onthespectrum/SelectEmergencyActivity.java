package com.ots.tdd.onthespectrum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectEmergencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_emergency);
    }

    public void moveToOngoingCallScreen(View v) {
        Intent intentOngoingCall = new Intent(this, OngoingCallActivity.class);
        startActivity(intentOngoingCall);
    }

    public void moveToRefMain(View v) {
        Intent intentRefMain = new Intent(this, MainActivity.class);
        startActivity(intentRefMain);
    }
}