package com.ots.tdd.onthespectrum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {


    Button mCallButton; // 'm' indicates that this stands for member data. This implies that it will have a global reference.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mCallButton = findViewById(R.id.callButton);
        mCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiateCall(view);
            }
        });

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        String prevPassword = sharedPref.getString("Lock", null);
        if (null == prevPassword) {
            SharedPreferences sp = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Lock", "");
            editor.commit();
            SettingsActivity.isLocked = false;
        } else {
            if (prevPassword.equals("")) {
                SettingsActivity.isLocked = false;
            } else {
                SettingsActivity.isLocked = true;
            }
        }

        // Show the action bar and return arrow at the top of the screen
        // TODO: Fix this.
        // Potential fix: https://stackoverflow.com/questions/24596494/android-app-crashes-on-startup-in-emulator
        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
    }

    public void initiateCall(View view) {
//        // Log into plivo cloud
//        outgoing = endpoint.createOutgoingCall();
//        Log.v("PlivoOutbound", "Create outbound call object");
//        //PHONE_NUMBER = number.getText().toString();
//        Log.v("PlivoOutbound", PHONE_NUMBER);
//        outgoing.call(PHONE_NUMBER);
        Intent callIntent = new Intent(HomeActivity.this, TestVoiceActivity.class);
        startActivity(callIntent);
    }

    public void moveToCallScreen(View v) {
        Intent intentCall = new Intent(this, ChooseEmergencyActivity.class);
        startActivity(intentCall);
    }

    public void moveToProfileScreen(View v) {
        Intent intentProfile = new Intent(this, ProfileActivity.class);
        startActivity(intentProfile);
    }

    public void moveToSettingsScreen(View v) {
        Intent intentSettings = new Intent(this, SettingsActivity.class);
        startActivity(intentSettings);
    }

    public void moveToEmergencyListScreen(View v) {
        Intent intentEmergencyList = new Intent(this, ListOfEmergenciesActivity.class);
        startActivity(intentEmergencyList);
    }

    public void moveToTestVoiceScreen(View v) {
//        Intent intentTestVoice = new Intent(this, TestVoiceActivity.class);
//        startActivity(intentTestVoice);
    }
}
