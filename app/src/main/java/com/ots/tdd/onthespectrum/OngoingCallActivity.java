package com.ots.tdd.onthespectrum;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bandwidth.sdk.BandwidthClient;
import com.bandwidth.sdk.model.Account;
import com.bandwidth.sdk.model.Call;
import com.bandwidth.sdk.model.AvailableNumber;
import com.bandwidth.sdk.model.PhoneNumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OngoingCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_call);


    }

    // Code taken from the provided examples from Bandwidth.com
    public void initiateCall(View v) throws Exception {
        // There are two ways to set your creds, e.g. your App Platform userId, api token and api secret
        // you can set these as environment variables or set them with the
        // BandwidthClient.getInstance(userId, apiToken, apiSecret) method.
        //
        // Use the setenv.sh script to set the env variables
        // BANDWIDTH_USER_ID
        // BANDWIDTH_API_TOKEN
        // BANDWIDTH_API_SECRET
        //
        // or uncomment this line and set them here
        // BandwidthClient.getInstance(userId, apiToken, apiSecret);
        // Temporary way to set up credentials whenever calling is done
        String userId = "ppa-tfsefyllbvkepcyoo6twgiy";
        String apiToken = "t-rpyepvjj2hbqlk2nawj6xxi";
        String apiSecret = "nodhdx3sckvajw42eeaal35dncnrutssrkgrzaa";

        //Debugging
        Button testButton = (Button) findViewById(R.id.TestCall);
        testButton.setBackgroundColor(Color.parseColor("#fc3468"));

        new PerformCallTask().execute(userId, apiToken, apiSecret);

        /*try {
            BandwidthClient.getInstance().setCredentials(userId, apiToken, apiSecret);


        }
        catch(final Exception e) {
            e.printStackTrace();
        }

        List<PhoneNumber> numbers = orderNumber();

        //Debugging
        testButton.setBackgroundColor(Color.parseColor("#d1a827"));

        // put your numbers in here
        // Ecclesia's number
        final String toNumber = "+14706293412";// your phone number here

        //final String fromNumber = "+1";// this is a number that is allocated on the AppPlatform. You can do this
        // via the dev console or with the SDK (see AllocateNumberExample)
        final String fromNumber = numbers.get(0).getNumber();

        //Debugging
        testButton.setBackgroundColor(Color.parseColor("#fbeee5"));

        final Call call = Call.create(toNumber, fromNumber);

        //Debugging
        testButton.setBackgroundColor(Color.parseColor("#bb1515"));

        System.out.println("call:" + call);

        //wait a few seconds here. Note that in a real application you'll handle the answer event
        // in your event server. See java-bandwidth-examples for how to do this
        try {
            Thread.sleep(10000);
        } catch(final InterruptedException ex) {
            Thread.currentThread().interrupt();
        }


        //Debugging
        testButton.setBackgroundColor(Color.parseColor("#594e36"));

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("sentence", "Hello. This is a test message.");
        params.put("voice", "kate"); // she's one of our favorites!
        call.speakSentence(params);

        //Debugging
        testButton.setBackgroundColor(Color.parseColor("#bad80a"));

        //wait a few more seconds to let the message play. Again in a real application this would be part
        // of your event handling. See java-bandwidth-examples for how to do this
        try {
            Thread.sleep(4000);
        } catch(final InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Updated call:" + call);
        call.hangUp();*/

        //Debugging
        testButton.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    // Method taken from an example provided by Bandwidth.com
    // Change later to better fit our needs
    public static List<PhoneNumber> orderNumber() throws Exception {
        String city = "Atlanta";
        String state = "GA";

        final Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("city", city);
        searchParams.put("quantity", 1);
        searchParams.put("state", state);

        final List<AvailableNumber> availableNumbers = AvailableNumber.searchLocal(searchParams);
        final List<PhoneNumber> orderedNumbers = new ArrayList<PhoneNumber>();

        for (final AvailableNumber availableNumber : availableNumbers) {
            final Map<String, Object> orderParams = new HashMap<String, Object>();
            orderParams.put("number", availableNumber.getNumber());

            orderedNumbers.add(PhoneNumber.create(orderParams));
        }

        return orderedNumbers;
    }


    public void cancelCall(View v) {

    }

    private class PerformCallTask extends AsyncTask<String, String, String> {
        protected String doInBackground(String... strs) {

            //edit later for error checking
            String userId = strs[0];
            String apiToken = strs[1];
            String apiSecret = strs[2];

            try {
                BandwidthClient.getInstance().setCredentials(userId, apiToken, apiSecret);
            }
            catch(final Exception e) {
                e.printStackTrace();
            }

            List<PhoneNumber> numbers = null;

            try {
                numbers = orderNumber();

                // put your numbers in here
                // Ecclesia's number
                final String toNumber = "+14706293412";// your phone number here

                //final String fromNumber = "+1";// this is a number that is allocated on the AppPlatform. You can do this
                // via the dev console or with the SDK (see AllocateNumberExample)
                String fromNumber = "";
                if (numbers != null) {
                    fromNumber = numbers.get(0).getNumber();
                }

                final Call call = Call.create(toNumber, fromNumber);

                System.out.println("call:" + call);

                //wait a few seconds here. Note that in a real application you'll handle the answer event
                // in your event server. See java-bandwidth-examples for how to do this
                try {
                    Thread.sleep(10000);
                } catch(final InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }


                final Map<String, Object> params = new HashMap<String, Object>();
                params.put("sentence", "Hello. This is a test message.");
                params.put("voice", "kate"); // she's one of our favorites!
                call.speakSentence(params);

                //wait a few more seconds to let the message play. Again in a real application this would be part
                // of your event handling. See java-bandwidth-examples for how to do this
                try {
                    Thread.sleep(4000);
                } catch(final InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Updated call:" + call);
                call.hangUp();
            }
            catch(final Exception e) {
                e.printStackTrace();
            }

            return userId;
        }

        protected void onPostExecute(String result) {

        }
    }
}
