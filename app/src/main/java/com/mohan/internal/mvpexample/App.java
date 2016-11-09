package com.mohan.internal.mvpexample;

import android.app.Application;
import android.content.Context;

import com.mohan.internal.mvpexample.model.Dbclass;

/**
 * Created by mohan on 6/10/16.
 */

public class App extends Application {

    private static Dbclass db;
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext=this;
    }

    public static Context getAppContext() {
        return appContext;
    }
    public static synchronized Dbclass getDb() {
        if (db == null) {
            db = Dbclass.getInstance(getAppContext());
        }
        return db;
    }
}
