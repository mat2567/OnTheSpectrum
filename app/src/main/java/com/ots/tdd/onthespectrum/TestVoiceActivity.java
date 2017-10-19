package com.ots.tdd.onthespectrum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

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

import java.util.List;
import java.util.Locale;



public class TestVoiceActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    TextToSpeech ttobj;
    String callState = "";
    private Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_voice);

        ttobj=new TextToSpeech(this, this);
        android.content.Context context = this.getApplicationContext();

        SinchClient sinchClient = Sinch.getSinchClientBuilder()
                    .context(context)
                    .userId("current-user-id")
                    .applicationKey("f59d26b3-3ca3-49dd-8d88-0f309049bd28") //
                    .applicationSecret("0wZ54QhUBEylRod2EyaShA==")
                    .environmentHost("clientapi.sinch.com")
                    .build();

        sinchClient.addSinchClientListener(new SinchClientListener() {
            public void onClientStarted(SinchClient client) { }
            public void onClientStopped(SinchClient client) { }
            public void onClientFailed(SinchClient client, SinchError error) { }
            public void onRegistrationCredentialsRequired(SinchClient client, ClientRegistration registrationCallback) { }
            public void onLogMessage(int level, String area, String message) { }
        });

        sinchClient.setSupportCalling(true);
        sinchClient.start();

        CallClient callClient = sinchClient.getCallClient();
        callClient.addCallClientListener(new SinchCallClientListener());

        call = callClient.getCall("+16784670532");
//        call = callClient.callPhoneNumber("+16784670532");

//        call.addCallListener(new SinchCallListener());
//        call.addCallListener(new SinchCallListener());

//        CallClient callClient = sinchClient.getCallClient();
//        Call call = callClient.callPhoneNumber("+16784670532");
// Or for video call: Call call = callClient.callUserVideo("<remote user id>");
//        call.addCallListener();
    }

//    public void onRegistrationCredentialsRequired(SinchClient client,
//                                                  ClientRegistration registrationCallback) {
//        // This will on the first run for this user call onRegistrationCredentialsRequired on the client listener.
//        // Perform API request to server which keeps the Application Secret.
//        myApiService.getAuthorizedSignatureForUser("<user id>", new OnCompletedCallback() {
//            public void onCompleted(String signature, long sequence) {
//                // pass the signature and sequence back to the Sinch SDK
//                // via the ClientRegistration interface.
//                registrationCallback.register(signature, sequence);
//            }
//        });
//    }
    @Override
    public void onInit(int status)
    {
        if (status == TextToSpeech.SUCCESS)
        {
            ttobj.setLanguage(Locale.US);
            ttobj.speak("hello World", 0, null);
        }
        else if (status == TextToSpeech.ERROR)
        {
            Log.e("Text To Speach", "ERROR");
        }
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (ttobj != null) {
            ttobj.stop();
            ttobj.shutdown();
        }
        super.onDestroy();
    }

    private class SinchCallListener implements CallListener {
        @Override
        public void onCallEnded(Call endedCall) {
            call = null;
            callState = "";
            Toast.makeText(TestVoiceActivity.this, "Ended", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCallEstablished(Call establishedCall) {
            callState = "connected";
            Toast.makeText(TestVoiceActivity.this, "Established", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCallProgressing(Call progressingCall) {
            Toast.makeText(TestVoiceActivity.this, "Ringing", Toast.LENGTH_SHORT).show();
            callState = "ringing";
        }

        @Override
        public void onShouldSendPushNotification(Call call, List<PushPair> pushPairs) {}
    }

    private class SinchCallClientListener implements CallClientListener {
        @Override
        public void onIncomingCall(CallClient callClient, Call incomingCall) {
            call = incomingCall;
            call.answer();
            call.addCallListener(new SinchCallListener());
        }

    }
}
