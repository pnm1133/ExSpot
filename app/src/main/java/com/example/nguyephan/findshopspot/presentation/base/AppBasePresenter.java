package com.example.nguyephan.findshopspot.presentation.base;

import android.support.annotation.NonNull;

import com.example.nguyephan.findshopspot.data.respository.Manager.RepositoryManager;
import com.example.nguyephan.findshopspot.ultis.scheduler.BaseSchedulerProvider;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by nguye phan on 3/21/2018.
 */

public class AppBasePresenter<V extends BaseView>  implements BasePresenter<V> {

    private V v;
    private BaseSchedulerProvider mSchedulerProvider;
    private CompositeDisposable compositeDisposable;

    public AppBasePresenter(@NonNull CompositeDisposable compositeDisposable,
                            @NonNull BaseSchedulerProvider baseSchedulerProvider,
                            @NonNull RepositoryManager repositoryManager){
        this.mSchedulerProvider = baseSchedulerProvider;
        this.compositeDisposable = compositeDisposable;
    }

       @Override
    public void onAttach(V v) {
        this.v = v;
    }

    @Override
    public void onDetach() {
        v = null;
        compositeDisposable.dispose();
    }

    @Override
    public boolean isAttachView() {
        return v!= null;
    }

    public V getView(){
        return v;
    }

    public Scheduler io(){
        return mSchedulerProvider.io();
    }

    public Scheduler main(){
        return mSchedulerProvider.main();
    }

    public Scheduler compound(){
        return mSchedulerProvider.compound();
    }

    public void addDisposible(Disposable disposable){
        compositeDisposable.add(disposable);
    }

}
