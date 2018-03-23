package com.example.nguyephan.findshopspot.presentation.base;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nguyephan.findshopspot.FindShopApplication;
import com.example.nguyephan.findshopspot.R;
import com.example.nguyephan.findshopspot.di.component.ActivityComponent;
import com.example.nguyephan.findshopspot.di.component.ApplicationComponent;
import com.example.nguyephan.findshopspot.di.component.DaggerActivityComponent;
import com.example.nguyephan.findshopspot.di.module.ActivityModule;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by nguye phan on 3/21/2018.
 */

public abstract class BaseActivity  extends AppCompatActivity implements BaseView {

    private static final String TAG = "BaseActivity";

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent applicationComponent = ((FindShopApplication)getApplication()).getApplicationComponent();
        mActivityComponent  = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(applicationComponent)
                .build();
    }

    public ActivityComponent getActivityComponent(){
        return mActivityComponent;
    }

    @Override
    public void loadIndicatorConnection() {

    }

    @Override
    public boolean isInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null;
    }

    @Override
    public void errorInternetConnection() {

    }

    @Override
    public boolean isGpsConnection() {
        LocationManager gps = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return gps != null && gps.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void errorGpsConnection() {

    }

    @Override
    public void hideKeyboard() {

    }

    @Override
    public void showKeyBoard() {

    }
}
