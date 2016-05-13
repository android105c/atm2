package com.etone.atm.Utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Maggie on 2016/5/13.
 */
public class MyApplication extends Application {
    private static Context context = null;

    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
