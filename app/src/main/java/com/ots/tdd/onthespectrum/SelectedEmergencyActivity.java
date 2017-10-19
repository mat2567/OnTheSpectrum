package com.ots.tdd.onthespectrum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectedEmergencyActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    ScrollView infoScrollView;
    ArrayList<ProfileElement> profArray = new ArrayList<>();
    String telNum = "+4706293412"; //6784670532
    String toSpeak = "Hello.";
    TextToSpeech ttobj;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_emergency);



        String scenarioInfo = getIntent().getStringExtra("scenario");
        ttobj=new TextToSpeech(this, this);

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

        // Start Sinch Client
        //TODO Does it make sense to initiate this here?
        final SinchClient sinchClient = Sinch.getSinchClientBuilder()
                .context(this.getApplicationContext())
                .userId("current-user-id")
                .applicationKey("f59d26b3-3ca3-49dd-8d88-0f309049bd28") //
                .applicationSecret("0wZ54QhUBEylRod2EyaShA==")
                .environmentHost("clientapi.sinch.com")
                .build();
        sinchClient.setSupportCalling(true);
        sinchClient.start();

        //Watch for call button to be clicked
        final Button button = (Button) findViewById(R.id.button11);

//        try {
        button.setOnClickListener(new View.OnClickListener() {
            Call call;
            TextView callState = (TextView) findViewById(R.id.callState);

            @Override
            public void onClick(View view) {
                // make a call!
                if (call == null) {
                    call = sinchClient.getCallClient().callPhoneNumber("+46000000000");
                    button.setText("Hang Up");
               } else {
                    call.hangup();
                    call = null;
                    button.setText("Call");
                }
                callState.setText("connected");
            }
        });
//        } catch(Exception ex) {
//            Log.e("Error on call button", ex.getMessage());
//        }

//            class SinchCallListener implements CallListener {
//                @Override
//                public void onCallEnded(Call endedCall) {
//                    //call ended by either party
//                    call = null;
//                    button.setText("Call");
//                    callState.setText("Call Ended");
//                    setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
//                }
//                @Override
//                public void onCallEstablished(Call establishedCall) {
//                    //incoming call was picked up
//                    callState.setText("connected");
//                    setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
//                }
//                @Override
//                public void onCallProgressing(Call progressingCall) {
//                    //call is ringing
//                    callState.setText("ringing");
//                }
//                @Override
//                public void onShouldSendPushNotification(Call call, List<PushPair> pushPairs) {
//                    //don't worry about this right now
//                }
//            }
//        });


        infoScrollView = (ScrollView) findViewById(R.id.infoScrollView);
        TextView infoTextView = new TextView(this);
        infoTextView.setText(infoText);
        infoScrollView.addView(infoTextView);
    }

    @Override
    public void onInit(int status)
    {
        if (status == TextToSpeech.SUCCESS)
        {
            ttobj.setLanguage(Locale.US);
            //ttobj.speak("hello World", 0, null);
        }
        else if (status == TextToSpeech.ERROR)
        {
            Log.e("Text To Speach", "ERROR");
        }
    }

    public void testVoice(View v) {
        ttobj.speak(toSpeak, 0, null);
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

////    public void initiateCall(View v) {
////
////        EditText txtPhn = (EditText)findViewById(R.id.phoneNumberEditText);
////        telNum = txtPhn.getText().toString();
////        if (telNum.length() == 10) {
////            boolean validLength = true;
////            for (int i = 0; i < 10; i++) {
////                if(!Character.isDigit(telNum.charAt(i))) {
////                    validLength = false;
////                }
////            }
////            if (validLength) {
////                Intent intentOngoingCall = new Intent(this, OngoingCallActivity.class);
////                intentOngoingCall.putExtra("TELEPHONE_NUMBER", telNum);
////                intentOngoingCall.putExtra("TO_SPEAK", toSpeak);
////                startActivity(intentOngoingCall);
////            } else {
////                String errorMessage = "Invalid phone number (not digit): " + telNum;
////                displayExceptionMessage(errorMessage);
////            }
////        } else {
////            String errorMessage = "Invalid phone number (too short): " + telNum + ": " + telNum.length();
////            displayExceptionMessage(errorMessage);
////        }
////
//
//    }

    public void displayExceptionMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
