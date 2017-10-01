package com.ots.tdd.onthespectrum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectedEmergencyActivity extends AppCompatActivity {

    ScrollView infoScrollView;
    ArrayList<ProfileElement> profArray = new ArrayList<>();
    String telNum = "6784670532"; //4706293412
    String toSpeak = "Hello.";

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
        toSpeak = "Hello. " + appInfo + scenarioInfo + " " + profInfo;

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

    public void initiateCall(View v) {

        //EditText txtPhn = (EditText)findViewById(R.id.phoneNumberEditText);
        //telNum = txtPhn.getText().toString();
        if (telNum.length() == 10) {
            boolean valid = true;
            for (int i = 0; i < 10; i++) {
                if(!Character.isDigit(telNum.charAt(i))) {
                    valid = false;
                }
            }
            if (valid) {
                Intent intentOngoingCall = new Intent(this, OngoingCallActivity.class);
                intentOngoingCall.putExtra("TELEPHONE_NUMBER", telNum);
                intentOngoingCall.putExtra("TO_SPEAK", toSpeak);
                startActivity(intentOngoingCall);
            } else {
                String errorMessage = "Invalid phone number (not digit): " + telNum;
                displayExceptionMessage(errorMessage);
            }
        } else {
            String errorMessage = "Invalid phone number (too short): " + telNum + ": " + telNum.length();
            displayExceptionMessage(errorMessage);
        }


    }

    public void displayExceptionMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
