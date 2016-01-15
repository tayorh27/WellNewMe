package com.wnm.wellnewme;

import android.app.Application;
import android.content.Context;

import com.wnm.wellnewme.SqliteDatabase.WellNewMeLocalDb;

/**
 * Created by tayo on 11/9/2015.
 */
public class MyApplication extends Application {
    private static MyApplication sInstance;
    private static WellNewMeLocalDb wellNewMeLocalDb;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }

    public synchronized static WellNewMeLocalDb getWriteAbleDb(){
        if(wellNewMeLocalDb == null){
            wellNewMeLocalDb = new WellNewMeLocalDb(getAppContext());
        }
        return wellNewMeLocalDb;
    }
}
