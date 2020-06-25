package com.smart.ticketing.backendless;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
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
import com.smart.ticketing.backendless.data.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ForgotPasswordActivity extends AppCompatActivity {

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

    public static String name = "", username, password, phone, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        unbinderknife = ButterKnife.bind(this);


        btnRegister.setVisibility(View.GONE);
        btnLogin.setText("Submit");
        etPassword.setVisibility(View.GONE);
        btnForgotpassword.setVisibility(View.GONE);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsernmae.getText().toString();
                String password = etPassword.getText().toString();


                if(username.equals("")){
                    Utils.showToast(getApplicationContext(), "Enter username");
                }else{

                    IDataStore<User> querydata = Backendless.Data.of(User.class);

                    DataQueryBuilder query = DataQueryBuilder.create();
                    query.setWhereClause("username='" + username + "'");

                    querydata.find(query, new AsyncCallback<List<User>>() {
                        @Override
                        public void handleResponse(List<User> response) {
                            if(response.size() > 0 ){
                                SmsManager sms = SmsManager.getDefault();
                                sms.sendTextMessage(response.get(0).getPhone(), null , "Your password: " + response.get(0).getPassword(),null, null);
                                Utils.showToast(getApplicationContext(), "Password  sent to register phone number.");
                            }else{
                                Utils.showToast(getApplicationContext(), "Username not found");
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
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
