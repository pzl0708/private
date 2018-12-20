package com.example.pzl.wanandroid.app;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    public static MyApp sMyApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp = this;
    }

    public static MyApp getApplication(){
        return sMyApp;
    }

    public static Context getContext(){
        return sMyApp.getApplicationContext();
    }
}
