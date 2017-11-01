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

    /**
     * Constructor for Emergency Element containing the title, location of the image in the device's
     * memory, and the emergency number that is assigned when EmergencyElement is created.
     *
     * @param title String containing the title of the emergency scenario
     * @param imageMemLocation String containing the location of the image in the device's memory
     * @param emergencyNumber int that is assigned in order when the list of EmergencyElements is created
     */
    public EmergencyElement(String title, String imageMemLocation, int emergencyNumber) {
        this.title = title;
        this.imageMemLocation = imageMemLocation;
        this.image = BitmapFactory.decodeFile(imageMemLocation);

        this.emergencyNumber = emergencyNumber;
    }


    /**
     * Gets the title of the scenario stored in EmergencyElement
     * @return title String of the scenario
     */
    public String getTitle() {
        return title;
    }


    /**
     * Gets the image of the scenario stored in EmergencyElement
     * @return Bitmap image associated with the scenario
     */
    public Bitmap getImage() {
        return image;
    }


    /**
     * Gets the number of the scenario stored in EmergencyElement, which is used for creating the
     * list of emergencies
     * @return int emergency number assigned to the scenario
     */
    public int getEmergencyNumber() {
        return emergencyNumber;
    }


    /**
     * Gets the image location of the scenario stored in EmergencyElement
     * @return String containing the image location in memory
     */
    public String getImageMemLocation() { return imageMemLocation; }


    /**
     * Sets the title of the scenario stored in EmergencyElement
     * @param title String containing the new title of the scenario
     */
    public void setTitle(String title) { this.title = title; }


    /**
     * Sets the image of the scenario stored in EmergencyElement
     * @param image Bitmap containing the new image of the scenario
     */
    public void setImage(Bitmap image) {
        this.image = image;
    }


    /**
     * Sets the emergency number of the scenario stored in EmergencyElement
     * @param emergencyNumber int to determine the number of this scenario in the emergency list
     */
    public void setEmergencyNumber(int emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }


    /**
     * Sets the memory location of the image of the scenario stored in EmergencyElement
     * @param memLocation String containing the image location in memory
     */
    public void setImageMemLocation(String memLocation) { this.imageMemLocation = memLocation; }
}
