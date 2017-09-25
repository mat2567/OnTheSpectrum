package com.ots.tdd.onthespectrum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by francestsenn on 9/25/17.
 */

public class LoadInfoActivity extends AppCompatActivity {

    ArrayList<ProfileElement> profile = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        String savedProfFields = sharedPref.getString("ProfileFields", null);
        if (null == savedProfFields) {
            profile.add(new ProfileElement("Name", "", 0));
            profile.add(new ProfileElement("Gender", "", 1));
            profile.add(new ProfileElement("Age", "", 2));
            profile.add(new ProfileElement("Phone Number", "", 3));
        } else {
            String[] fields = savedProfFields.split(";;");
            for (int i = 0; i < fields.length; i++) {
                String info = sharedPref.getString(fields[i], "");
                profile.add(new ProfileElement(fields[i], info, i));
            }
        }

        Intent intentCall = new Intent(this, HomeActivity.class);
        intentCall.putParcelableArrayListExtra("Profile", profile);
        startActivity(intentCall);
    }

}
