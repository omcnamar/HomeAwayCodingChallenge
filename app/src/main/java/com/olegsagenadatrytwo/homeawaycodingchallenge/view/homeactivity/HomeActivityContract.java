package com.olegsagenadatrytwo.homeawaycodingchallenge.view.homeactivity;

import com.olegsagenadatrytwo.homeawaycodingchallenge.BasePresenter;
import com.olegsagenadatrytwo.homeawaycodingchallenge.BaseView;
import com.olegsagenadatrytwo.homeawaycodingchallenge.entities.SeatGeek;

public interface HomeActivityContract {

    interface View extends BaseView {
        void updateUI(SeatGeek seatGeek);
    }

    interface Presenter extends BasePresenter<View> {
        void attachRemote();
        void makeRestCallForEvents(String query);
    }

}
