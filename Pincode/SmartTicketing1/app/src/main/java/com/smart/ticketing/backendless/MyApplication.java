package com.smart.ticketing.backendless;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.backendless.Backendless;
import com.smart.ticketing.R;

import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

/**
 * Created by lohith on 11/4/15.
 */

@ReportsCrashes(formKey = "", // will not be used
        mailTo = "dayanand.shine@gmail.com",
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.app_name)
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {

//    Logger LOG = LoggerFactory.getLogger(MyApplication.class);

    @Override
    public void onCreate() {
        super.onCreate();


//        ACRA.init(this);

        Backendless.setUrl(Defaults.SERVER_URL);

//        Fabric.with(this, new Crashlytics());

//        LOG.info("oncreate myaplication....");

//        DaggerUtil.init(this);
//        Backendless.initApp(this, "6BC5674D-1286-3078-FF78-A322B6AEAC00", "199EFCBF-A78D-A88D-FF0D-6DB7C8212100", "v1");

        // travel package
//        Backendless.initApp(this, "47BF48A6-6523-A8AF-FF6D-8FE3A7761400", "5F87A182-566B-004D-FF4F-5559B0358100", "v1");

        // inventory
//        Backendless.initApp(this, "63372DBF-DCBA-0DF7-FF04-655B8F9E2800", "E41EBCAD-31DE-E624-FF11-C0357A9BBD00", "v1");


//         olx book
//        Backendless.initApp(this, "597FA66F-F2BB-CD49-FFCA-A6EF7B7E1500", "BC146DF9-5CFE-6193-FF56-63259B74E300", "v1");

        // complaints book
//        Backendless.initApp(this, "AC7863D9-DC28-D773-FF3E-E561453A1A00", "C0E353A9-F793-947E-FFAA-3B844C6C9400", "v1");

        // agrimart
//        Backendless.initApp(this, "F0FE2F01-9A9C-41D8-FF8B-9D4DC8546400", "1E69DC85-1043-B851-FF94-398196202D00", "v1");

        // Dictionary
//        Backendless.initApp(this, "550D8507-A5B1-E4C2-FF2E-DBDA16AAA100", "4CA7BF7A-8349-A77F-FFF8-FCF25A5D9E00", "v1");

        // BMTC
//        Backendless.initApp(this, "D414E46C-D1E3-E567-FF40-9648DB4E4600", "5CF62F1B-4019-310F-FFF3-2F858FEF8300", "v1");


        // qless old
//        Backendless.initApp(this, "7AC17C3D-58F9-BD84-FFA4-AB286F17B700", "79192CE8-F8E0-5A00-FFF9-473A7014DA00", "v1");


        // qless
//        Backendless.initApp(this, Defaults.APPLICATION_ID, Defaults.API_KEY);

//        Backendless.initApp(this, Defaults.APPLICATION_ID, Defaults.API_KEY);

        // bustracking
//        Backendless.initApp(this, "A4D5D0CC-FE4D-4166-FFB0-A37B8A88A400", "8E0F16DB-1002-6B5E-FF77-ACB7A5F3CA00");

        // voter
//        Backendless.initApp(this, "50CD1584-09E3-0C55-FF59-8324B5270800", "9815B418-B72A-376F-FFBC-EB704956C400");

        // medical
//        Backendless.initApp(this, "B6D1DDC5-43A9-F3FF-FF40-58DCE0CE9700", "13FA1BE6-6357-C117-FFC3-3755FD28B300");

        // agriculture
//        Backendless.initApp(this, "F175F540-7C68-2D24-FFDB-F3AD244FB500", "E98EFB18-0F4F-7D48-FFE3-16B3B92EC200");

//         Ration
//        Backendless.initApp(this, "2BD8113E-0ACE-99EF-FF7D-600A855F3000", "E7F496AF-2E23-F9E0-FFE9-8F2E08D73D00");


//        timetable
//        Backendless.initApp(this, "870647AB-C0A3-50D9-FF61-54DE50A7E600", "3A8C4FD6-F0DE-58A7-FF86-FD2256C6F500");


        // campus
//        Backendless.initApp(this, "0FA27FDD-2ACD-00F4-FF7E-CFF56C818200", "6C84C686-A761-28FE-FF45-51229BEBA400");

        // placements
//        Backendless.initApp(this, "167783B7-0E38-9056-FF15-3FC3820A9400", "059D3500-69D1-A8C4-FF5E-D49DC6F3EF00");


        // grocestore
//        Backendless.initApp(this, "9A32883B-BCDE-471D-FFC5-68ABB4652F00", "0F01955E-F4AE-3C99-FF68-617601E04E00");


        // Rare Blood Bank
//        Backendless.initApp(this, "8BF61081-EC04-AEC1-FFB6-9198DEB6A500", "50A3796A-DE79-F5CC-FF06-1C90A6BC6300");


        // KisanMitra
//        Backendless.initApp(this, "08BECB54-856F-6065-FFAC-93ECA17C5400", "F06A7EC2-D3DF-5F79-FFFE-D2AB4C8BEC00");

        // gym
//        Backendless.initApp(this, "BBDD3C77-FB39-A631-FFE1-CCD572A02F00", "BB17D477-9943-01DE-FF74-A4C269D6A400");

        // virtual exam
//        Backendless.initApp(this, "2FD0E6BD-8F76-B8A4-FFDE-7DD128547C00", "78974E35-B2FF-3073-FFFD-FBA788EB4500");

        // Attendance
//        Backendless.initApp(this, "60F4CF1E-8284-DE0C-FF23-C86BCE2BF700", "978464B1-82C4-B43D-FF75-7736EEFD3600");

        // OLX
//        Backendless.initApp(this, "D836A384-46A0-3D44-FF35-E4638A9D0500", "72805581-D7F8-65FA-FF7E-D30E92E2A400");

        // museum
//        Backendless.initApp(this, "3BCD9C4A-A89E-AC8A-FF3A-29545DD9FC00", "75BB7EF4-ED6F-36CF-FF71-6427BCADA500");

        // carpooling
//        Backendless.initApp(this, "C634BC61-05A3-0366-FF6F-76B801C1A100", "CE5FD712-B14D-5C2A-FF79-DE477A0F5C00");

        // Pincode
        Backendless.initApp(this, "9CF8FD11-09A8-92EA-FFB8-22083A4F2000", "DA132688-80BB-8408-FF0F-D0D4F9457D00");

        // chatbot
//        Backendless.initApp(this, "B9EC8EC7-DA84-6828-FF5E-01C9900E2E00", "2DF909E0-AA34-A7C5-FF5D-E7962B8A1100");

        // movie
//        Backendless.initApp(this, "8AE351E5-4A92-66CE-FFF4-A71A1E95E100", "E0AEAC2B-B2E9-994E-FF2E-A2A7E7709900");


        // Garbage
//        Backendless.initApp(this, "323A83C0-C53C-171D-FFB5-52B4EFA78300", "AD50754D-C920-F5A2-FFF8-2DE23A35D600");


    }

    @Override
    public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }


}
