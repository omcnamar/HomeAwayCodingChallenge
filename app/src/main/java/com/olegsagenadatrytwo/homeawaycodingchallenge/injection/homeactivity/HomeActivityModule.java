package com.olegsagenadatrytwo.homeawaycodingchallenge.injection.homeactivity;

import com.olegsagenadatrytwo.homeawaycodingchallenge.view.homeactivity.HomeActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {

    public HomeActivityModule() {
    }

    @Provides
    HomeActivityPresenter providesHomeActivityPresenter(){
        return new HomeActivityPresenter();
    }
}
