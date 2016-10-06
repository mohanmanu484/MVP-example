package com.mohan.internal.mvpexample.retrofit;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohan on 3/10/16.
 */

public class RestClient {

    private static Retrofit retrofit;
    static String URL="http://api.themoviedb.org";

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder    ()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(new ToStringConverterFactory())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }
}

