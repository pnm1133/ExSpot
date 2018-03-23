package com.example.nguyephan.findshopspot.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.nguyephan.findshopspot.data.respository.Manager.RepositoryManager;
import com.example.nguyephan.findshopspot.data.respository.Manager.RepositoryManagerImp;
import com.example.nguyephan.findshopspot.data.respository.local.UserRepositoryCache;
import com.example.nguyephan.findshopspot.data.respository.local.UserRepositoryCacheImp;
import com.example.nguyephan.findshopspot.data.respository.userrespository.UserRepository;
import com.example.nguyephan.findshopspot.di.ApplicationContext;
import com.example.nguyephan.findshopspot.di.RepositoryInfo;
import com.example.nguyephan.findshopspot.ultis.common.CommonUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nguye phan on 3/22/2018.
 */
@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application){
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return application;
    }

    @Provides
    SharedPreferences provideSharedPreference(){
        return application.getSharedPreferences(CommonUtils.PRE_NAME,Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    UserRepositoryCache provideUserRepositoryCache(UserRepositoryCacheImp userRepositoryCacheImp){
        return userRepositoryCacheImp;
    }

    @Provides
    @Singleton
    RepositoryManager provideRepositoryManager(RepositoryManagerImp repositoryManager){
        return repositoryManager;
    }

    @Provides
    Application provideApplication(){
        return application;
    }

}
