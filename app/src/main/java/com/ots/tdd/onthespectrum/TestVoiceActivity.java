package com.ots.tdd.onthespectrum;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


// URL To Initiate Call:    https://ots-plivo-connection.herokuapp.com/initiate_call/

public class TestVoiceActivity extends AppCompatActivity{ //implements EventListener{

    TextToSpeech ttobj;
    HttpURLConnection connection;
    BufferedReader reader;
    String endpointURL = "https://request-plivo-connection.herokuapp.com//initiate_call/" + SelectedEmergencyActivity.toSpeak;
    SharedPreferences sharedPref;
    String location = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_voice);

        location = getIntent().getStringExtra("location");

        android.content.Context context = this.getApplicationContext();
        new callTask().execute(endpointURL);

        sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        String careTakerNum = sharedPref.getString("Caretaker Phone Number", null);
        sendText(careTakerNum);
    }


    // Probably should put this call in onResume
    public class callTask extends AsyncTask<String, String, String> {
        TextView callStatus = findViewById(R.id.call_status);

        @Override
        protected String doInBackground(String... params) {
            try {
                //Connect to Server
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // Receive response from server
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = " ";

                while((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            callStatus.setText("The message was successfully sent.");
        }
    }


    public void sendText(String careTakerNum) {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SEND_SMS},1);
        ArrayList<String> messages = new ArrayList<>();
        String message =
                "Hello Caretaker of "+sharedPref.getString("Name", "OnTheSpectrum user")+",\n" +
                "I am in a "+ sharedPref.getString("ScenarioNames", "emergency") + ".";
        messages.add(message);
        String ns = "";
        String ew = "";
        int sep = 0;

        if (location.indexOf('N') != -1) {
            ns = location.substring(0, location.indexOf('N') -1);
            sep = location.indexOf('N') + 2;
        } else if (location.indexOf('S') != -1) {
            ns = "-" + location.substring(0, location.indexOf('S') -1);
            sep = location.indexOf('S') + 2;
        }

        if (location.indexOf('E') != -1) {
            ew = location.substring(sep, location.indexOf('E'));
        } else if (location.indexOf('W') != -1) {
            ew = "-" + location.substring(sep, location.indexOf('W'));
        }
        String googleURL = "http://www.google.com/maps/place/" +ns + "," + ew;

        String more  = "I have contacted emergency services. Here is my location: " + googleURL;
        messages.add(more);
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        while(permissionCheck == PackageManager.PERMISSION_DENIED) {
            permissionCheck = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.SEND_SMS);
        }
        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            SmsManager smsManager = SmsManager.getDefault();
            if (careTakerNum != null) {
                smsManager.sendMultipartTextMessage(careTakerNum,
                        null, messages, null, null);
            }
        }
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

    public void returnToHome(View v) {
        // Return to home screen
        /*Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
        finish();
    }

    @Override
    public void onStop() {
        super.onStop();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
