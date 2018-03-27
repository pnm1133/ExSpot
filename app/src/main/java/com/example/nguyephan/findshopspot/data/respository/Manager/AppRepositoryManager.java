package com.example.nguyephan.findshopspot.data.respository.Manager;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.nguyephan.findshopspot.data.pojo.Response;
import com.example.nguyephan.findshopspot.data.pojo.User;
import com.example.nguyephan.findshopspot.data.respository.api.UserLoginApi;
import com.example.nguyephan.findshopspot.data.respository.local.PreferenceCache;
import com.example.nguyephan.findshopspot.ultis.common.CommonUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by nguye phan on 3/22/2018.
 */
@Singleton
public class AppRepositoryManager implements RepositoryManager {
    private PreferenceCache mPreferenceCache;
    private UserLoginApi mUserLoginApi;

    @Inject
    public AppRepositoryManager(@NonNull PreferenceCache userRepository,
                                @NonNull UserLoginApi userLoginApi){
        this.mPreferenceCache = userRepository;
        this.mUserLoginApi = userLoginApi;
    }

    @Override
    public Response getUserResponseLogin() {
        return mUserLoginApi.getUserResponseLogin();
    }

    @Override
    public User getUserFromCache() {
        return mPreferenceCache.getUserFromCache();
    }

    @Override
    public void saveUserToCache(User user) {
        mPreferenceCache.saveUserToCache(user);
    }

    @Override
    public Observable<Response> startLoginToCache() {
        if(TextUtils.isEmpty(getUserFromCache().getName())){
            Response response = new Response();
            response.setStatus(CommonUtils.STATUS_RESPONSE_FAULT);
            return Observable.just(response);
        }else {
            return Observable.fromCallable(this::getUserResponseLogin);
        }
    }

}
