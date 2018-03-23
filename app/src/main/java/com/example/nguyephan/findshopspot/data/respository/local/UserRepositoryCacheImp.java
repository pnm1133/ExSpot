package com.example.nguyephan.findshopspot.data.respository.local;

import android.content.Context;

import com.example.nguyephan.findshopspot.data.pojo.Response;
import com.example.nguyephan.findshopspot.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by nguye phan on 3/22/2018.
 */
@Singleton
public class UserRepositoryCacheImp implements UserRepositoryCache {

    private Context context;

    @Inject
    public UserRepositoryCacheImp(@ApplicationContext Context context){
        this.context = context;
    }

    @Override
    public Response getUserResponseCache() {
        // get ShearPreference ==> connect to server account
        Response response = new Response();
        response.setStatus("FAULT");
        return response;
    }

    private void LoginCache(){

    }
}
