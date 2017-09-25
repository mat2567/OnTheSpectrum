package com.ots.tdd.onthespectrum;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.Format;
import java.text.MessageFormat;



public class CallActivity extends AppCompatActivity {


    TextView msgTxtV;
    MessageFormat personalMsg = new MessageFormat("My name is{0}. I am noverbal autistic and communicating with you through an app as an aid. I am in an emergency at {1}. \n{2}");
    Object[] profileElements ={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        msgTxtV = (TextView) findViewById(R.id.emergencyMsg);
        msgTxtV.setText(personalMsg.toPattern());
        //loadEmergencyMessage(msgTxtV);

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

        String additionalInfo = " ";
        int i = 0;

        /* Add Profile Info into array */
        String userName = (ProfileActivity.itemList.get(0)).userInfo;
        String userLocation = (ProfileActivity.itemList.get(1)).userInfo;
        profileElements[0] = userName;
        profileElements[1] = userLocation;

        for(ProfileElement element: ProfileActivity.itemList) {
            additionalInfo += element.infoType + ": " + element.userInfo + "\n";
        }
        additionalInfo = additionalInfo.substring(2);
        profileElements[2] = additionalInfo;
        //DEBUG
        //v.setText(additionalInfo);

        /* Add appropriate info to message */
        personalMsg.format(profileElements);

        /* Update xml file with new formatted message */
        v.setText(personalMsg.toPattern());
    }
}
