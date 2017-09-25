package com.ots.tdd.onthespectrum;

import android.os.Parcelable;
import android.os.Parcel;

/**
 * Created by francestsenn on 9/6/17.
 */

public class ProfileElement implements Parcelable {
    String infoType;
    String userInfo;
    int profileNumber;

    public ProfileElement(String infoType, String userInfo, int profileNumber) {
        this.infoType = infoType;
        this.userInfo = userInfo;
        this.profileNumber = profileNumber;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Storing the ProfileElement data to Parcel object
     **/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(infoType);
        dest.writeString(userInfo);
        dest.writeInt(profileNumber);
    }

    /**
     * Retrieving ProfileElement data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     **/
    private ProfileElement(Parcel in){
        this.infoType = in.readString();
        this.userInfo = in.readString();
        this.profileNumber = in.readInt();
    }

    public static final Parcelable.Creator<ProfileElement> CREATOR = new Parcelable.Creator<ProfileElement>() {

        @Override
        public ProfileElement createFromParcel(Parcel source) {
            return new ProfileElement(source);
        }

        @Override
        public ProfileElement[] newArray(int size) {
            return new ProfileElement[size];
        }
    };
}