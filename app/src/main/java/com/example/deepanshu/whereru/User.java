package com.example.deepanshu.whereru;

import android.content.Context;

/**
 * Created by Deepanshu on 29-Aug-16.
 */
public class User {

    private String mobNo = null;
    private String latitude = null;
    private String longitude = null;
    private String lastLocationUpdateTime = null;

    private Context mContext;

    public User(Context context) {
        mContext = context;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLastLocationUpdateTime() {
        return lastLocationUpdateTime;
    }

    public void setLastLocationUpdateTime(String lastLocationUpdateTime) {
        this.lastLocationUpdateTime = lastLocationUpdateTime;
    }
}
