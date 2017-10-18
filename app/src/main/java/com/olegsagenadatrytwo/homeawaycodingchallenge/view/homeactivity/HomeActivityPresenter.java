package com.olegsagenadatrytwo.homeawaycodingchallenge.view.homeactivity;

import com.olegsagenadatrytwo.homeawaycodingchallenge.entities.SeatGeek;
import com.olegsagenadatrytwo.homeawaycodingchallenge.injection.homeactivitypresenter.DaggerHomeActivityPresenterComponent;
import com.olegsagenadatrytwo.homeawaycodingchallenge.injection.homeactivitypresenter.HomeActivityPresenterModule;
import com.olegsagenadatrytwo.homeawaycodingchallenge.model.remote.IRemote;
import com.olegsagenadatrytwo.homeawaycodingchallenge.model.remote.Remote;

import javax.inject.Inject;

public class HomeActivityPresenter implements HomeActivityContract.Presenter, IRemote{

    @Inject
    Remote remote;
    private HomeActivityContract.View view;

    @Override
    public void attachView(HomeActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void attachRemote(){
        DaggerHomeActivityPresenterComponent
                .builder()
                .homeActivityPresenterModule(new HomeActivityPresenterModule(this))
                .build()
                .insert(this);
    }

    @Override
    public void makeRestCallForEvents(String query) {
        remote.getEvents(query);
    }

    @Override
    public void sendData(final SeatGeek seatGeek) {
        ((HomeActivity)view).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.updateUI(seatGeek);
            }
        });

    }
}
