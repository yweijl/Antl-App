package com.avansprojects.antl;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class AntlApp extends Application {

    public static AntlApp shared_preference;

    /*On Create Application Create AppPreferences*/
    @Override
    public void onCreate() {
        Log.i(this.toString(), "Reached on Create()");
        super.onCreate();
        shared_preference = this;
        Log.i(this.toString(), shared_preference.toString());
    }

    public static AntlApp getContext() {
        return shared_preference;
    }

}

