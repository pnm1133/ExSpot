package com.example.nguyephan.findshopspot.data.respository.Manager;

import com.example.nguyephan.findshopspot.data.pojo.Response;
import com.example.nguyephan.findshopspot.data.pojo.UserResponse;
import com.example.nguyephan.findshopspot.data.respository.local.UserRepositoryCache;

import io.reactivex.Observable;

/**
 * Created by nguye phan on 3/22/2018.
 */

public interface RepositoryManager extends UserRepositoryCache {

    Observable<Response> startLoginToCache();

}
