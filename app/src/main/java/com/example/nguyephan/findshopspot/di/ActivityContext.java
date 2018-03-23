package com.example.nguyephan.findshopspot.di;

/**
 * Created by nguye phan on 3/22/2018.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

import dagger.Component;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {

}
