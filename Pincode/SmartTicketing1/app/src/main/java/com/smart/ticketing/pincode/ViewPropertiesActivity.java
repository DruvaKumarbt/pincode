package com.smart.ticketing.pincode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.smart.ticketing.R;
import com.smart.ticketing.Utils;
import com.smart.ticketing.pincode.data.Property;

import java.util.ArrayList;
import java.util.List;

public class ViewPropertiesActivity extends AppCompatActivity {


    ListView lvDiseses;
    EditText etAadharId;
    Button btnSubmit;

    private String TAG = "ViewBooksActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_issues);


        lvDiseses = (ListView) findViewById(R.id.listview);
        etAadharId = (EditText) findViewById(R.id.editText);
        btnSubmit = (Button) findViewById(R.id.button1);

        etAadharId.setHint("Enter AadharId");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etAadharId.getText().toString();
                if(name.equals("")){
                    Utils.showToast(getApplicationContext(), "Enter aadharId");
                    return;
                }else if(name.length() !=12){
                    Utils.showToast(getApplicationContext(), "Invalid aadharId");
                    return;
                }
                fetchDisesess(name);
            }
        });

        lvDiseses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ViewPropertiesActivity.this, PropertyRegisterActivity.class);
                intent.putExtra("data", propertyList.get(i));
                startActivity(intent);
            }
        });
    }


    public void fetchDisesess(String name) {
        IDataStore<Property> querydata = Backendless.Data.of(Property.class);
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause("aadharId = '"+name+"'");

        querydata.find(query, new AsyncCallback<List<Property>>() {
            @Override
            public void handleResponse(List<Property> response) {
                if (response.size() > 0) {
                    propertyList = response;
                    updateList();
                } else {
                    Utils.showToast(getApplicationContext(), "Details did not match. Verify details and try again.");
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
            }
        });
    }

    List<Property> propertyList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();
    public void updateList(){
        nameList.clear();
        for(Property disease : propertyList){
            nameList.add(disease.getName() + "\n"+disease.getLatlng()+"\nKathNo: "+disease.getKathaNo()+"\nRegNo: "+disease.getRegNo()+"\nFrom: "+disease.getFromWhom());
        }

        ArrayAdapter<String> aa = new ArrayAdapter<String>(ViewPropertiesActivity.this, android.R.layout.simple_list_item_1, nameList);
        lvDiseses.setAdapter(aa);
    }

}
