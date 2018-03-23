package com.example.nguyephan.findshopspot;

import android.app.Application;

import com.example.nguyephan.findshopspot.data.respository.Manager.RepositoryManager;
import com.example.nguyephan.findshopspot.di.component.ApplicationComponent;
import com.example.nguyephan.findshopspot.di.component.DaggerApplicationComponent;
import com.example.nguyephan.findshopspot.di.module.ApplicationModule;

import javax.inject.Inject;


/**
 * Created by nguye phan on 3/22/2018.
 */

public class FindShopApplication extends Application {

    private ApplicationComponent mApplicationComponent;

//    @Inject
//    RepositoryManager mRepositoryManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }


}
