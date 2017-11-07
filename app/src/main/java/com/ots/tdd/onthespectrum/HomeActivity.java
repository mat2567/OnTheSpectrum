package com.ots.tdd.onthespectrum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.ClientRegistration;
import com.sinch.android.rtc.SinchClientListener;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.calling.CallListener;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        getApplicationContext().bindService(new Intent(this, SinchService.class), this,
//                BIND_AUTO_CREATE);
        android.content.Context context = this.getApplicationContext();
        final SinchClient sinchClient = Sinch.getSinchClientBuilder()
                    .context(context)
                    .userId("arsh")
                    .applicationKey("f59d26b3-3ca3-49dd-8d88-0f309049bd28") //
                    .applicationSecret("0wZ54QhUBEylRod2EyaShA==")
                    .environmentHost("sandbox.sinch.com")
                    .build();

        sinchClient.setSupportCalling(true);
//        sinchClient.getCallClient().callPhoneNumber("+46000000000");
//        sinchClient.startListeningOnActiveConnection();
//
//        sinchClient.addSinchClientListener(new MySinchClientListener());
//        sinchClient.getCallClient().addCallClientListener(new SinchCallClientListener());
        sinchClient.start();
        // Show the action bar and return arrow at the top of the screen
        // TODO: Fix this.
        // Potential fix: https://stackoverflow.com/questions/24596494/android-app-crashes-on-startup-in-emulator
        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
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
        Intent intentEmergencyList = new Intent(this, ChooseEmergencyActivity.class);
        startActivity(intentEmergencyList);
    }

    public void moveToTestVoiceScreen(View v) {
        Intent intentTestVoice = new Intent(this, TestVoiceActivity.class);
        Log.d("Home", "here");

        startActivity(intentTestVoice);
    }
}
