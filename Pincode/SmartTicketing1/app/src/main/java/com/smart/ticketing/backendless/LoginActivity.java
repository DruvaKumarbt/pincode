package com.smart.ticketing.backendless;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.smart.ticketing.R;
import com.smart.ticketing.Utils;
import com.smart.ticketing.backendless.data.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.editText)
    EditText etUsernmae;

    @BindView(R.id.editText2)
    EditText etPassword;

    @BindView(R.id.button1)
    Button btnLogin;

    @BindView(R.id.button2)
    Button btnRegister;


    @BindView(R.id.forgotpassword)
    Button btnForgotpassword;


    private Unbinder unbinderknife;

    public static String name = "", username, password, phone, address, fatherPhone, type ="";
    public static User user;

    public static int balance = 1500;

    private String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.olx_activity_home);


        unbinderknife = ButterKnife.bind(this);

        btnRegister.setVisibility(View.GONE);
        btnForgotpassword.setVisibility(View.GONE);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnForgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsernmae.getText().toString();
                String password = etPassword.getText().toString();

//                username = "hod";
//                password = "hod";

                if (username.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter username");
                } else if (password.equals("")) {
                    Utils.showToast(getApplicationContext(), "Enter password");
                } else {

                    /*if(username.equals("post") && password.equals("post")){
                        Intent intent = new Intent(LoginActivity.this, SplashActivity.sClass);
                        type = "post";
                        startActivity(intent);
                        return;
                    }

                    if(username.equals("admin") && password.equals("admin")){
                        Intent intent = new Intent(LoginActivity.this, SplashActivity.sClass);
                        type = "admin";
                        startActivity(intent);
                        return;
                    }*/



                    BackendLessManager.userLogin(username, password, new BackendLessManager.OnBackendLessResponseListener() {
                        @Override
                        public void onBackendResponse(Object object, boolean status) {

                            if (status) {
                                List<User> users = ((List<User>) object);
                                if (users.size() > 0) {

                                    Utils.showToast(LoginActivity.this, "Login successfull");

                                    Intent intent = new Intent(LoginActivity.this, SplashActivity.sClass);
                                    startActivity(intent);

                                    name = users.get(0).getName();
                                    LoginActivity.username = users.get(0).getUsername();
                                    LoginActivity.password = users.get(0).getPassword();
                                    phone = users.get(0).getPhone();
                                    address = users.get(0).getAddress();
                                    fatherPhone = users.get(0).getFatherPhone();
                                    type = users.get(0).getType();
                                    user = users.get(0);
                                } else {
                                    Utils.showToast(getApplicationContext(), "Invalid credentials");
                                }
                            } else {
                                Utils.showToast(LoginActivity.this, "Registation failed");
                            }
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

}
