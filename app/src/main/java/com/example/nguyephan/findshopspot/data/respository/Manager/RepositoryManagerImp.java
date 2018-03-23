package com.example.nguyephan.findshopspot.data.respository.Manager;

import android.support.annotation.NonNull;

import com.example.nguyephan.findshopspot.data.pojo.Response;
import com.example.nguyephan.findshopspot.data.respository.local.UserRepositoryCache;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by nguye phan on 3/22/2018.
 */
@Singleton
public class RepositoryManagerImp implements RepositoryManager {
    private UserRepositoryCache mUserRepositoryCache;

    @Inject
    public RepositoryManagerImp(@NonNull UserRepositoryCache userRepository){
        this.mUserRepositoryCache = userRepository;
    }

    @Override
    public Response getUserResponseCache() {
        return mUserRepositoryCache.getUserResponseCache();
    }

    @Override
    public Observable<Response> startLoginToCache() {
        return Observable.fromCallable(this::getUserResponseCache);
    }

}
