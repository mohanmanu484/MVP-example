package com.mohan.internal.mvpexample.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.mohan.internal.mvpexample.pojo.Movie;
import com.mohan.internal.mvpexample.pojo.MoviesResponse;
import com.mohan.internal.mvpexample.retrofit.NetworkCall;
import com.mohan.internal.mvpexample.retrofit.RestClient;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by mohan on 4/10/16.
 */

public class MovieRepository implements MovieDataSource {

    private static final String TAG = "MovieRepository";

    @Override
    public void getMovies(@NonNull LoadMoviesCallback callback) {
        loadMoviesFromNetwork(callback);
    }

    @Override
    public void getMovie(@NonNull String taskId, @NonNull GetMovieCallback callback) {

    }

    public void loadMoviesFromNetwork(@NonNull final LoadMoviesCallback callback) {
        Retrofit retrofit = RestClient.getClient();
        String apiToken = "";  // replace with yor api key of moviesdb

        final NetworkCall networkCall = retrofit.create(NetworkCall.class);
        networkCall.getMovies(apiToken).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    List<Movie> movieList = response.body().getResults();
                    if (movieList.isEmpty()) {
                        callback.onDataNotAvailable();
                    } else {
                        callback.onMoviesLoaded(movieList);
                    }
                } else {
                    try {
                        callback.onNetworkError(response.errorBody().string());
                    } catch (IOException e) {
                        callback.onNetworkError("Unknown error");
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

                callback.onNetworkError(t.getMessage());
                t.printStackTrace();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
