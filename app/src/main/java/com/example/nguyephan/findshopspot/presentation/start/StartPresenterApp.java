package com.example.nguyephan.findshopspot.presentation.start;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.nguyephan.findshopspot.data.respository.Manager.RepositoryManager;
import com.example.nguyephan.findshopspot.presentation.base.AppBasePresenter;
import com.example.nguyephan.findshopspot.ultis.common.CommonUtils;
import com.example.nguyephan.findshopspot.ultis.scheduler.BaseSchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by nguye phan on 3/21/2018.
 */

public class StartPresenterApp<V extends StartContact.View> extends AppBasePresenter<V> implements StartContact.Presenter<V> {
    private static final String TAG = "StartPresenterApp";

    private CompositeDisposable compositeDisposable;
    private BaseSchedulerProvider baseSchedulerProvider;
    private RepositoryManager mRepositoryManager;

    @Inject
    public StartPresenterApp(@NonNull CompositeDisposable compositeDisposable,
                             @NonNull BaseSchedulerProvider baseSchedulerProvider,
                             @NonNull RepositoryManager repositoryManager) {
        super(compositeDisposable, baseSchedulerProvider,repositoryManager);
        this.mRepositoryManager  = repositoryManager;
    }

    @Override
    public void startLogin() {
        addDisposible(mRepositoryManager.startLoginToCache()
                .subscribeOn(io())
                .observeOn(main())
                .subscribe(response -> {
                    if (response.getStatus().contains(CommonUtils.STATUS_RESPONSE_OK)) {
                        getView().replaceHomePage();
                    } else if (response.getStatus().contains(CommonUtils.STATUS_RESPONSE_FAULT)){
                        getView().replaceLoginPage();
                    }else {
                        //
                    }
                }, throwable -> Log.e(TAG, "error :"+throwable.getMessage())));
    }
}
