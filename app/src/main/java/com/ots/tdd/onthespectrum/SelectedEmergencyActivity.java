package com.ots.tdd.onthespectrum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectedEmergencyActivity extends AppCompatActivity {

    ScrollView infoScrollView;
    ArrayList<ProfileElement> profArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_emergency);
        String scenarioInfo = getIntent().getStringExtra("scenario");

        String appInfo = "I have nonverbal autism, and I am speaking to you through an application on my phone. ";

        loadProfileInfo();
        String profInfo = "";
        for (ProfileElement profElem : profArray) {
            if (profElem.userInfo != "") {
                profInfo += "My " + profElem.infoType + " is " + profElem.userInfo + ". ";
            }
        }

        String infoText = "What will be said on your call:\n\nHello. " + appInfo + scenarioInfo + " " + profInfo;

        infoScrollView = (ScrollView) findViewById(R.id.infoScrollView);
        TextView infoTextView = new TextView(this);
        infoTextView.setText(infoText);
        infoScrollView.addView(infoTextView);
    }


    private void loadProfileInfo() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        String savedProfFields = sharedPref.getString("ProfileFields", null);
        if (null == savedProfFields) {
            profArray.add(new ProfileElement("Name", "", 0));
            profArray.add(new ProfileElement("Gender", "", 1));
            profArray.add(new ProfileElement("Age", "", 2));
            profArray.add(new ProfileElement("Phone Number", "", 3));
            profArray.add(new ProfileElement("Home Address", "", 4));
        } else {
            String[] fields = savedProfFields.split(";;");
            for (int i = 0; i < fields.length; i++) {
                String info = sharedPref.getString(fields[i], "");
                profArray.add(new ProfileElement(fields[i], info, i));
            }
        }
    }
}
