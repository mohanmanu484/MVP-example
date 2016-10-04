package com.mohan.internal.mvpexample.retrofit;

import com.mohan.internal.mvpexample.pojo.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by mohan on 2/10/16.
 */

public interface NetworkCall {

    @Headers({
            "Accept: application/vnd.myapp.v1+json",
    })
    @GET("/api/patient/records")
    Call<String> makeCall(@Query("api_token") String apiToken);

    @GET()
    Call<String> universalCall(@Url String url);

    @GET("3/movie/top_rated")
    Call<String> test(@Query("api_key") String apiKey);

    @GET("3/movie/top_rated")
    Call<MoviesResponse> getMovies(@Query("api_key") String apiKey);
}
