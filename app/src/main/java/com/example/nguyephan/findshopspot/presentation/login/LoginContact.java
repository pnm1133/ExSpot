package com.example.nguyephan.findshopspot.presentation.login;

import com.example.nguyephan.findshopspot.di.PerActivity;
import com.example.nguyephan.findshopspot.presentation.base.AppBasePresenter;
import com.example.nguyephan.findshopspot.presentation.base.BasePresenter;
import com.example.nguyephan.findshopspot.presentation.base.BaseView;

/**
 * Created by nguye phan on 3/24/2018.
 */

public interface LoginContact {

    interface View extends BaseView {

        void showEmailAndPassWordCache();

        void showNothingUser();

    }

    @PerActivity
    interface Presenter<V extends BaseView> extends BasePresenter<V>{

        void userCacheLogin();


    }
}
