package com.example.nguyephan.findshopspot.presentation.register;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.nguyephan.findshopspot.data.pojo.User;
import com.example.nguyephan.findshopspot.data.respository.Manager.RepositoryManager;
import com.example.nguyephan.findshopspot.presentation.base.AppBasePresenter;
import com.example.nguyephan.findshopspot.ultis.scheduler.BaseSchedulerProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import static android.content.ContentValues.TAG;

/**
 * Created by nguye phan on 3/26/2018.
 */

public class RegisterPresenter<V extends RegisterFragmentContact.View> extends AppBasePresenter<V>
        implements RegisterFragmentContact.Presenter<V> {

    @Inject
    public RegisterPresenter(@NonNull CompositeDisposable compositeDisposable,
                             @NonNull BaseSchedulerProvider baseSchedulerProvider,
                             @NonNull RepositoryManager repositoryManager) {
        super(compositeDisposable, baseSchedulerProvider, repositoryManager);
    }

    @Override
    public void startRegister() {
        getView().createNewAccount();
    }

    @Override
    public void saveUserToCache(User user) {
        Log.e(TAG,"save user to cache");
    }

}
