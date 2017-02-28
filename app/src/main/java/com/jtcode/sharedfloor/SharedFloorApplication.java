package com.jtcode.sharedfloor;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jtcode.sharedfloor.interfaces.CustomConstants;


/**
 * Created by Hulk-lián
 */

public class SharedFloorApplication extends Application {
    private static Context context;

    public SharedFloorApplication(){
        super();
        context=this;
    }

    public static Context getContext(){return context;}

    @Override
    public void onTerminate() {
        SharedPreferences.Editor sp= PreferenceManager.getDefaultSharedPreferences(context).edit();
        sp.putLong(CustomConstants.TIMEINMILIS,System.currentTimeMillis());
        super.onTerminate();
    }
}
