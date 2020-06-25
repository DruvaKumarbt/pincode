package com.smart.ticketing.scanner;

import android.app.AlertDialog;
import android.app.LocalActivityManager;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.smart.ticketing.R;
import com.smart.ticketing.Utils;
import com.smart.ticketing.globalnest.result.ResultHandler;
import com.smart.ticketing.globalnest.result.ResultHandlerFactory;
import com.smart.ticketing.pincode.AddressInfoActivity;


import java.io.FileOutputStream;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

public class CaptureActivity extends DecoderActivity {

    private static final String TAG = CaptureActivity.class.getSimpleName();
    private static final Set<ResultMetadataType> DISPLAYABLE_METADATA_TYPES = EnumSet
            .of(ResultMetadataType.ISSUE_NUMBER,
                    ResultMetadataType.SUGGESTED_PRICE,
                    ResultMetadataType.ERROR_CORRECTION_LEVEL,
                    ResultMetadataType.POSSIBLE_COUNTRY);
    protected static LocalActivityManager mLocalActivityManager;
    private static final String LOG_TAG = null;
    String name;
    boolean e;
    private int myYear, myMonth, myDay;
    static final int ID_DATEPICKER = 0;
    private TextView statusView = null;
    TextView boothfirstname = null;
    private View resultView = null;
    private boolean inScanMode = false;
    static String seshu;
    String resp = null;
    private boolean playBeep;
    static ProgressDialog progressDialog;

    static String scanedQR;
    final Context context = this;
    ToggleButton toggleButton;
    EditText myDialogfname, myDialoglname, myDialogcompany, myDialogemail,
            myDialognotes, myDialogphone, myDialogtitle, myDialogdate;

    Button btnViewCart;

    static android.location.Location startLocation, endLocation;
    /**
     * When the beep has finished playing, rewind to queue up another one.
     */

    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.capture);
        // insertdataindb("dklskl");

        writeToFile();
//		boothfirstname = (TextView) findViewById(R.id.booth_firstname);
//		
//		boothfirstname.setText("Welcome "+global.getMyBoothFirstName().toString()+"\n"+"Center and Hold the QR Code to Scan");

		/*
         * resp = postPost(""); System.out.println(""+postPost(""));
		 * Log.v(LOG_TAG, "  responce for SignIn= " + resp);
		 */

        Log.v(TAG, "onCreate()");
        resultView = findViewById(R.id.result_view);
        statusView = (TextView) findViewById(R.id.status_view);
        btnViewCart = (Button) findViewById(R.id.viewcart);

//        btnViewCart.setVisibility(View.GONE);
        /*
         *
		 * // cancel button Button cancel = (Button) findViewById(R.id.cancel);
		 * cancel.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub try { Log.v(TAG, "onClick()"); inScanMode = false;
		 * super.finalize();
		 * 
		 * } catch (Throwable e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } });
		 */
        inScanMode = false;

        btnViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(CaptureActivity.this, ViewCartActivity.class);
