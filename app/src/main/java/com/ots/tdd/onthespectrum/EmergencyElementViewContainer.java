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

    public EmergencyElementViewContainer(ImageButton emergencyButton, TextView emergencyTitle, int emergencyNumber) {
        this.emergencyButton = emergencyButton;
        this.emergencyTitle = emergencyTitle;
        this.emergencyNumber = emergencyNumber;
    }

    public static ImageButton getButton(EmergencyElementViewContainer pevc) {
        return pevc.emergencyButton;
    }

    public static TextView getTextView(EmergencyElementViewContainer pevc) {
        return pevc.emergencyTitle;
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

    public static EmergencyElementViewContainer findContainerUsingTitle(TextView title) {
        for (int i = 0; i < emergencyElements.size(); i++) {
            EmergencyElementViewContainer curr = emergencyElements.get(i);
            if (curr.emergencyTitle.equals(title)) {
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
