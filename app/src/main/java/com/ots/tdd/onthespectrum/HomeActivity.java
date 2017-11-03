package com.ots.tdd.onthespectrum;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {

    Button emergencyButton;
    Button profileButton;
    Button editEmergencyButton;
    Button settingsButton;

    int fontChange;
    int titleSize;
    int subtitleSize;
    int bodySize;

    SharedPreferences sharedPref;

    int colorHomeButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        int theme = sharedPref.getInt("colorTheme", 0);
        if (theme == 0) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("colorTheme", R.style.AppTheme);
            colorHomeButtons = R.color.colorHome;
            editor.commit();
        } else {
            switch (theme) {
                case R.style.AppTheme:
                    colorHomeButtons = R.color.colorHome;
                    break;
                case R.style.Theme1:
                    colorHomeButtons = R.color.theme1Home;
                    break;
            }
        }
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String prevPassword = sharedPref.getString("Lock", null);
        if (null == prevPassword) {
            SharedPreferences.Editor editor = sharedPref.edit();
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
        setTextSizesAndColors();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.recreate();
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

    private void setTextSizesAndColors() {
        emergencyButton = (Button) findViewById(R.id.emergencyButton);
        profileButton = (Button) findViewById(R.id.profileButton);
        editEmergencyButton = (Button) findViewById(R.id.editEmergencyButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);

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

        emergencyButton.setBackgroundColor(getResources().getColor(colorHomeButtons));
    }
}
