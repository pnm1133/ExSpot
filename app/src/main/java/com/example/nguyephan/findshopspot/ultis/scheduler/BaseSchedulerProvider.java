package com.example.nguyephan.findshopspot.ultis.scheduler;

import io.reactivex.Scheduler;

/**
 * Created by nguye phan on 3/21/2018.
 */

public interface BaseSchedulerProvider {

    Scheduler io();

    Scheduler compound();

    Scheduler main();
}
