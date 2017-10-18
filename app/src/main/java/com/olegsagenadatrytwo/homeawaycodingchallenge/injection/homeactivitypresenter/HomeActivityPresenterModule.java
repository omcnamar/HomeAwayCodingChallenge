package com.olegsagenadatrytwo.homeawaycodingchallenge.injection.homeactivitypresenter;

import com.olegsagenadatrytwo.homeawaycodingchallenge.model.remote.Remote;
import com.olegsagenadatrytwo.homeawaycodingchallenge.view.homeactivity.HomeActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityPresenterModule {

    private HomeActivityPresenter homeActivityPresenter;

    public HomeActivityPresenterModule(HomeActivityPresenter homeActivityPresenter) {
        this.homeActivityPresenter = homeActivityPresenter;
    }

    @Provides
    Remote providesRemote(){
        return new Remote(homeActivityPresenter);
    }
}