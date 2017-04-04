package com.example.torpe.followyou3;

import android.content.Context;
import android.util.Log;

/**
 * Created by Torpe on 2017. 04. 04..
 */

public class ConfigThread implements Runnable {
    protected Context context;
    protected String userId;
    public ConfigThread(Context context,String userId) {
        this.context = context;
        this.userId = userId;
    }

    public void run() {
        Log.d("start",this.userId);
        ServerCommunication serverCommunication = new ServerCommunication(this.context,this.userId);
        String result = serverCommunication.getConfigData();
        Throwable stack = new Throwable().fillInStackTrace();
        StackTraceElement[] trace = stack.getStackTrace();
        Log.d(trace[0].getClassName(),result);
    }
}