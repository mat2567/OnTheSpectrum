package com.ots.tdd.onthespectrum;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by francestsenn on 10/6/17.
 */

public class EmergencyElement {
    String title;
    String imageMemLocation;
    Bitmap image;

    public EmergencyElement(String title, String imageMemLocation) {
        this.title = title;
        this.imageMemLocation = imageMemLocation;
        this.image = BitmapFactory.decodeFile(imageMemLocation);
    }
}
