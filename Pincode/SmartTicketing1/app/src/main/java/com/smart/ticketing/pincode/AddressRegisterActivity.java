package com.smart.ticketing.pincode;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.smart.ticketing.QRGeneratorTransaction;
import com.smart.ticketing.R;
import com.smart.ticketing.Utils;
import com.smart.ticketing.facereco.LocationHandler;
import com.smart.ticketing.pincode.data.Addresses;
import com.smart.ticketing.pincode.data.Property;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AddressRegisterActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText etName;

    @BindView(R.id.editText2)
    EditText etPhone;

    @BindView(R.id.editText3)
    EditText etPropertyId;

    @BindView(R.id.editText4)
    EditText etPincode;

    @BindView(R.id.editText5)
    EditText etAddress1;

    @BindView(R.id.editText6)
    EditText etAddress2;

    @BindView(R.id.editText7)
    EditText etLatLng;

    @BindView(R.id.editText8)
    EditText etCity;

    @BindView(R.id.button2)
    Button btnSubmit;

    private Unbinder unbinderknife;
    private String TAG = "RegisterActivity";
    String usertype ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincode_register);

        unbinderknife = ButterKnife.bind(this);


        etPropertyId.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.i(TAG, "Enter pressed");
                    Utils.showToast(getApplicationContext(), "Done pressed");

                    if(etPropertyId.getText().toString().equalsIgnoreCase("")){
                        Utils.showToast(getApplicationContext(), "Enter property id");
                        return false;
                    }
                    fetchProperTyInfo(etPropertyId.getText().toString());

                }
                return false;
            }
        });

        new LocationHandler(this).initLocation(new LocationHandler.OnLocationChanged() {
            @Override
            public void onLocationAvailable(Location location) {
                etLatLng.setText(location.getLatitude()+","+location.getLongitude());
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String pid = etPropertyId.getText().toString();
                String pincode = etPincode.getText().toString();
                String address1 = etAddress1.getText().toString();
                String latlng = etLatLng.getText().toString();
                String address2 = etAddress2.getText().toString();
                String city = etCity.getText().toString();

                if (name.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter name");
                } else if (phone.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter phone");
                } else if (pid.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter property id");
                } else if (pincode.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter pincode");
                } else if (address1.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter address1");
                } else if(address2.equals("")){
                    Utils.showToast(getApplicationContext(), "Enter address2");
                } else if(latlng.equals("")){
                    Utils.showToast(getApplicationContext(), "Enter latlng");
                } else if(city.equals("")){
                    Utils.showToast(getApplicationContext(), "Enter city");
                } else {
                    /*if(name.length() != 12){
                        Utils.showToast(getApplication(), "Invalid aadharId");
                        return;
                    }*/
                    if (phone.length() != 10) {
                        Utils.showToast(getApplicationContext(), "Invalid phone");
                    } else {

//                        User user = new User();
//                        user.setAddress(address);
//                        user.setUsertype(usertype);
//                        user.setUsername(username);
//                        user.setName(name);
//                        user.setPhone(phone);

                        final Addresses address = new Addresses();

                        address.setName(name);
                        address.setPhone(phone);
                        address.setPincode(pincode);
                        address.setPropertyId(pid);
                        address.setAddressLine1(address1);
                        address.setAddressLine2(address2);
                        address.setLatlng(latlng);
                        address.setCity(city);

                        IDataStore<Addresses> querydata = Backendless.Data.of(Addresses.class);

                        DataQueryBuilder query = DataQueryBuilder.create();
                        query.setWhereClause("propertyId='" + pid + "'");

                        querydata.find(query, new AsyncCallback<List<Addresses>>() {
                            @Override
                            public void handleResponse(List<Addresses> users) {
                                if (users.size() > 0) {
                                    Utils.showToast(getApplicationContext(), "Property Id exists");
                                } else {


                                    Backendless.Persistence.save(address, new AsyncCallback<Addresses>() {
                                        @Override
                                        public void handleResponse(Addresses response) {
                                            Utils.showToast(getApplicationContext(), "Address registration successfull");
                                            Intent intent = new Intent(AddressRegisterActivity.this, QRGeneratorTransaction.class);
                                            intent.putExtra("id", response.getObjectId());
                                            intent.putExtra("name", response.getName());
                                            startActivity(intent);

                                        }

                                        @Override
                                        public void handleFault(BackendlessFault fault) {
                                            Utils.showToast(getApplicationContext(), "Error reg: "+fault.getMessage());
                                        }
                                    });

                                }
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Utils.showToast(getApplicationContext(), "Login error: "+fault);
                            }
                        });
                    }
                }
            }
        });
    }



    public void fetchProperTyInfo(String pid){
        IDataStore<Property> querydata = Backendless.Data.of(Property.class);

        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause("pid='" + pid + "'");

        querydata.find(query, new AsyncCallback<List<Property>>() {
            @Override
            public void handleResponse(List<Property> users) {
                if (users.size() > 0) {
                    Property property = users.get(0);
                    etAddress1.setText(property.getAddressLine1());
                    etAddress2.setText(property.getAddressLine2());
                    etCity.setText(property.getCity());
                    etLatLng.setText(property.getLatlng());
                    etName.setText(property.getName());
                    etPhone.setText(property.getPhone());
                    etPincode.setText(property.getPincode());
                } else {
                    Utils.showToast(getApplicationContext(), "Property Id does not found");

                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Utils.showToast(getApplicationContext(), "Login error: " + fault);
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinderknife.unbind();
    }
}
