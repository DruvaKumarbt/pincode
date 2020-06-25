package com.smart.ticketing;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, 60000).show();
    }

    public static String assignEmpty(String str) {
        /*if (str == null || str.equalsIgnoreCase("null")) {
            return "";
        }*/
        return str;
    }



    public static void setHardwarePhone(Context context, String time){
        SharedPreferences sharedPreferences = context.getSharedPreferences("SIREN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone", time);
        editor.commit();
    }

    public static String getHardwarePhone(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("SIREN", Context.MODE_PRIVATE);
        return sharedPreferences.getString("phone", "");
    }



    public static void setAllTime(Context context, String time){
        SharedPreferences sharedPreferences = context.getSharedPreferences("SIREN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("time", time);
        editor.commit();
    }

    public static String getAllTime(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("SIREN", Context.MODE_PRIVATE);
        return sharedPreferences.getString("time", "");
    }

    public static void saveUserCredentials(Context context, String username, String password){
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }

    public static String getUsername(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        return sharedPreferences.getString("username", null);
    }

    public static String getPasswrod(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        return sharedPreferences.getString("password", null);
    }


    public static void print(String message) {
        System.out.println(message);
    }



    public static void showAlert(final Context context, final String message){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }

        builder.setTitle("Info")
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete


                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void displayDatepicker(Context context, final OnDateChosenListener listener){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, monthOfYear);
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        Log.i("CALENDAR", "Day: "+cal.get(Calendar.DAY_OF_MONTH) +" Month: "+cal.get(Calendar.MONTH));
                        listener.onDateChange(cal);


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public interface OnDateChosenListener{
        public void onDateChange(Calendar date);
    }

}
