package com.mohan.internal.mvpexample.movie;

import com.mohan.internal.mvpexample.pojo.Movie;

import java.util.List;

/**
 * Created by mohan on 4/10/16.
 */

public interface MoviesContract {


    interface View extends BaseView<Presenter>{
        void showProgress();
        void hideProgress();
        void setTitle(String title);
        void showMovies(List<Movie> movies);
        boolean isActive();
        void showNetworkError(String message);
    }
    interface Presenter extends BasePresenter{
        void refreshMovies();
    }


}
