package com.example.nguyephan.findshopspot.presentation.start;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.example.nguyephan.findshopspot.R;
import com.example.nguyephan.findshopspot.presentation.base.BaseActivity;
import com.example.nguyephan.findshopspot.presentation.login.LoginFragment;
import com.example.nguyephan.findshopspot.presentation.register.RegisterFragment;
import com.example.nguyephan.findshopspot.ultis.common.CommonFr;

import javax.inject.Inject;

public class StartActivity extends BaseActivity implements StartContact.View,
        LoginFragment.OnFragmentInteractionListener,
        StartFragment.OnFragmentInteractionListener ,
        RegisterFragment.OnFragmentInteractionListener {

    private static final String LOGIN_PAGE_TAG = "loginPage";
    private static final String HOME_PAGE_TAG = "homePage";
    private static final String REGISTER_PAGE_TAG = "registerPage";

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
