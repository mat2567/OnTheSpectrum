package com.ots.tdd.onthespectrum;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class TestVoiceActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    TextToSpeech ttobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_voice);

        ttobj=new TextToSpeech(this, this);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.plivo.com/v1/Account/MANJVHYJU2YZA1MDQ2ZW/Call/";

//        String url ="https://ots-v1.herokuapp.com/text-to-speech/";
//
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("GET", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley", "Error: " + error.getMessage());
            }
        });
        queue.add(stringRequest);

//        String url = "https://api.plivo.com/v1/Account/MANJVHYJU2YZA1MDQ2ZW/Call/";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("from", "14706293412");
                params.put("to", "16784670532");
                params.put("url", "https://s3.amazonaws.com/static.plivo.com/answer.xml");
                return params;
            }
        };
        queue.add(postRequest);

        //start POST
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response) {
//                        // response
//                        Log.d("POST", response);
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("Error:", error.getMessage());
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String>  params = new HashMap<String, String>();
////                params.put("name", "Arsh");
////                params.put("domain", "https://ots-v1.herokuapp.com/text-to-speech/");
//                String script = "import plivo, plivoxml\n" +
//                        "\n" +
//                        "auth_id = \"MANJI1ZGQYOWQ3NTU4MW\"\n" +
//                        "auth_token = \"NmU2YThiMmZiM2FlNDM1MDFkMTQ2NTAyMDJmNDdh\"\n" +
//                        "\n" +
//                        "p = plivo.RestAPI(auth_id, auth_token)\n" +
//                        "\n" +
//                        "params = { #ecclesia = 4706293412\n" +
//                        "    'to': '16784670532',    # The phone numer to which the call will be placed\n" +
//                        "    'from' : '1111111111', # The phone number to be used as the caller id\n" +
//                        "\n" +
//                        "    'answer_url' : \"https://ots-v1.herokuapp.com/text-to-speech/\",\n" +
//                        "    'answer_method' : \"GET\", # The method used to call the answer_url\n" +
//                        "}\n" +
//                        "\n" +
//                        "response = p.make_call(params)\n" +
//                        "print str(response)\n";
//                //heroku requires certain file and like  requirements file,
////                String script = "hi";
//                params.put("Script",script);
//                params.put("name", "Arsh");
//
//                return params;
//            }
//        };
//        queue.add(postRequest);
//        Log.d("volley", "done");
    }

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
}
