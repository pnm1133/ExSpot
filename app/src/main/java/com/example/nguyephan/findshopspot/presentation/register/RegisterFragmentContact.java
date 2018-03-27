package com.example.nguyephan.findshopspot.presentation.register;

import android.support.annotation.Nullable;
import android.view.View;

import com.example.nguyephan.findshopspot.data.pojo.User;
import com.example.nguyephan.findshopspot.di.PerActivity;
import com.example.nguyephan.findshopspot.presentation.base.BasePresenter;
import com.example.nguyephan.findshopspot.presentation.base.BaseView;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * Created by nguye phan on 3/26/2018.
 */

public interface RegisterFragmentContact {

    interface View extends BaseView {

        void replaceLogin();

        void showError(@Nullable String error,@Nullable Task<AuthResult> task);

        void createNewAccount();

        void updateUser();

        void hideKeyBoard(android.view.View v);

    }

    @PerActivity
    interface Presenter<V extends BaseView> extends BasePresenter<V>{

        void startRegister();

        void saveUserToCache(User user);
    }
}
