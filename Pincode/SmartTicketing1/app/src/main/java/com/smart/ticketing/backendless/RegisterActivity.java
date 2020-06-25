package com.smart.ticketing.backendless;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.smart.ticketing.R;
import com.smart.ticketing.Utils;
import com.smart.ticketing.backendless.data.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText etUsernmae;

    @BindView(R.id.editText2)
    EditText etPassword;

    @BindView(R.id.editText3)
    EditText etName;

    @BindView(R.id.editText4)
    EditText etPhone;

    @BindView(R.id.editText5)
    EditText etAddress;

    @BindView(R.id.editText7)
    EditText etLocality;

    @BindView(R.id.editText8)
    EditText etBloodGroup;

    @BindView(R.id.editText6)
    EditText etDepartment;

    @BindView(R.id.button2)
    Button btnSubmit;

    private Unbinder unbinderknife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        unbinderknife = ButterKnife.bind(this);

//        etLocality.setHint("Enter sem");
        etDepartment.setVisibility(View.GONE);
        etDepartment.setText("cse");
//        etLocality.setVisibility(View.GONE);
//        etBloodGroup.setVisibility(View.GONE);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = etUsernmae.getText().toString();
                String password = etPassword.getText().toString();
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String address = etAddress.getText().toString();
                String locality = etLocality.getText().toString();
                String bloodGroup = etBloodGroup.getText().toString();
                String department = etDepartment.getText().toString();

                if (username.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter username");
                } else if (password.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter password");
                } else if (name.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter name");
                } else if (phone.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter phone");
                } else if (address.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter address");
                } else if (locality.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter locality");
                } else if (bloodGroup.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter bloodgroup");
                } else if (department.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter department");
                }
                else {
                    if (phone.length() != 10) {
                        Utils.showToast(getApplicationContext(), "Invalid phone");
                    } else {

                        User user = new User();
                        user.setUsername(username);
                        user.setPassword(password);
                        user.setName(name);
                        user.setPhone(phone);
                        user.setAddress(address);
                        user.setLocality(locality);
                        user.setBloodGroup(bloodGroup);
                        user.setDepartment(department);
//                        user.setType("student");

                        BackendLessManager.insertUser(user, new BackendLessManager.OnBackendLessResponseListener() {
                            @Override
                            public void onBackendResponse(Object object, boolean status) {
                                if (status) {
                                    Utils.showToast(RegisterActivity.this, "Registation successfull");
                                    finish();
                                } else {
                                    Utils.showToast(RegisterActivity.this, "Registation failed");
                                }
                            }
                        });
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
}
