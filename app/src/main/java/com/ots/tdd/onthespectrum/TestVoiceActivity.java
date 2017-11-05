package com.ots.tdd.onthespectrum;

import android.media.AudioManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;


// URL To Initiate Call:    https://ots-plivo-connection.herokuapp.com/initiate_call/

public class TestVoiceActivity extends AppCompatActivity{ //implements EventListener{

    TextToSpeech ttobj;
    HttpURLConnection connection;
    BufferedReader reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_voice);

        android.content.Context context = this.getApplicationContext();
        //ttobj=new TextToSpeech(this, this);
        new callTask().execute("https://ots-plivo-connection.herokuapp.com/initiate_call/");


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
