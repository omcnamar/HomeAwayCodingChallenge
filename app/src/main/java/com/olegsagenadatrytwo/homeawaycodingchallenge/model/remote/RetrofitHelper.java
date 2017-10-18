package com.olegsagenadatrytwo.homeawaycodingchallenge.model.remote;

import com.olegsagenadatrytwo.homeawaycodingchallenge.entities.SeatGeek;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


class RetrofitHelper {
    private static final String BASE_URL = "https://api.seatgeek.com/";
    private static final String PATH = "2/events";
    private static final String CLIENT_ID = "OTMwNDA5OHwxNTA4MzAxNDc1LjIx";
    private static final String CLIENT_SECRET = "bf36bf115948748dd3e92c1a0d98028ded4925c91ce21d70bd77ed20709499ad";

    private static Retrofit create(){

        //create a logging interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //create a custom client to add the interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    static Call<SeatGeek> getEventsCall(String query){
        Retrofit retrofit = create();
        EventService eventService = retrofit.create(EventService.class);
        return eventService.getEventData(CLIENT_ID, CLIENT_SECRET, query);
    }

    interface EventService{

        @GET(PATH)
        Call<SeatGeek> getEventData(@Query("client_id") String clientId,
                                    @Query("client_secret") String clientSecret,
                                    @Query("q") String query);
    }

}
