package com.example.torpe.followyou3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGetData = (Button) findViewById(R.id.btnGetData);
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new Thread(new Runnable() {
                    public void run() {
                        ServerCommunication serverCommunication = new ServerCommunication(MainActivity.this, "ia95ir");
                        String szoveg = serverCommunication.sendLocationData();
                        Log.d("itt", szoveg);
                    }
                }).start();
            }
        });
    }
}
