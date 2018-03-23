package com.example.nguyephan.findshopspot.ultis.scheduler;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nguye phan on 3/21/2018.
 */

public class SchedulerProvider implements BaseSchedulerProvider {

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler compound() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler main() {
        return AndroidSchedulers.mainThread();
    }
}