//                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause()");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (inScanMode)
                finish();
            else
                onResume();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void handleDecode(Result rawResult, Bitmap barcode) {
        drawResultPoints(barcode, rawResult);

        ResultHandler resultHandler = ResultHandlerFactory.makeResultHandler(
                this, rawResult);
        handleDecodeInternally(rawResult, resultHandler, barcode);
    }

    protected void showScanner() {
        inScanMode = true;
        resultView.setVisibility(View.GONE);
        statusView.setText(R.string.msg_default_status);
        statusView.setVisibility(View.VISIBLE);
        viewfinderView.setVisibility(View.VISIBLE);
    }

    protected void showResults() {
        inScanMode = false;
        statusView.setVisibility(View.GONE);
        viewfinderView.setVisibility(View.GONE);
        resultView.setVisibility(View.VISIBLE);
    }

    // Put up our own UI for how to handle the decodBarcodeFormated contents.
    private void handleDecodeInternally(Result rawResult,
                                        ResultHandler resultHandler, Bitmap barcode) {
        onPause();
//        showResults();

        CharSequence displayContents = resultHandler.getDisplayContents();
        String QRContent = displayContents.toString();

        Toast.makeText(getApplicationContext(), "" + QRContent, Toast.LENGTH_LONG).show();


        // pincode
        Intent intent = new Intent(CaptureActivity.this, AddressInfoActivity.class);
        intent.putExtra("id", QRContent);
        startActivity(intent);



        // museum speech text
        /*Intent intent = new Intent(CaptureActivity.this, com.smart.ticketing.museum.MyAlarmService.class);
        intent.putExtra("text", QRContent);
        startService(intent);*/


        // mobitravel
        /*Intent intent = new Intent(CaptureActivity.this, com.smart.ticketing.museum.TextActivity.class);
        intent.putExtra("id", QRContent);
        startActivity(intent);*/

        /*Intent intent = new Intent(CaptureActivity.this, OffersActivity.class);
        intent.putExtra("id", QRContent);
        startActivity(intent);*/

        /*Intent intent = new Intent(CaptureActivity.this, TextActivity.class);
        intent.putExtra("id", QRContent);
        startActivity(intent);*/

        // qless old
        /*Intent intent = new Intent(CaptureActivity.this, CheckProductActivity.class);
        intent.putExtra("id", QRContent);
        startActivity(intent);*/

        // uncomment for parking QR
//        updateDatabse(QRContent);

        // alertDialog(QRContent);

        // uncomment for bmtc
        /*Intent intent = new Intent(CaptureActivity.this, ViewQRCodeContentActivity.class);
        intent.putExtra("msg", QRContent);
        startActivity(intent);*/


        /*new LocationHandler(this).initLocation(new LocationHandler.OnLocationChanged() {
            @Override
            public void onLocationAvailable(android.location.Location location) {
                if(startLocation == null){
                    startLocation = location;
                    Utils.showToast(getApplicationContext(), "Start location saved");
                }else{
                    endLocation = location;
                    Utils.showToast(getApplicationContext(), "end location saved");

                    float dist = startLocation.distanceTo(endLocation);

                    int amount = (int) (dist * ViewBooksActivity.amount);

                    String data = "You have travelled distance of "+dist + " and charged of "+amount;


                    Intent intent = new Intent(CaptureActivity.this, ViewAmountActivity.class);
                    intent.putExtra("data", data);
                    startActivity(intent);
                }
            }
        });*/
    }




    public String getSpacingCount(String content){
        int contentlen = content.length();
        int spaces = contentlen - 18 ;
        for(int i=0;i<=spaces;i++){
            content = content + " ";
        }
       content =  content + " ";
        return content;
    }
    public void writeToFile(){

        String data = "";

        SharedPreferences preferences = getSharedPreferences("parking", MODE_PRIVATE);

        if(preferences.getBoolean("heading", false)){
            return;
        }

        data = "ID\t\tCheckinTime\t\t\t\t\tCheckoutTime\t\t\t\t\tCost";
        try {
            FileOutputStream fos = new FileOutputStream("/sdcard/time.txt", true);
            fos.write(data.getBytes());
            fos.close();
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("heading", true);
            edit.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String data){
        try {
            FileOutputStream fos = new FileOutputStream("/sdcard/time.txt", true);
            fos.write(data.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private class GetDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            if (isOnline()) {
                try {
                    Thread.sleep(0);
                    //resp = postPost("");
                    System.out.println(" nearestPlaces responceforVerify= " + resp);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
            return resp;
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            super.onPostExecute(result);
            if (result != null) {
            } else {
                alertDialog("please scan again");
            }
        }
    }

    public static String convertURL(String str) {

        String url = null;
        try {
            url = new String(str.trim().replace(" ", "%20"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    private void alertDialog(String string) {
        // TODO Auto-generated method stub
        AlertDialog.Builder d1 = new AlertDialog.Builder(getParent());
        d1.setMessage(string);
        d1.setTitle("Warning");
        d1.setPositiveButton("ok", null);
        d1.setCancelable(true);
        d1.create().show();
    }

    private Boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnected())
            return true;

        return false;
    }

    @Override
    public void onBackPressed() {

        return;
    }

	/*
	 * myDialogfname.getText().toString(), myDialoglname.getText().toString(),
	 * myDialogcompany .getText().toString(), myDialogemail
	 * .getText().toString(), myDialognotes .getText().toString(), myDialogphone
	 * .getText().toString()
	 */
    // Validation for all the fields

}
