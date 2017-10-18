package com.olegsagenadatrytwo.homeawaycodingchallenge.model.remote;

import android.util.Log;

import com.olegsagenadatrytwo.homeawaycodingchallenge.entities.SeatGeek;

public class Remote {

    public static final String TAG = "Remote";
    private IRemote iremote;

    public Remote(IRemote iremote){
        this.iremote = iremote;
    }

    public void getEvents(String query){
        retrofit2.Call<SeatGeek> weatherDataCall = RetrofitHelper.getEventsCall(query);
        weatherDataCall.enqueue(new retrofit2.Callback<SeatGeek>() {
            @Override
            public void onResponse(retrofit2.Call<SeatGeek> call, retrofit2.Response<SeatGeek> response) {
                Log.d(TAG, "onResponse: ");
                SeatGeek seatGeek = response.body();
                iremote.sendData(seatGeek);
            }

            @Override
            public void onFailure(retrofit2.Call<SeatGeek> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                iremote.sendData(null);
            }
        });
    }

}
