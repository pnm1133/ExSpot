package com.example.nguyephan.findshopspot.presentation.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.nguyephan.findshopspot.di.component.ActivityComponent;

/**
 * Created by nguye phan on 3/21/2018.
 */

public class BaseFragment extends Fragment implements BaseView{

    private BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BaseActivity){
            mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void loadIndicatorConnection() {
        if(mActivity == null){
            return;
        }
        mActivity.loadIndicatorConnection();
    }

    @Override
    public void hideLoadIndicatorConnection() {
        if(mActivity == null){
            return;
        }
        mActivity.hideLoadIndicatorConnection();
    }

    @Override
    public boolean isInternetConnection() {
        if (mActivity == null){
            return false;
        }
        return mActivity.isInternetConnection();
    }

    @Override
    public void errorInternetConnection() {
        if(mActivity == null){
            return;
        }
        mActivity.errorInternetConnection();
    }

    @Override
    public boolean isGpsConnection() {
        if (mActivity == null){
            return false;
        }
        return mActivity.isGpsConnection();
    }

    @Override
    public void errorGpsConnection() {
        if(mActivity == null){
            return;
        }
        mActivity.errorGpsConnection();
    }

    @Override
    public void hideKeyboard() {
        if(mActivity == null){
            return;
        }
        mActivity.hideKeyboard();
    }

    @Override
    public void showKeyBoard() {

    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }
}
