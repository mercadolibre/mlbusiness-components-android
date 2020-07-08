package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.annotation.SuppressLint;
import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Main Application class that extends from Application to execute the start method only once.
 */
public class MainApplication extends Application {

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
