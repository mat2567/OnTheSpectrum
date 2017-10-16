package com.ots.tdd.onthespectrum;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

/**
 * Created by Ethan on 10/6/2017.
 */

public class EmergencyElement {
    private String title;
    private Bitmap image;
    String imageMemLocation;
    int emergencyNumber;

    public EmergencyElement(String title, String imageMemLocation, int emergencyNumber) {
        this.title = title;
        this.imageMemLocation = imageMemLocation;
        this.image = BitmapFactory.decodeFile(imageMemLocation);

        this.emergencyNumber = emergencyNumber;
    }

    public String getTitle() {
        return title;
    }

    public Bitmap getImage() {
        return image;
    }

    public int getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setEmergencyNumber(int emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }
}
