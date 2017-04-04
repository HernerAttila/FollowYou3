package com.example.torpe.followyou3;

import android.content.Context;
import android.util.Log;


/**
 * Created by Torpe on 2017. 04. 02..
 */

public class Config {
    private Context mContext;
    protected boolean LteEnabled;
    protected int pufferMaxSize;
    protected String userId;


    public Config(Context context){
        this.mContext = context;
        this.LteEnabled = this.mContext.getResources().getBoolean(R.bool.LteEnabled);
        this.pufferMaxSize = this.mContext.getResources().getInteger(R.integer.pufferMaxSize);
        this.userId = this.mContext.getResources().getString(R.string.userId);
    }

    public void setNewConfig(){
        Runnable r = new ConfigThread(this.mContext,this.userId);
        new Thread(r).start();;
    }

    public boolean isLteEnabled() {
        return LteEnabled;
    }

    public int getPufferMaxSize() {
        return pufferMaxSize;
    }

    public String getUserId() {
        return userId;
    }
}