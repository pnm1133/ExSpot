package com.example.nguyephan.findshopspot.data.respository.Manager;

import com.example.nguyephan.findshopspot.data.pojo.Response;
import com.example.nguyephan.findshopspot.data.pojo.User;
import com.example.nguyephan.findshopspot.data.respository.api.UserLoginApi;
import com.example.nguyephan.findshopspot.data.respository.local.PreferenceCache;

import io.reactivex.Observable;

/**
 * Created by nguye phan on 3/22/2018.
 */

public interface RepositoryManager extends PreferenceCache,UserLoginApi {

    Observable<Response> startLoginToCache();

}
