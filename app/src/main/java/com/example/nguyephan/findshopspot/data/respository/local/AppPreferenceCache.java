package com.example.nguyephan.findshopspot.data.respository.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nguyephan.findshopspot.FindShopApplication;
import com.example.nguyephan.findshopspot.data.pojo.Response;
import com.example.nguyephan.findshopspot.data.pojo.User;
import com.example.nguyephan.findshopspot.di.ApplicationContext;
import com.example.nguyephan.findshopspot.di.component.ApplicationComponent;
import com.example.nguyephan.findshopspot.ultis.common.CommonUtils;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by nguye phan on 3/22/2018.
 */
@Singleton
public class AppPreferenceCache implements PreferenceCache {
    private static final String PRE_USER_CACHE_KEY = "user_cache";
    private Context context;
    private ApplicationComponent mApplicationComponent;


    @Inject
    public AppPreferenceCache(@ApplicationContext Context context){
        this.context = context;
        mApplicationComponent = ((FindShopApplication)context).getApplicationComponent();
    }

    @Override
    public User getUserFromCache() {
        String json = getSharedPreferences().getString(PRE_USER_CACHE_KEY,"");
        Gson gson = new Gson();
        User user = gson.fromJson(json,User.class);
        if (user == null){
            return new User();
        }else {
            return user;
        }
    }

    @Override
    public void saveUserToCache(User user) {
        Gson gson = new Gson();
        String save  = gson.toJson(user);
        getSharedPreferences().edit().putString(PRE_USER_CACHE_KEY,save).apply();
    }

    private SharedPreferences getSharedPreferences(){
        return mApplicationComponent.getSharedPreferences();
    }

}
