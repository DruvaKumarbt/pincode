package com.smart.ticketing.pincode;

import android.content.DialogInterface;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.smart.ticketing.R;
import com.smart.ticketing.Utils;
import com.smart.ticketing.facereco.LocationHandler;
import com.smart.ticketing.pincode.data.Property;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PropertyRegisterActivity extends AppCompatActivity {


    @BindView(R.id.editText0)
    EditText etAadharId;


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

    @BindView(R.id.editText9)
    EditText etKathano;

    @BindView(R.id.editText10)
    EditText etFromWhom;

    @BindView(R.id.editText11)
    EditText etToWhom;

    @BindView(R.id.editText12)
    EditText etRegdate;

    @BindView(R.id.editText13)
    EditText etRegno;

    @BindView(R.id.button2)
    Button btnSubmit;

    @BindView(R.id.delete)
    Button btnDelete;


    private Unbinder unbinderknife;
    private String TAG = "RegisterActivity";
    String usertype;

    Property address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincode_property);

        unbinderknife = ButterKnife.bind(this);

        address = (Property) getIntent().getSerializableExtra("data");

        if (address != null) {
            etAadharId.setText(address.getAadharId());
            etAddress1.setText(address.getAddressLine1());
            etAddress2.setText(address.getAddressLine2());
            etCity.setText(address.getCity());
            etFromWhom.setText(address.getFromWhom());
            etKathano.setText(address.getKathaNo());
            etLatLng.setText(address.getKathaNo());
            etName.setText(address.getName());
            etPhone.setText(address.getPhone());
            etPincode.setText(address.getPincode());
            etPropertyId.setText(address.getPid());
            etRegdate.setText(address.getDateOfReg());
            etRegno.setText(address.getRegNo());
            etToWhom.setText(address.getToWhom());
            btnDelete.setVisibility(View.VISIBLE);

        } else {
            btnDelete.setVisibility(View.GONE);
            new LocationHandler(this).initLocation(new LocationHandler.OnLocationChanged() {
                @Override
                public void onLocationAvailable(Location location) {
                    Log.i(TAG, "");
                    etLatLng.setText(location.getLatitude() + "," + location.getLongitude());
                }
            });
        }


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlert("Are you sure to delete?");

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String aadhar = etAadharId.getText().toString();
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String pid = etPropertyId.getText().toString();
                String pincode = etPincode.getText().toString();
                String address1 = etAddress1.getText().toString();
                String latlng = etLatLng.getText().toString();
                String address2 = etAddress2.getText().toString();
                String city = etCity.getText().toString();
                String kathano = etKathano.getText().toString();
                String fromwhom = etFromWhom.getText().toString();
                String towhom = etToWhom.getText().toString();
                String date = etRegdate.getText().toString();
                String regno = etRegno.getText().toString();

                if (aadhar.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter aadhar");
                } else if (name.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter name");
                } else if (phone.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter phone");
                } else if (pid.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter property id");
                } else if (pincode.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter pincode");
                } else if (address1.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter address1");
                } else if (address2.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter address2");
                } else if (latlng.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter latlng");
                } else if (city.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter city");
                } else if (kathano.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter kathano");
                } else if (fromwhom.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter fromwhom");
                } else if (towhom.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter towhom");
                } else if (date.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter reg date");
                } else if (regno.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter reg no");
                } else {
                    if (aadhar.length() != 12) {
                        Utils.showToast(getApplication(), "Invalid aadharId");
                        return;
                    }
                    if (phone.length() != 10) {
                        Utils.showToast(getApplicationContext(), "Invalid phone");
                    } else {

//                        User user = new User();
//                        user.setAddress(address);
//                        user.setUsertype(usertype);
//                        user.setUsername(username);
//                        user.setName(name);
//                        user.setPhone(phone);

                        if (address == null) {
                            address = new Property();
                        }

                        address.setAadharId(aadhar);
                        address.setName(name);
                        address.setPhone(phone);
                        address.setPincode(pincode);
                        address.setPid(pid);
                        address.setAddressLine1(address1);
                        address.setAddressLine2(address2);
                        address.setLatlng(latlng);
                        address.setCity(city);
                        address.setKathaNo(kathano);
                        address.setFromWhom(fromwhom);
                        address.setToWhom(towhom);
                        address.setDateOfReg(date);
                        address.setRegNo(regno);

                        if (address.getObjectId() != null) {
                            Backendless.Persistence.save(address, new AsyncCallback<Property>() {
                                @Override
                                public void handleResponse(Property response) {
                                    Utils.showToast(getApplicationContext(), "Property registration successfull");
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Utils.showToast(getApplicationContext(), "Error reg: " + fault.getMessage());
                                }
                            });
                        } else {


                            IDataStore<Property> querydata = Backendless.Data.of(Property.class);

                            DataQueryBuilder query = DataQueryBuilder.create();
                            query.setWhereClause("pid='" + pid + "'");

                            querydata.find(query, new AsyncCallback<List<Property>>() {
                                @Override
                                public void handleResponse(List<Property> users) {
                                    if (users.size() > 0) {
                                        Utils.showToast(getApplicationContext(), "Property Id exists");
                                    } else {


                                        Backendless.Persistence.save(address, new AsyncCallback<Property>() {
                                            @Override
                                            public void handleResponse(Property response) {
                                                Utils.showToast(getApplicationContext(), "Property registration successfull");
                                            }

                                            @Override
                                            public void handleFault(BackendlessFault fault) {
                                                Utils.showToast(getApplicationContext(), "Error reg: " + fault.getMessage());
                                            }
                                        });

                                    }
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Utils.showToast(getApplicationContext(), "Login error: " + fault);
                                }
                            });
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinderknife.unbind();
    }


    public void showAlert(final String message){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }

        builder.setTitle("Delete")
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        Backendless.Data.of(Property.class).remove(address, new AsyncCallback<Long>() {
                            @Override
                            public void handleResponse(Long response) {
                                Log.i(TAG, "delete successful: " + response);
                                Utils.showToast(getApplicationContext(), "Delete successfully");
                                finish();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Log.i(TAG, "delete failed: " + fault.getMessage());
                            }
                        });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
