package com.smart.ticketing.pincode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.smart.ticketing.R;
import com.smart.ticketing.backendless.LoginActivity;
import com.smart.ticketing.scanner.CaptureActivity;


public class MainActivity extends AppCompatActivity {


    Button btnRegisterAddress, btnRegisterProperty, btnScan, btnViewPropertyInfo;
    Button btnEnterid;
    private String TAG = "ViewBooksActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincode_main);


        btnRegisterAddress = (Button) findViewById(R.id.health);
        btnRegisterProperty = (Button) findViewById(R.id.pharmacy);
        btnViewPropertyInfo = (Button) findViewById(R.id.blood);
        btnEnterid = (Button) findViewById(R.id.checkid);

        btnScan = (Button) findViewById(R.id.chat);


        if (LoginActivity.type.equalsIgnoreCase("post")) {
            btnScan.setVisibility(View.VISIBLE);
            btnEnterid.setVisibility(View.VISIBLE);
            btnRegisterProperty.setVisibility(View.GONE);
            btnViewPropertyInfo.setVisibility(View.GONE);
            btnRegisterAddress.setVisibility(View.GONE);

        } else {
            btnScan.setVisibility(View.GONE);
            btnEnterid.setVisibility(View.GONE);
            btnRegisterProperty.setVisibility(View.VISIBLE);
            btnViewPropertyInfo.setVisibility(View.VISIBLE);
            btnRegisterAddress.setVisibility(View.VISIBLE);
        }

//        btnRegisterProperty.setVisibility(View.GONE);
//        btnRegisterAddress.setVisibility(View.GONE);
//        btnScan.setVisibility(View.GONE);


        btnRegisterAddress.setText("Reg Address");
        btnRegisterProperty.setText("Reg Property");
        btnViewPropertyInfo.setText("View Property");
        btnScan.setText("Scan QR");


        btnEnterid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchForIdActivity.class);
                startActivity(intent);
            }
        });

        btnRegisterAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddressRegisterActivity.class);
                startActivity(intent);
            }
        });


        btnRegisterProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PropertyRegisterActivity.class);
                startActivity(intent);
            }
        });

        btnViewPropertyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewPropertiesActivity.class);
                startActivity(intent);
            }
        });

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivity(intent);
            }
        });
    }
}
