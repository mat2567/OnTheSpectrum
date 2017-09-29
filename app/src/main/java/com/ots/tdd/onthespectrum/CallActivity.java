package com.ots.tdd.onthespectrum;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.Format;
import java.text.MessageFormat;
import java.util.ArrayList;


public class CallActivity extends AppCompatActivity {


    TextView msgTxtV;
    MessageFormat personalMsg = new MessageFormat("My name is{0}. I am noverbal autistic and communicating with you through an app as an aid. I am in an emergency at {1}. \n{2}");
    //ArrayList<ProfileElement> profileElements = new ArrayList<>();
    Object[] userInfo = new Object[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        msgTxtV = (TextView) findViewById(R.id.emergencyMsg);
        loadEmergencyMessage(msgTxtV);

        // (1) Get all Info From Profile
        // (2) Concatenate info with base text String.
        // (3) Display info

    }


     /**Loads user's information into View to deliver to call recipient
     * @param v the Text view to be edited.
     * @author Ecclesia Morain
     */
    public void loadEmergencyMessage(TextView v){

        /*TODO: Set check for when ProfileActivities values aren't loaded*/
        /*TODO: Get Profile Information from device instead of ProfileActivity*/
        String userName = "";
        String userLocation = "";
        String additionalInfo = "  ";
        ArrayList<ProfileElement> profileElements = new ArrayList<>();

        /* Add Profile Info From Device Into Array */

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        String savedProfFields = sharedPref.getString("ProfileFields", null);

        //Why are there values being loaded here
        if (null == savedProfFields) {
            profileElements.add(new ProfileElement("Name", "", 0));
            profileElements.add(new ProfileElement("Gender", "", 1));
            profileElements.add(new ProfileElement("Age", "", 2));
            profileElements.add(new ProfileElement("Phone Number", "", 3));
            profileElements.add(new ProfileElement("Home Address", "", 4));
        } else {
            String[] fields = savedProfFields.split(";;");
            userName = fields[0];
            userLocation = fields[3];
            for (int i = 0; i < fields.length; i++) {
                additionalInfo = sharedPref.getString(fields[i], "");
                //profileElements.add(new ProfileElement(fields[i], info, i));
            }
        }

//        for(ProfileElement element: ProfileActivity.itemList) {
//            additionalInfo += "My " + element.infoType + " is " + element.userInfo + "\n";
//        }
        additionalInfo = additionalInfo.substring(1);
        userInfo[0] = userName;
        userInfo[1] = userLocation;
        userInfo[2] = additionalInfo;

        /* Add appropriate info to message and update xml file with new formatted message */
        v.setText(personalMsg.format(userInfo));
    }



//     // TODO: What is this method doing ? IT seems to be adding Profile Elements to an array, but to do what with?
//    private void loadInfo() {
//        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
//        String savedProfFields = sharedPref.getString("ProfileFields", null);
//
//        //Why are there values being loaded here
//        if (null == savedProfFields) {
//            profileElements.add(new ProfileElement("Name", "", 0));
//            profileElements.add(new ProfileElement("Gender", "", 1));
//            profileElements.add(new ProfileElement("Age", "", 2));
//            profileElements.add(new ProfileElement("Phone Number", "", 3));
//            profileElements.add(new ProfileElement("Home Address", "", 4));
//        } else {
//            String[] fields = savedProfFields.split(";;");
//            for (int i = 0; i < fields.length; i++) {
//                String info = sharedPref.getString(fields[i], "");
//                profileElements.add(new ProfileElement(fields[i], info, i));
//            }
//        }
//    }
}
