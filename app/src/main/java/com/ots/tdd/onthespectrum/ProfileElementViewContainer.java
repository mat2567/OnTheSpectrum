package com.ots.tdd.onthespectrum;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ethan on 9/7/2017.
 */

public class ProfileElementViewContainer {
    TextView profileTextView;
    EditText profileEditText;
    ImageView profileEdit;
    ImageView profileSave;
    ImageView profileCancel;
    String previousText;
    int profileNumber;

    static ArrayList<ProfileElementViewContainer> profileElements= new ArrayList<ProfileElementViewContainer>();


    public ProfileElementViewContainer(TextView profileTextView, EditText profileEditText,
                                       ImageView profileEdit, ImageView profileSave,
                                       ImageView profileCancel, int profileNumber) {
        this.profileTextView = profileTextView;
        this.profileEditText = profileEditText;
        this.profileEdit = profileEdit;
        this.profileSave = profileSave;
        this.profileCancel = profileCancel;
        this.previousText = "";
        this.profileNumber = profileNumber;
    }

    public static EditText getEditText(ProfileElementViewContainer pevc) {
        return pevc.profileEditText;
    }

    public static TextView getTextView(ProfileElementViewContainer pevc) {
        return pevc.profileTextView;
    }

    public static ImageView getEdit(ProfileElementViewContainer pevc) {
        return pevc.profileEdit;
    }

    public static ImageView getSave(ProfileElementViewContainer pevc) {
        return pevc.profileSave;
    }

    public static ImageView getCancel(ProfileElementViewContainer pevc) {
        return pevc.profileCancel;
    }

    public static ProfileElementViewContainer findContainerUsingEdit(ImageView pEdit) {
        for (int i = 0; i < profileElements.size(); i++) {
            ProfileElementViewContainer curr = profileElements.get(i);
            if (curr.profileEdit.equals(pEdit)) {
                return curr;
            }
        }
        return null;
    }

    public static ProfileElementViewContainer findContainerUsingSave(ImageView pSave) {
        for (int i = 0; i < profileElements.size(); i++) {
            ProfileElementViewContainer curr = profileElements.get(i);
            if (curr.profileSave.equals(pSave)) {
                return curr;
            }
        }
        return null;
    }

    public static ProfileElementViewContainer findContainerUsingCancel(ImageView pCancel) {
        for (int i = 0; i < profileElements.size(); i++) {
            ProfileElementViewContainer curr = profileElements.get(i);
            if (curr.profileCancel.equals(pCancel)) {
                return curr;
            }
        }
        return null;
    }

    public static ProfileElementViewContainer findContainerUsingNumber(int pNum) {
        for (int i = 0; i < profileElements.size(); i++) {
            ProfileElementViewContainer curr = profileElements.get(i);
            if (curr.profileNumber ==pNum) {
                return curr;
            }
        }
        return null;
    }

    public static void addPEVCToArray(ProfileElementViewContainer pevc) {
        profileElements.add(pevc);
    }

    public static void setPreviousText(ProfileElementViewContainer pevc, String s) {
        pevc.previousText = s;
    }

    public static String getPreviousText(ProfileElementViewContainer pevc) {
        return pevc.previousText;
    }

    public static int getProfileNumber(ProfileElementViewContainer pevc) {
        return pevc.profileNumber;
    }

}


