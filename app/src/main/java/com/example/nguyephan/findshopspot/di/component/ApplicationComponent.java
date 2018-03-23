package com.example.nguyephan.findshopspot.di.component;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.nguyephan.findshopspot.FindShopApplication;
import com.example.nguyephan.findshopspot.data.respository.Manager.RepositoryManager;
import com.example.nguyephan.findshopspot.data.respository.local.UserRepositoryCache;
import com.example.nguyephan.findshopspot.di.ApplicationContext;
import com.example.nguyephan.findshopspot.di.RepositoryInfo;
import com.example.nguyephan.findshopspot.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nguye phan on 3/22/2018.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(FindShopApplication findShopApplication);

    @ApplicationContext
    Context getContext();

    UserRepositoryCache getUserRepositoryCache();

    RepositoryManager getRepositoryManager();

    SharedPreferences getSharedPreferences();

    Application getApplication();

}
