package com.smart.ticketing.pincode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.smart.ticketing.R;
import com.smart.ticketing.Utils;

public class SearchForIdActivity extends AppCompatActivity {


    ListView lvDiseses;
    EditText etQRId;
    Button btnSubmit;

    private String TAG = "ViewBooksActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_issues);


        lvDiseses = (ListView) findViewById(R.id.listview);
        etQRId = (EditText) findViewById(R.id.editText);
        btnSubmit = (Button) findViewById(R.id.button1);

        etQRId.setHint("Enter QRID");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etQRId.getText().toString();
                if(name.equals("")){
                    Utils.showToast(getApplicationContext(), "Enter id");
                    return;
                }

                Intent intent = new Intent(SearchForIdActivity.this, AddressInfoActivity.class);
                intent.putExtra("id", name);
                startActivity(intent);
            }
        });

    }
}
