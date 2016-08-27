package com.example.deepanshu.whereru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private static final int requestCode=0;
private TextView userMobileNumberTextBox;
private String mobileNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userMobileNumberTextBox=(TextView) findViewById(R.id.user_mobile_no);

        if (savedInstanceState==null) {
            String mobileNo=MobileNumberPreferences.getMobileNo(this);
            if (mobileNo == null) {
                Intent intent = new Intent(this, FirstRun.class);
                startActivityForResult(intent, requestCode);
            } else {
                mobileNumber = MobileNumberPreferences.getMobileNo(this);
                userMobileNumberTextBox.append(mobileNo);
            }
        }
        else {
            mobileNumber=savedInstanceState.getString("MOBILE");
            userMobileNumberTextBox.append(mobileNumber);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0){
            mobileNumber=data.getStringExtra("MOBILE");
            MobileNumberPreferences.setMobileNo(mobileNumber,this);
            userMobileNumberTextBox.append(mobileNumber);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("MOBILE",mobileNumber);
    }
}
