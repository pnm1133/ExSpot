package com.example.nguyephan.findshopspot.presentation.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.example.nguyephan.findshopspot.data.pojo.User;
import com.example.nguyephan.findshopspot.data.respository.Manager.RepositoryManager;
import com.example.nguyephan.findshopspot.presentation.base.AppBasePresenter;
import com.example.nguyephan.findshopspot.presentation.base.BasePresenter;
import com.example.nguyephan.findshopspot.ultis.scheduler.BaseSchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by nguye phan on 3/24/2018.
 */

public class LoginPresenter<V extends LoginContact.View> extends AppBasePresenter<V> implements LoginContact.Presenter<V> {
    private static final String TAG = "LoginPresenter";
    private RepositoryManager repositoryManager;

    @Inject
    public LoginPresenter(@NonNull CompositeDisposable compositeDisposable,
                          @NonNull BaseSchedulerProvider baseSchedulerProvider,
                          @NonNull RepositoryManager repositoryManager) {
        super(compositeDisposable, baseSchedulerProvider, repositoryManager);
        this.repositoryManager = repositoryManager;
    }

    @Override
    public void userCacheLogin() {
//        if (repositoryManager.getUserFromCache() == null){
//            getView().showNothingUser();
//        }else {
//            getView().showEmailAndPassWordCache();
//        }

        addDisposible(Observable
                .just(repositoryManager.getUserFromCache())
                .subscribeOn(io())
                .observeOn(main())
                .subscribe(user -> {
                    if(TextUtils.isEmpty(user.getName())){
                        getView().showNothingUser();
                    }else {
                        getView().showEmailAndPassWordCache();
                    }
        }));
    }
}
