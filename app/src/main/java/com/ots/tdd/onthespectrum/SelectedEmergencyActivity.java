package com.ots.tdd.onthespectrum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedEmergencyActivity extends AppCompatActivity {

    String telNum = "4706293412";

    ScrollView infoScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_emergency);
        String scenarioInfo = getIntent().getStringExtra("scenario");
        String infoText = "Hello. " + scenarioInfo;
        Spanned text = Html.fromHtml(infoText);

        infoScrollView = (ScrollView) findViewById(R.id.infoScrollView);
        TextView infoTextView = new TextView(this);
        infoTextView.setText(text);
        infoScrollView.addView(infoTextView);
    }

    public void initiateCall(View v) {

        EditText txtPhn = (EditText)findViewById(R.id.phoneNumberEditText);
        telNum = txtPhn.getText().toString();
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
