package com.example.deepanshu.whereru;

import android.content.Context;

/**
 * Created by Deepanshu on 29-Aug-16.
 */
public class User {

    private String mobNo = null;
    private double latitude;
    private double longitude;
    private String lastLocationUpdateTime;

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLastLocationUpdateTime() {
        return lastLocationUpdateTime;
    }

    public void setLastLocationUpdateTime(String lastLocationUpdateTime) {
        this.lastLocationUpdateTime = lastLocationUpdateTime;
    }
}
