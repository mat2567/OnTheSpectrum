package com.ots.tdd.onthespectrum;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// URL To Initiate Call:    https://ots-plivo-connection.herokuapp.com/initiate_call/

public class TestVoiceActivity extends AppCompatActivity{ //implements EventListener{

    TextToSpeech ttobj;
    HttpURLConnection connection;
    BufferedReader reader;
    String endpointURL = "https://ots-plivo-connection.herokuapp.com/initiate_call/" + SelectedEmergencyActivity.toSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_voice);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        String careTakerNum = sharedPref.getString("Caretaker Phone Number", null);

        android.content.Context context = this.getApplicationContext();
//        ttobj=new TextToSpeech(this, this);
        new callTask().execute(endpointURL);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SEND_SMS},1);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        while(permissionCheck == PackageManager.PERMISSION_DENIED) {
            permissionCheck = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.SEND_SMS);
        }
        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            SmsManager smsManager = SmsManager.getDefault();
            if (careTakerNum != null)
                smsManager.sendTextMessage(careTakerNum,
                        null,"hello Arsh", null,null);
            //TODO: replace Hello Arsh with the string that we want to send to caretaker
        }
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
            callStatus.setText(result);
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
}
