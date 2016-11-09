package com.mohan.internal.mvpexample.movie;

import android.support.annotation.NonNull;

import com.mohan.internal.mvpexample.model.MovieDataSource;
import com.mohan.internal.mvpexample.model.MovieRepository;
import com.mohan.internal.mvpexample.pojo.Movie;

import java.util.List;


/**
 * Created by mohan on 4/10/16.
 */

public class MoviePresenter implements MoviesContract.Presenter {

    private final MovieRepository mMovieRepository;
    private final MoviesContract.View mMovieView;

    public MoviePresenter(@NonNull MovieRepository movieRepository, @NonNull MoviesContract.View movieView) {

        if (null == movieRepository) {
            throw new IllegalArgumentException("movieRepository cannot be null");
        }
        if (null == movieView) {
            throw new IllegalArgumentException("movieView cannot be null!");
        }
        mMovieRepository = movieRepository;
        mMovieView = movieView;

        mMovieView.setPresenter(this);
    }

    @Override
    public void refreshMovies() {
        loadMovies();
    }

    @Override
    public void start() {
        mMovieView.setTitle("Movies List");
        loadMovies();
    }



    private void loadMovies() {
        mMovieView.showProgress();
        mMovieRepository.getMovies(new MovieDataSource.LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {

                if(!mMovieView.isActive()){
                    return;
                }

                mMovieView.hideProgress();
                mMovieView.showMovies(movies);
            }

            @Override
            public void onDataNotAvailable() {

                if(!mMovieView.isActive()){
                    return;
                }
                mMovieView.hideProgress();
            }

            @Override
            public void onNetworkError(String message) {

                if(!mMovieView.isActive()){
                    return;
                }
                mMovieView.hideProgress();
                mMovieView.showNetworkError(message);
            }
        });
    }
}
