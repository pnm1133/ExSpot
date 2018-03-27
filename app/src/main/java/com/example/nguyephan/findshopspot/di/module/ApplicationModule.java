package com.example.nguyephan.findshopspot.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.nguyephan.findshopspot.data.respository.Manager.RepositoryManager;
import com.example.nguyephan.findshopspot.data.respository.Manager.AppRepositoryManager;
import com.example.nguyephan.findshopspot.data.respository.api.AppUserLoginApi;
import com.example.nguyephan.findshopspot.data.respository.api.UserLoginApi;
import com.example.nguyephan.findshopspot.data.respository.local.PreferenceCache;
import com.example.nguyephan.findshopspot.data.respository.local.AppPreferenceCache;
import com.example.nguyephan.findshopspot.di.ApplicationContext;
import com.example.nguyephan.findshopspot.ultis.common.CommonUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

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
    PreferenceCache provideUserRepositoryCache(AppPreferenceCache appPreferenceCache){
        return appPreferenceCache;
    }

    @Provides
    @Singleton
    RepositoryManager provideRepositoryManager(AppRepositoryManager repositoryManager){
        return repositoryManager;
    }

    @Provides
    @Singleton
    UserLoginApi provideUserLoginApi(){
        return new AppUserLoginApi();
    }

    @Provides
    Application provideApplication(){
        return application;
    }

    @Provides
    CalligraphyConfig provideCalligraphyConfig(){
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SourceSansPro-Regular.ttf")
                .setFontAttrId(uk.co.chrisjenx.calligraphy.R.attr.fontPath)
                .build();
    }

}
