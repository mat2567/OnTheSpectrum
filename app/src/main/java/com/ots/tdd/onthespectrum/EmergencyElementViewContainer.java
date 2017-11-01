package com.ots.tdd.onthespectrum;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Ethan on 10/8/2017.
 */

public class EmergencyElementViewContainer {
    ImageButton emergencyButton;
    TextView emergencyTitle;
    int emergencyNumber;

    static ArrayList<EmergencyElementViewContainer> emergencyElements= new ArrayList<EmergencyElementViewContainer>();


    /**
     * Constructor for the view container of the EmergencyElement
     * @param emergencyButton ImageButton containing the image of the scenario
     * @param emergencyTitle TextView containing the text with the emergency scenario's title
     * @param emergencyNumber int of the EmergencyElement, which helps with arranging the list
     */
    public EmergencyElementViewContainer(ImageButton emergencyButton, TextView emergencyTitle, int emergencyNumber) {
        this.emergencyButton = emergencyButton;
        this.emergencyTitle = emergencyTitle;
        this.emergencyNumber = emergencyNumber;
    }


    /**
     * Gets the view container of the EmergencyElement with the given emergency number
     * @param pNum int emergency number of the desired EmergencyElement
     * @return EmergencyElementViewContainer of the desired EmergencyElement
     */
    public static EmergencyElementViewContainer findContainerUsingNumber(int pNum) {
        for (int i = 0; i < emergencyElements.size(); i++) {
            EmergencyElementViewContainer curr = emergencyElements.get(i);
            if (curr.emergencyNumber ==pNum) {
                return curr;
            }
        }
        return null;
    }

    /**
     * Adds another EmergencyElementViewContainer to an array containing all scenarios
     * @param eevc EmergencyElementViewController of the scenario
     */
    public static void addEEVCToArray(EmergencyElementViewContainer eevc) {
        emergencyElements.add(eevc);
    }

}
