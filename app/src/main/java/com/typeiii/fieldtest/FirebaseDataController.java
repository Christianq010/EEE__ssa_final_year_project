package com.typeiii.fieldtest;

import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.typeiii.fieldtest.model.FieldData;

import java.sql.Timestamp;
import java.util.Date;

public class FirebaseDataController {
    private static final FirebaseDataController instance = new FirebaseDataController();

    //private constructor to avoid client applications to use constructor
    private FirebaseDataController(){}

    public static FirebaseDataController getInstance(){
        return instance;
    }

    private DatabaseReference mDatabase;

    public void saveDataToFirebaseDatabase(Location location, int mSignalStrength){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("database").push();

        FieldData fieldData = new FieldData();
        fieldData.setLatitude(location.getLatitude());
        fieldData.setLongitude(location.getLongitude());
        fieldData.setSignalStrength(mSignalStrength);
        fieldData.setTimestamp(getTimestamp());
        fieldData.setDeviceName(getDeviceName());
        mDatabase.setValue(fieldData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.v("TAG", "Success");
                        } else {
                            Log.v("TAG", "Error: " );
                        }
                    }
                });
    }

    private String getTimestamp() {
        Date date= new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts.toString();
    }

    private String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}


