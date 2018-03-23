package com.example.nguyephan.findshopspot.presentation.start;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nguyephan.findshopspot.R;
import com.example.nguyephan.findshopspot.data.pojo.UserResponse;
import com.example.nguyephan.findshopspot.data.respository.Manager.RepositoryManager;
import com.example.nguyephan.findshopspot.data.respository.Manager.RepositoryManagerImp;
import com.example.nguyephan.findshopspot.data.respository.local.UserRepositoryCacheImp;
import com.example.nguyephan.findshopspot.presentation.base.BaseActivity;
import com.example.nguyephan.findshopspot.presentation.base.BaseView;
import com.example.nguyephan.findshopspot.presentation.login.LoginFragment;
import com.example.nguyephan.findshopspot.ultis.scheduler.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class StartActivity extends BaseActivity implements StartContact.View,
        LoginFragment.OnFragmentInteractionListener,
        StartFragment.OnFragmentInteractionListener  {

    private static final String LOGIN_PAGE_TAG = "loginPage";
    private static final String HOME_PAGE_TAG = "homePage";

    @Inject
    StartContact.Presenter<StartContact.View> presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        getActivityComponent().inject(this);

        initPresenter();
    }

    private void initPresenter(){

        presenter.onAttach(this);

        if(isInternetConnection()){
            presenter.startLogin();
        }else {
            errorInternetConnection();
        }
    }

    @Override
    public void replaceLoginPage() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, LoginFragment.newInstance(),LOGIN_PAGE_TAG);
        transaction.commit();
    }

    @Override
    public void replaceHomePage() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,StartFragment.newInstance(),HOME_PAGE_TAG);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
