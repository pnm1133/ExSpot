package com.example.nguyephan.findshopspot.di.component;

import android.app.Activity;
import android.content.Context;

import com.example.nguyephan.findshopspot.di.ActivityContext;
import com.example.nguyephan.findshopspot.di.PerActivity;
import com.example.nguyephan.findshopspot.di.module.ActivityModule;
import com.example.nguyephan.findshopspot.di.module.ApplicationModule;
import com.example.nguyephan.findshopspot.presentation.base.BaseActivity;
import com.example.nguyephan.findshopspot.presentation.login.LoginFragment;
import com.example.nguyephan.findshopspot.presentation.register.RegisterFragment;
import com.example.nguyephan.findshopspot.presentation.start.StartActivity;
import com.example.nguyephan.findshopspot.presentation.start.StartContact;
import com.example.nguyephan.findshopspot.ultis.scheduler.BaseSchedulerProvider;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by nguye phan on 3/22/2018.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(StartActivity  startActivity);

    void inject(LoginFragment loginFragment);

    void inject(RegisterFragment registerFragment);

}
