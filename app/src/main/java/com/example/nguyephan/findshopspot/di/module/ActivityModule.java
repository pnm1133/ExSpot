package com.example.nguyephan.findshopspot.di.module;

import android.content.Context;

import com.example.nguyephan.findshopspot.di.ActivityContext;
import com.example.nguyephan.findshopspot.di.PerActivity;
import com.example.nguyephan.findshopspot.presentation.base.BaseActivity;
import com.example.nguyephan.findshopspot.presentation.login.LoginContact;
import com.example.nguyephan.findshopspot.presentation.login.LoginPresenter;
import com.example.nguyephan.findshopspot.presentation.register.RegisterFragment;
import com.example.nguyephan.findshopspot.presentation.register.RegisterFragmentContact;
import com.example.nguyephan.findshopspot.presentation.register.RegisterPresenter;
import com.example.nguyephan.findshopspot.presentation.start.StartContact;
import com.example.nguyephan.findshopspot.presentation.start.StartPresenterApp;
import com.example.nguyephan.findshopspot.ultis.scheduler.BaseSchedulerProvider;
import com.example.nguyephan.findshopspot.ultis.scheduler.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by nguye phan on 3/22/2018.
 */
@Module
public class ActivityModule {
    private BaseActivity baseActivity;

    public ActivityModule(BaseActivity activity){
        this.baseActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideActivityContext(){
        return baseActivity;
    }

    @Provides
    @PerActivity
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    BaseSchedulerProvider provideBaseSchedulerProvider(){
        return new SchedulerProvider();
    }

    @Provides
    @PerActivity
    StartContact.Presenter<StartContact.View> provideStartPresenter(StartPresenterApp<StartContact.View> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    LoginContact.Presenter<LoginContact.View> provideLoginPresenter(LoginPresenter<LoginContact.View> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    RegisterFragmentContact.Presenter<RegisterFragmentContact.View> provideRegisterPresenter(RegisterPresenter<RegisterFragmentContact.View> presenter){
        return presenter;
    }

}
