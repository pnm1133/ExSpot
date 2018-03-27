package com.example.nguyephan.findshopspot.data.respository.local;

import com.example.nguyephan.findshopspot.data.pojo.Response;
import com.example.nguyephan.findshopspot.data.pojo.User;
import com.example.nguyephan.findshopspot.data.pojo.UserResponse;

/**
 * Created by nguye phan on 3/22/2018.
 */

public interface PreferenceCache {

    User getUserFromCache();

    void saveUserToCache(User user);
}
