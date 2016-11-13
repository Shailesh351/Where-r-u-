package com.example.deepanshu.whereru.other;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Deepanshu on 30-Aug-16.
 */
public class Locations implements LocationListener {
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    Location location=new Location("Location");
    TextView txtLat;
    String lat;
    String provider;
    User user ;
    protected double latitude, longitude;
    protected static boolean gps_enabled, network_enabled;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60; // 1 minute
    private locationChanging locationChangingListener;
    private Handler mainActivityHandler;
    public Locations(Context con, Handler handler) {
        context = con;
        mainActivityHandler=handler;
    }


    public boolean isGpsEnabled(){
        locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);

        // getting GPS status
        gps_enabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        network_enabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (network_enabled&&gps_enabled) {
           return true;

            //  network provider is enabled
        }
    return false;
    }
    public Location getLocation() {
        try {
            locationManager = (LocationManager) context
                    .getSystemService(Context.LOCATION_SERVICE);

            // getting GPS status
            gps_enabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            network_enabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!gps_enabled && !network_enabled) {

                return null;
                // no network provider is enabled
            } else {
                //this.canGetLocation = true;
                if (network_enabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("activity", "LOC Network Enabled");
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            Log.d("activity", "LOC by Network");
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (gps_enabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("activity", "RLOC: GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                Log.d("activity", "RLOC: loc by GPS");
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("activity", "RLOC: Locations xx "+latitude+" "+longitude);

        return location;
    }
    public interface locationChanging{
        void locationChanged(Location loc);
    }
    @Override
    public void onLocationChanged(final Location loc) {
        mainActivityHandler.post(new Runnable() {
            @Override
            public void run() {
                locationChangingListener.locationChanged(loc);
            }
        });
        location.set(loc);
    }
    public void setLocationListener(locationChanging listener){
        locationChangingListener = listener;    
    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.i("Latitude","status");
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.i("Latitude","enable");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.i("Latitude","disable");
    }
}
