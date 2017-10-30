package com.ots.tdd.onthespectrum;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Locale;

import java.util.LinkedHashMap;
import com.plivo.endpoint.*;
import com.plivo.*;

public class TestVoiceActivity extends AppCompatActivity implements EventListener{

    TextToSpeech ttobj;

    public final static String EXTRA_MESSAGE = "com.plivo.example.MESSAGE";

    // Edit the variables below with your Plivo endpoint username and password
    public final static String PLIVO_USERNAME = "emorain";
    public final static String PLIVO_PASSWORD = "12345";

    // Edit the PHONE_NUMBER with the number you want to make the call to
    public static String PHONE_NUMBER = "14706293412";
    //private EditText number;
    private AudioManager myAudioManager;
    Endpoint endpoint = Endpoint.newInstance(true, this);
    Outgoing outgoing = new Outgoing(endpoint);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_voice);


        //ttobj=new TextToSpeech(this, this);
        Log.v("PlivoOutbound", "Trying to log in");
        endpoint.login(PLIVO_USERNAME, PLIVO_PASSWORD);
        //number = (EditText) findViewById(R.id.number);

    }

    public void callNow(View view) {
        // Log into plivo cloud
        outgoing = endpoint.createOutgoingCall();
        Log.v("PlivoOutbound", "Create outbound call object");
        //PHONE_NUMBER = number.getText().toString();
        Log.v("PlivoOutbound", PHONE_NUMBER);
        outgoing.call(PHONE_NUMBER);

    }

    @Override
    public void onLogin() {

    }

    @Override
    public void onLogout() {

    }

    @Override
    public void onLoginFailed() {

    }

    @Override
    public void onIncomingDigitNotification(String s) {

    }

    @Override
    public void onIncomingCall(Incoming incoming) {

    }

    @Override
    public void onIncomingCallHangup(Incoming incoming) {

    }

    @Override
    public void onIncomingCallRejected(Incoming incoming) {

    }

    @Override
    public void onOutgoingCall(Outgoing outgoing) {

    }

    @Override
    public void onOutgoingCallAnswered(Outgoing outgoing) {

    }

    @Override
    public void onOutgoingCallRejected(Outgoing outgoing) {

    }

    @Override
    public void onOutgoingCallHangup(Outgoing outgoing) {

    }

    @Override
    public void onOutgoingCallInvalid(Outgoing outgoing) {

    }

//    @Override
//    public void onInit(int status)
//    {
//        if (status == TextToSpeech.SUCCESS)
//        {
//            ttobj.setLanguage(Locale.US);
//            ttobj.speak("hello World", 0, null);
//        }
//        else if (status == TextToSpeech.ERROR)
//        {
//            Log.e("Text To Speach", "ERROR");
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        // Don't forget to shutdown tts!
//        if (ttobj != null) {
//            ttobj.stop();
//            ttobj.shutdown();
//        }
//        super.onDestroy();
//    }
}
