package com.mohan.internal.mvpexample.model;

import android.support.annotation.NonNull;

import com.mohan.internal.mvpexample.pojo.Movie;

import java.util.List;

/**
 * Created by mohan on 4/10/16.
 */

public interface MovieDataSource {

    interface LoadMoviesCallback {

        void onMoviesLoaded(List<Movie> movies);

        void onDataNotAvailable();

        void onNetworkError(String message);
    }

    interface GetMovieCallback {

        void onMovieLoaded(Movie movie);

        void onDataNotAvailable();
    }

    void getMovies(@NonNull LoadMoviesCallback callback);

    void getMovie(@NonNull String movieId, @NonNull GetMovieCallback callback);
}
