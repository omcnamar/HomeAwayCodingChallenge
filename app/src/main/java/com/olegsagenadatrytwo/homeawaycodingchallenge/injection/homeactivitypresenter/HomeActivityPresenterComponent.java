package com.olegsagenadatrytwo.homeawaycodingchallenge.injection.homeactivitypresenter;

import com.olegsagenadatrytwo.homeawaycodingchallenge.view.homeactivity.HomeActivityPresenter;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {HomeActivityPresenterModule.class} )
public interface HomeActivityPresenterComponent {
    void insert(HomeActivityPresenter homeActivityPresenter);
}