package com.example.nguyephan.findshopspot.presentation.base;

/**
 * Created by nguye phan on 3/21/2018.
 */

public interface BasePresenterItf<V extends BaseView> {

    void onAttach(V v);

    void onDetach();

    boolean isAttachView();
}
