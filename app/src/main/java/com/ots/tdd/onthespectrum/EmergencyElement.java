package com.ots.tdd.onthespectrum;

import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

/**
 * Created by Ethan on 10/6/2017.
 */

public class EmergencyElement {
    private String title;
    private Drawable image;
    int emergencyNumber;

    public EmergencyElement(String title, Drawable image, int emergencyNumber) {
        this.title = title;
        this.image = image;
        this.emergencyNumber = emergencyNumber;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getImage() {
        return image;
    }

    public int getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setEmergencyNumber(int emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }
}
