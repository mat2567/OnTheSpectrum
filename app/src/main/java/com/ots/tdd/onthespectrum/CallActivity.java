package com.ots.tdd.onthespectrum;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CallActivity extends AppCompatActivity {

    String telNum = "4706293412";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        // Show the action bar and return arrow at the top of the screen
        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
    }

    public void initiateCall(View v) {
        Intent intentOngoingCall = new Intent(this, OngoingCallActivity.class);
        intentOngoingCall.putExtra("TELEPHONE_NUMBER", telNum);
        startActivity(intentOngoingCall);
    }
}
