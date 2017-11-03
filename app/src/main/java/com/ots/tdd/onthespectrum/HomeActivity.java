package com.ots.tdd.onthespectrum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {

    Button emergencyButton;
    Button profileButton;
    Button editEmergencyButton;
    Button settingsButton;

    int fontChange;
    int titleSize;
    int subtitleSize;
    int bodySize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
        setTextSizes();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        setTextSizes();
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
        Intent intentTestVoice = new Intent(this, TestVoiceActivity.class);
        startActivity(intentTestVoice);
    }

    private void setTextSizes() {
        emergencyButton = (Button) findViewById(R.id.emergencyButton);
        profileButton = (Button) findViewById(R.id.profileButton);
        editEmergencyButton = (Button) findViewById(R.id.editEmergencyButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        titleSize = sharedPref.getInt("TitleFontSize", 0);
        if (titleSize == 0) {
            //add it to SharedPreferences
            titleSize = 30;
            editor.putInt("TitleFontSize", titleSize);
        }

        subtitleSize = sharedPref.getInt("SubtitleFontSize", 0);
        if (subtitleSize == 0) {
            //add it to SharedPreferences
            subtitleSize = 18;
            editor.putInt("SubtitleFontSize", subtitleSize);
        }

        bodySize = sharedPref.getInt("BodyFontSize", 0);
        if (bodySize == 0) {
            //add it to SharedPreferences
            bodySize = 16;
            editor.putInt("BodyFontSize", bodySize);
        }

        fontChange = sharedPref.getInt("FontSizeChange", Integer.MAX_VALUE);
        if (fontChange == Integer.MAX_VALUE) {
            //add it to SharedPreferences
            fontChange = 0;
            editor.putInt("FontSizeChange", fontChange);
        }

        editor.commit();

        emergencyButton.setTextSize(bodySize + 2 + fontChange);
        profileButton.setTextSize(bodySize + 2 + fontChange);
        editEmergencyButton.setTextSize(bodySize + 2 + fontChange);
        settingsButton.setTextSize(bodySize + 2 + fontChange);
    }
}
