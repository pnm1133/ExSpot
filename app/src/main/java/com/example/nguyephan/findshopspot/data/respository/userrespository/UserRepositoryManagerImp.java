package com.example.nguyephan.findshopspot.data.respository.userrespository;

import android.support.annotation.NonNull;

import com.example.nguyephan.findshopspot.data.pojo.User;

import io.reactivex.Observable;

/**
 * Created by nguye phan on 3/22/2018.
 */

public class UserRepositoryManagerImp implements UserRepositoryManager {
    private UserRepository userRepository;

    public UserRepositoryManagerImp(@NonNull UserRepository userRepository){

    }

    @Override
    public Observable<Boolean> userCache() {
        return null;
    }
}
