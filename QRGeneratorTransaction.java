package com.smart.ticketing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.smart.ticketing.backendless.LoginActivity;
import com.smart.ticketing.backendless.data.Pass;
import com.smart.ticketing.bmtc.RenewalPasssActivity;
import com.smart.ticketing.globalnest.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class QRGeneratorTransaction extends Activity {

    String userId;
    ImageView imageView;
    Bitmap bitmap;
    String message = "";
    TextView textView;
    Button btnRenewal;
    Pass pass;
    static String yourId;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcodetransaction);

        imageView = (ImageView) findViewById(R.id.qrcodeview);
        textView = (TextView) findViewById(R.id.textview);
        btnRenewal = (Button) findViewById(R.id.renewal);

        yourId = getIntent().getStringExtra("id");

        if (yourId == null) {
            Random ran = new Random();
            int num = ran.nextInt(1000000000);
            yourId = num + "";
        }


        name = getIntent().getStringExtra("name");
        if (name == null) {
            name = LoginActivity.username;
        }

        CreateQRCode(yourId);
        textView.setText("Your unique no: " + yourId);

//        imageView.setImageBitmap(bitmap);


       /* BackendLessManager.fetchPass(LoginActivity.username, new BackendLessManager.OnBackendLessResponseListener() {
            @Override
            public void onBackendResponse(Object object, boolean status) {
                if(status){
                    List<Pass> passList = ((List<Pass>) object);
                    if(passList.size() == 0){
                        Utils.showToast(getApplicationContext(), "No pass availble");
                        btnRenewal.setVisibility(View.GONE);
                        return;
                    }
                    pass = passList.get(0);
                    if(!pass.getActive()){
                        Utils.showToast(getApplicationContext(), "Pass is not activated");
                        btnRenewal.setVisibility(View.GONE);
                        return;
                    }
                    message = pass.getUsername() +"\nName: "+pass.getName() + "\nCollege: "+pass.getCollege() +"\nFrom: "+pass.getFrom()
                            +"\nVia: "+pass.getVia() + "\nTo: "+pass.getTo() + "\nValidity: " + pass.getValidity() +"\n"+pass.getPhotoUrl();
                    textView.setText(message);
                    bitmap = CreateQRCode(message);
                    imageView.setImageBitmap(bitmap);

                }else{

                }
            }
        });*/

        btnRenewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QRGeneratorTransaction.this, RenewalPasssActivity.class);
                intent.putExtra("pass", pass);
                startActivity(intent);
            }
        });

    }


    public Bitmap CreateQRCode(String otpQR) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(otpQR, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            imageView.setImageBitmap(bmp);
            SaveImage(bmp);
            return bmp;


        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }

    }

    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/pincode");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);

        String fname = "QR-" + name + "-" + yourId + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
