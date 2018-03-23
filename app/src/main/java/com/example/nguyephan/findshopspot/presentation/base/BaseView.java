package com.example.nguyephan.findshopspot.presentation.base;

import android.app.Activity;

/**
 * Created by nguye phan on 3/21/2018.
 */

public interface BaseView {

    void loadIndicatorConnection();

    boolean isInternetConnection();

    void errorInternetConnection();

    boolean isGpsConnection();

    void errorGpsConnection();

    void hideKeyboard();

    void showKeyBoard();

}
