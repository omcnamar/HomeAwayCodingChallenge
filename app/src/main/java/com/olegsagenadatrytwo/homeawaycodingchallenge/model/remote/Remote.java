package com.olegsagenadatrytwo.homeawaycodingchallenge.model.remote;

import com.olegsagenadatrytwo.homeawaycodingchallenge.entities.SeatGeek;

public class Remote {

    private IRemote iremote;

    public Remote(IRemote iremote){
        this.iremote = iremote;
    }

    public void getEvents(String query){
        retrofit2.Call<SeatGeek> weatherDataCall = RetrofitHelper.getEventsCall(query);
        weatherDataCall.enqueue(new retrofit2.Callback<SeatGeek>() {
            @Override
            public void onResponse(retrofit2.Call<SeatGeek> call, retrofit2.Response<SeatGeek> response) {
                SeatGeek seatGeek = response.body();
                iremote.sendData(seatGeek);
            }

            @Override
            public void onFailure(retrofit2.Call<SeatGeek> call, Throwable t) {
                iremote.sendData(null);
            }
        });
    }

}
