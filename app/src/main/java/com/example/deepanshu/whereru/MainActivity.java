package com.example.deepanshu.whereru;

import android.app.FragmentManager;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private static final int requestCode = 0;
    private TextView userMobileNumberTextBox, latitudeTextView, longitudeTextView, lastLocationTimeTextView;
    private User user;
    private Locations locations;
    private Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userMobileNumberTextBox = (TextView) findViewById(R.id.user_mobile_no);
        latitudeTextView = (TextView) findViewById(R.id.latitude_cordinates);
        longitudeTextView = (TextView) findViewById(R.id.longitude_cordinates);
        lastLocationTimeTextView = (TextView) findViewById(R.id.last_location_time);

        user = new User(getApplicationContext());
        locations = new Locations(getApplicationContext());

        locationUpdate();


        if (savedInstanceState == null) {
            getMobileNo();
        } else {
            user.setMobNo(savedInstanceState.getString("MOBILE"));
            userMobileNumberTextBox.append(user.getMobNo());
        }

        latitudeTextView.setText("Latitude: " + user.getLatitude());
        longitudeTextView.setText("Longitude: " + user.getLongitude());
        lastLocationTimeTextView.setText("Last Locations Time: " + user.getLastLocationUpdateTime());
    }

    private boolean locationUpdate(){
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd MMM yyyy HH:mm:ss");




            if (!locations.isGpsEnabled()) {
                FragmentManager manager = getFragmentManager();
                PermissionForGPS permissionForGPS = new PermissionForGPS();
                permissionForGPS.show(manager, "GPS_PERMISSION");

                return true;
            }
             else {
                loc = locations.getLocation();
                user.setLatitude(loc.getLatitude());
                user.setLongitude(loc.getLongitude());
                user.setLastLocationUpdateTime(sdf.format(java.util.Calendar.getInstance().getTime()));
                return  false;
            }
    }

    private void getMobileNo(){
        String mobileNo = MobileNumberPreferences.getMobileNo(this);
        if (mobileNo == null) {
            Intent intent = new Intent(this, FirstRun.class);
            startActivityForResult(intent, requestCode);
        } else {
            user.setMobNo(MobileNumberPreferences.getMobileNo(this));
            userMobileNumberTextBox.append(user.getMobNo());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            user.setMobNo(data.getStringExtra("MOBILE"));
            MobileNumberPreferences.setMobileNo(user.getMobNo(), this);
            userMobileNumberTextBox.append(user.getMobNo());
        }

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("MOBILE", user.getMobNo());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        locationUpdate();
        latitudeTextView.setText("Latitude: " + user.getLatitude());
        longitudeTextView.setText("Longitude: " + user.getLongitude());
        lastLocationTimeTextView.setText("Last Locations Time: " + user.getLastLocationUpdateTime());


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

}
