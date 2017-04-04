package com.example.torpe.followyou3;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    final Config config = new Config();
    final String userId = config.getUserId();
    protected ServerCommunication serverCommunication = new ServerCommunication(MainActivity.this, this.userId);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.checkPremmisions();
        TrackGPS gps = new TrackGPS(MainActivity.this);
        Log.d("Longitude", new Double(gps.getLongitude()).toString());
        Log.d("Latitude", new Double(gps.getLatitude()).toString());
        Button btnGetData = (Button) findViewById(R.id.btnGetData);
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                TrackGPS gps = new TrackGPS(MainActivity.this);
                Log.d("Longitude", new Double(gps.getLongitude()).toString());
                Log.d("Latitude", new Double(gps.getLatitude()).toString());
                if(serverCommunication.isConnected()){
                    Log.d("isConnected", "Igen");
                }
                else {
                    Log.d("isConnected", "Nem");
                }
                /*new Thread(new Runnable() {
                    public void run() {
                        ServerCommunication serverCommunication = new ServerCommunication(MainActivity.this, "ia95ir");
                        String szoveg = serverCommunication.sendLocationData();
                        Log.d("itt", szoveg);
                    }
                }).start();*/
            }
        });
    }

    private void checkPremmisions() {
        // the only way we insert the dummy contact if if we are below M.
        // Else we continue on and prompt the user for permissions

        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("GPS");
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("Storage");
        if (!addPermission(permissionsList, Manifest.permission.INTERNET))
            permissionsNeeded.add("INTERNET");
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_NETWORK_STATE))
            permissionsNeeded.add("INTERNET STÁTUSZ");
        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                            }
                        });
                return;
            }
            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!shouldShowRequestPermissionRationale(permission)) return false;
        }
        return true;
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
