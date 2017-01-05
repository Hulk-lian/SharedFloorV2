package com.jtcode.sharedfloor;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jtcode.sharedfloor.interfaces.CustomConstants;

import java.util.Map;

public class PreferencesIO {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public PreferencesIO(Context context) {
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        editor= sharedPreferences.edit();
    }

    public void writeUserPassword(String user, String pass){
        editor.putString(CustomConstants.USER,user);
        editor.putString(CustomConstants.PASS,pass);
    }
    public void removeUserPassword(){
        editor.remove(CustomConstants.USER);
        editor.remove(CustomConstants.PASS);
    }

    public String readDataByID(String dataID){
        String dataReaded=null;
        String datakey=null;

        switch (dataID) {
            case CustomConstants.USER:
                datakey=CustomConstants.USER;
                break;
            case CustomConstants.PASS:
                datakey=CustomConstants.PASS;
                break;
        }
        dataReaded=sharedPreferences.getString(datakey,null);

        return dataReaded;
    }
}
