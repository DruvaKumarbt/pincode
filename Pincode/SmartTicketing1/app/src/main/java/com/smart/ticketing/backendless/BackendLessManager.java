package com.smart.ticketing.backendless;

import android.content.Context;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.backendless.persistence.DataQueryBuilder;
import com.smart.ticketing.backendless.data.Pass;
import com.smart.ticketing.backendless.data.User;
import com.smart.ticketing.backendless.data.Wallet;


import java.io.File;
import java.util.List;

/**
 * Created by dayanand on 15/03/17.
 */

public class BackendLessManager {

    private static final String TAG = "BackendLessManager";

    Context mContext;

    public BackendLessManager(Context context) {
        this.mContext = context;
    }

    public static void insertUser(User user, final OnBackendLessResponseListener listener) {
        Backendless.Persistence.save(user, new AsyncCallback<User>() {
            @Override
            public void handleResponse(User response) {
                Log.i(TAG, "insert user: " + response);
                listener.onBackendResponse(response, true);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.i(TAG, " " + fault);
                listener.onBackendResponse(fault, false);
            }
        });
    }


    public static void userLogin(String username, String password, final OnBackendLessResponseListener listener) {

        IDataStore<User> querydata = Backendless.Data.of(User.class);

        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause("username='" + username + "' and password='" + password + "'");


        querydata.find(query, new AsyncCallback<List<User>>() {
            @Override
            public void handleResponse(List<User> response) {
                listener.onBackendResponse(response, true);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG, "error in backend less: "+fault);
                listener.onBackendResponse(fault, false);
            }
        });

    }




    public static void insertPass(Pass pass, final OnBackendLessResponseListener listener) {
        Backendless.Persistence.save(pass, new AsyncCallback<Pass>() {
            @Override
            public void handleResponse(Pass response) {
                Log.i(TAG, "insert user: " + response);
                listener.onBackendResponse(response, true);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.i(TAG, " " + fault);
                listener.onBackendResponse(fault, false);
            }
        });
    }

    public static void fetchPass(String username, final OnBackendLessResponseListener listener) {

        IDataStore<Pass> dataStore = Backendless.Data.of(Pass.class);

        DataQueryBuilder query = DataQueryBuilder.create();

        query.setWhereClause("username='" + username + "'");


        dataStore.find(query, new AsyncCallback<List<Pass>>(){
            @Override
            public void handleResponse(List<Pass> leadsBackendlessCollection) {
                listener.onBackendResponse(leadsBackendlessCollection, true);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.d(TAG, "Backendlessfault: " + backendlessFault);
                listener.onBackendResponse(backendlessFault, false);
            }
        });
    }


    public static void fetchWallet(String cardno, String pin, final OnBackendLessResponseListener listener) {

        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause("cardNo='" + cardno + "' and pin='"+pin +"'");

        Backendless.Persistence.of(Wallet.class).find(query, new AsyncCallback<List<Wallet>>(){
            @Override
            public void handleResponse(List<Wallet> leadsBackendlessCollection) {
                listener.onBackendResponse(leadsBackendlessCollection, true);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.d(TAG, "Backendlessfault: " + backendlessFault);
                listener.onBackendResponse(backendlessFault, false);
            }
        });
    }




    public void checkForExistingUser(String username, final OnBackendLessResponseListener listener) {


        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause("username='" + username + "'");


        Backendless.Persistence.of(User.class).find(query, new AsyncCallback<List<User>>() {
            @Override
            public void handleResponse(List<User> leadsBackendlessCollection) {
                listener.onBackendResponse(leadsBackendlessCollection, true);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.d(TAG, " " + backendlessFault);
                listener.onBackendResponse(backendlessFault, false);
            }
        });
    }


    public interface OnBackendLessResponseListener {

        public void onBackendResponse(Object object, boolean status);
    }


    public static void uploadFileAsync(String filepath, final OnBackendLessResponseListener listener) {
        final File file = new File(filepath);

        // now upload the file
        Backendless.Files.upload(file, "/myfiles", new AsyncCallback<BackendlessFile>() {
            @Override
            public void handleResponse(BackendlessFile uploadedFile) {

                listener.onBackendResponse(uploadedFile, true);
                System.out.println("File has been uploaded. File URL is - " + uploadedFile.getFileURL());
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                System.out.println("Server reported an error - " + backendlessFault.getMessage());
                listener.onBackendResponse(backendlessFault, false);
            }
        });
    }




}
