package com.ots.tdd.onthespectrum;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by francestsenn on 9/6/17.
 */

public class ProfileElement {
    TextView profileTextView = null;
    EditText profileEditText = null;
    ImageView profileEdit = null;
    ImageView profileSave = null;
    ImageView profileCancel = null;

    int editVis = View.VISIBLE;
    int saveVis = View.GONE;
    int cancelVis = View.GONE;
    boolean editTextEnabled = false;

    String infoType;
    String userInfo;
    int profileNumber;
    String previousUserInfo;

    public ProfileElement(String infoType, String userInfo, int profileNumber) {
        this.infoType = infoType;
        this.userInfo = userInfo;
        this.profileNumber = profileNumber;
    }
}