package com.example.nguyephan.findshopspot.presentation.base;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import com.example.nguyephan.findshopspot.FindShopApplication;
import com.example.nguyephan.findshopspot.R;
import com.example.nguyephan.findshopspot.di.component.ActivityComponent;
import com.example.nguyephan.findshopspot.di.component.ApplicationComponent;
import com.example.nguyephan.findshopspot.di.component.DaggerActivityComponent;
import com.example.nguyephan.findshopspot.di.module.ActivityModule;
import com.example.nguyephan.findshopspot.ultis.common.CommonUtils;
import com.example.nguyephan.findshopspot.ultis.errorDialog.GpsDialogError;
import com.example.nguyephan.findshopspot.ultis.errorDialog.IntenetDialogError;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by nguye phan on 3/21/2018.
 */

public abstract class BaseActivity  extends AppCompatActivity implements BaseView {

    private static final String TAG = "BaseActivity";
    private static final String INTERNET_DIALOG_TAG = "internet_dialog_error";
    private static final String GPS_DIALOG_TAG = "gps_dialog_error";
    private ActivityComponent mActivityComponent;
    private ProgressDialog mProgressDialog;

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
        hideLoadIndicatorConnection();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoadIndicatorConnection() {
        if(mProgressDialog == null){
            return;
        }
        if(mProgressDialog.isShowing()){
            mProgressDialog.cancel();
        }
    }

    @Override
    public boolean isInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null;
    }

    @Override
    public void errorInternetConnection() {
        android.support.v4.app.FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        IntenetDialogError dialogError = IntenetDialogError.newInstance(INTERNET_DIALOG_TAG);
        dialogError.show(fragmentTransaction,INTERNET_DIALOG_TAG);
        dialogError.setCancelable(false);
    }

    @Override
    public boolean isGpsConnection() {
        LocationManager gps = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return gps != null && gps.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void errorGpsConnection() {
        android.support.v4.app.FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        GpsDialogError dialogError = GpsDialogError.newInstance();
        dialogError.show(fragmentTransaction,GPS_DIALOG_TAG);
        dialogError.setCancelable(false);
    }

    @Override
    public void hideKeyboard() {
        View view = getCurrentFocus();
        InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (in != null) {
            in.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    @Override
    public void showKeyBoard() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
