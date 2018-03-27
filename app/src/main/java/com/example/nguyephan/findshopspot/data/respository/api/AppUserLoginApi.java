package com.example.nguyephan.findshopspot.data.respository.api;

import com.example.nguyephan.findshopspot.data.pojo.Response;
import com.example.nguyephan.findshopspot.ultis.common.CommonUtils;

/**
 * Created by nguye phan on 3/23/2018.
 */

public class AppUserLoginApi implements UserLoginApi {

    @Override
    public Response getUserResponseLogin() {
        Response response = new Response();
        response.setStatus(CommonUtils.STATUS_RESPONSE_OK);
        return response;
    }
}
