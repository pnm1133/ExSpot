package com.example.nguyephan.findshopspot.data.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nguye phan on 3/22/2018.
 */

public class UserResponse implements Parcelable {

    protected UserResponse(Parcel in) {

    }

    public static final Creator<UserResponse> CREATOR = new Creator<UserResponse>() {
        @Override
        public UserResponse createFromParcel(Parcel in) {
            return new UserResponse(in);
        }

        @Override
        public UserResponse[] newArray(int size) {
            return new UserResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
