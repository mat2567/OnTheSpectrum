package com.ots.tdd.onthespectrum;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
