package com.example.deepanshu.whereru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstRun extends AppCompatActivity {
    private EditText mobileNoTextBox;
    private Button done;
    private String mobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_run);
        mobileNoTextBox = (EditText) findViewById(R.id.mobile);
        if (savedInstanceState != null)
            mobileNo = savedInstanceState.getString("MOBILE");
        done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobileNo = mobileNoTextBox.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("MOBILE", mobileNo);
                setResult(0, intent);
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mobileNo = mobileNoTextBox.getText().toString();
        outState.putString("MOBILE", mobileNo);
    }

}
