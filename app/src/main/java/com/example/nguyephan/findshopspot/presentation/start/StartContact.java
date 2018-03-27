package com.example.nguyephan.findshopspot.presentation.start;

import com.example.nguyephan.findshopspot.di.PerActivity;
import com.example.nguyephan.findshopspot.presentation.base.BasePresenter;
import com.example.nguyephan.findshopspot.presentation.base.BaseView;

/**
 * Created by nguye phan on 3/21/2018.
 */

public interface StartContact {

    interface View extends BaseView {

        void replaceLoginPage();

        void replaceHomePage();
    }

    @PerActivity
    interface Presenter<V extends BaseView> extends BasePresenter<V> {

        void startLogin();

    }
}
