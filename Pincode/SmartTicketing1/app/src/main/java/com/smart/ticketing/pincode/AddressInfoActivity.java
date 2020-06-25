package com.smart.ticketing.pincode;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.smart.ticketing.R;
import com.smart.ticketing.Utils;
import com.smart.ticketing.backendless.data.Pass;
import com.smart.ticketing.facereco.LocationHandler;
import com.smart.ticketing.pincode.data.Addresses;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AddressInfoActivity extends AppCompatActivity {


    private final String TAG = "MainActivity";

    @BindView(R.id.tv)
    TextView tvDetails;

    @BindView(R.id.playvideo)
    Button btnPlayvideo;

    private Unbinder unbinderknife;

    static String name = "";

    Pass ads = new Pass();

    private int CAMERA_REQUEST = 100;

    String filePath = "";

    boolean isUpdate;

    Map<String, List<String>> map = new HashMap<>();

    List<String> malls = new ArrayList<>();

    int totalAmount = 0;

    Location currLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qless_offers_activity);

        unbinderknife = ButterKnife.bind(this);

        offerId = getIntent().getStringExtra("id");
        String data = getIntent().getStringExtra("data");
        tvDetails.setText(offerId);


        btnPlayvideo.setText("Direction");




        fetchData();


        btnPlayvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (phone != null) {

                    String uri = "http://maps.google.com/maps?q=" + item.getLatlng() + " (" + "location " + item.getName()
                            + "   " + item.getPhone() + ")";

                    Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(it);


                    new LocationHandler(AddressInfoActivity.this).initLocation(new LocationHandler.OnLocationChanged() {
                        @Override
                        public void onLocationAvailable(Location location) {

                            Utils.showToast(getApplicationContext(), "Found location");
                            String uri = "http://maps.google.com/maps?q=" + location.getLatitude()+"," +location.getLongitude()+ "(location)";
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phone, null, "Dear " +item.getName() + ", You have new post, post man just started to your deliver it to your home. loc: "+uri, null, null);
                        }
                    });

                }


            }
        });


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinderknife.unbind();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

        }
    }

    String offerId;
    String youtubeLink;
    Addresses item;
    String phone;

    public void fetchData() {
        IDataStore<Addresses> items = Backendless.Data.of(Addresses.class);
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("objectId = '" + offerId + "'");
        queryBuilder.setPageSize(100);

        items.find(queryBuilder, new AsyncCallback<List<Addresses>>() {
            @Override
            public void handleResponse(List<Addresses> itemslist) {
                Log.i(TAG, "Retrieved " + itemslist.size() + " objects");

                if (itemslist.size() > 0) {

                    tvDetails.setText("Name: " + itemslist.get(0).getName() + "\nLatlng:\n" + itemslist.get(0).getLatlng());
                    phone = itemslist.get(0).getPhone();
                    item = itemslist.get(0);

                } else {
                    Utils.showToast(getApplicationContext(), "No items available");
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG, "Server reported an error " + fault.getMessage());
            }
        });
    }
}
