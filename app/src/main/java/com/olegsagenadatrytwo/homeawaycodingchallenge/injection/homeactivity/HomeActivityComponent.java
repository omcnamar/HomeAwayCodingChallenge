package com.olegsagenadatrytwo.homeawaycodingchallenge.injection.homeactivity;
import com.olegsagenadatrytwo.homeawaycodingchallenge.view.homeactivity.HomeActivity;

import dagger.Component;

@Component(modules = HomeActivityModule.class)
public interface HomeActivityComponent {

    void insert(HomeActivity homeActivity);
}
