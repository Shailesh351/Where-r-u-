package com.example.deepanshu.whereru;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private static final int requestCode = 0;
    private TextView userMobileNumberTextBox, latitudeTextView, longitudeTextView, lastLocationTimeTextView;
    private User user;
    private Locations locations;
    private Location loc;
    private Handler handler;
    private PermissionForGPS permissionForGPS;
    ProgressDialog progressDoalog;

    private int cylce=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userMobileNumberTextBox = (TextView) findViewById(R.id.user_mobile_no);
        latitudeTextView = (TextView) findViewById(R.id.latitude_cordinates);
        longitudeTextView = (TextView) findViewById(R.id.longitude_cordinates);
        lastLocationTimeTextView = (TextView) findViewById(R.id.last_location_time);
        handler=new Handler();
        user = new User(getApplicationContext());
        locations = new Locations(getApplicationContext(),handler);

        locationUpdate();
        locations.setLocationListener(new Locations.locationChanging() {
            @Override
            public void locationChanged(Location loc) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd MMM yyyy HH:mm:ss");
                user.setLatitude(loc.getLatitude());
                user.setLongitude(loc.getLongitude());
                user.setLastLocationUpdateTime(sdf.format(java.util.Calendar.getInstance().getTime()));

                latitudeTextView.setText("Latitude: " + loc.getLatitude());
                longitudeTextView.setText("Longitude: " + loc.getLongitude());
                lastLocationTimeTextView.setText("Last Locations Time: " + user.getLastLocationUpdateTime());
            }
        });

        if (savedInstanceState == null) {
            getMobileNo();
        } else {
            user.setMobNo(savedInstanceState.getString("MOBILE"));
            userMobileNumberTextBox.append(user.getMobNo());
        }
        if (loc!=null) {
            latitudeTextView.setText("Latitude: " + user.getLatitude());
            longitudeTextView.setText("Longitude: " + user.getLongitude());
            lastLocationTimeTextView.setText("Last Locations Time: " + user.getLastLocationUpdateTime());
        }
    }

    private boolean locationUpdate(){
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd MMM yyyy HH:mm:ss");




            if (!locations.isGpsEnabled()) {
                FragmentManager manager = getFragmentManager();
                permissionForGPS = new PermissionForGPS();
                permissionForGPS.show(manager, "GPS_PERMISSION");

                cylce=1;

                return true;
            }
             else {

                progressDoalog = new ProgressDialog(MainActivity.this);

                progressDoalog.setMessage("Loading Location....");
                progressDoalog.setTitle("LOCATION");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.show();
                int i=0;

                     while(loc==null&&i<=15){
                         try {
                             Thread.sleep(1000);

                             i++;
                             loc = locations.getLocation();
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }


                         if (i<=15) {

                             progressDoalog.dismiss();
                         }
                     }
                progressDoalog.dismiss();

                if(loc!=null) {
                    user.setLatitude(loc.getLatitude());
                    user.setLongitude(loc.getLongitude());
                    user.setLastLocationUpdateTime(sdf.format(java.util.Calendar.getInstance().getTime()));
                }
                else
                {
                    latitudeTextView.setText("Unable to get a location.Please try Later");
                }
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
        if(loc!=null) {
            latitudeTextView.setText("Latitude: " + user.getLatitude());
            longitudeTextView.setText("Longitude: " + user.getLongitude());
            lastLocationTimeTextView.setText("Last Locations Time: " + user.getLastLocationUpdateTime());
        }
        permissionForGPS.dismiss();
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
