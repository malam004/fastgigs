package com.example.xyzreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xyzreader.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ActivityInfo extends AppCompatActivity {

    private final static int REQUEST_CODE_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        setTitle("FASTGIGS");

        MobileAds.initialize(getApplicationContext(), getString(R.string.google_ad_id));
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

       Button btnSignIn = (Button)findViewById(R.id.sign_in);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityInfo.this, ActivitySignIn.class);
                //intent.putExtra("message", "This message comes from PassingDataSourceActivity's first button");
                startActivity(intent);
            }
        });

        Button btnLogIn = (Button)findViewById(R.id.log_in);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityInfo.this, ActivityLogIn.class);
                //intent.putExtra("message", "This message comes from PassingDataSourceActivity's second button");
                startActivityForResult(intent, REQUEST_CODE_1);
            }
        });
    }
}