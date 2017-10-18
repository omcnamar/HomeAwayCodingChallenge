package com.olegsagenadatrytwo.homeawaycodingchallenge;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);
    void removeView();

}
