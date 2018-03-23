package com.example.nguyephan.findshopspot.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

import dagger.Component;
import dagger.Module;

/**
 * Created by nguye phan on 3/22/2018.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface RepositoryInfo {
}
