package com.ots.tdd.onthespectrum;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ethan on 10/8/2017.
 */

public class EmergencyElementViewContainer {
    ImageButton emergencyButton;
    int emergencyNumber;

    static ArrayList<EmergencyElementViewContainer> emergencyElements= new ArrayList<EmergencyElementViewContainer>();

    public EmergencyElementViewContainer(ImageButton emergencyButton, int emergencyNumber) {
        this.emergencyButton = emergencyButton;
        this.emergencyNumber = emergencyNumber;
    }

    public static ImageButton getButton(EmergencyElementViewContainer pevc) {
        return pevc.emergencyButton;
    }

    public static EmergencyElementViewContainer findContainerUsingButton(ImageButton eButton) {
        for (int i = 0; i < emergencyElements.size(); i++) {
            EmergencyElementViewContainer curr = emergencyElements.get(i);
            if (curr.emergencyButton.equals(eButton)) {
                return curr;
            }
        }
        return null;
    }

    public static EmergencyElementViewContainer findContainerUsingNumber(int pNum) {
        for (int i = 0; i < emergencyElements.size(); i++) {
            EmergencyElementViewContainer curr = emergencyElements.get(i);
            if (curr.emergencyNumber ==pNum) {
                return curr;
            }
        }
        return null;
    }

    public static void addEEVCToArray(EmergencyElementViewContainer eevc) {
        emergencyElements.add(eevc);
    }

    public static int getEmergencyNumber(EmergencyElementViewContainer eevc) {
        return eevc.emergencyNumber;
    }

}
