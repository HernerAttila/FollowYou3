package com.example.torpe.followyou3;

import android.content.Context;

/**
 * Created by Torpe on 2017. 04. 02..
 */

public class Config {
    protected boolean LteEnabled;
    protected int pufferMaxSize;
    protected String userId;

    public void Config(Context context){
        this.LteEnabled = context.getResources().getBoolean(R.bool.LteEnabled);
        this.pufferMaxSize = context.getResources().getInteger(R.integer.pufferMaxSize);
        this.userId = context.getResources().getString(R.string.userId);
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
