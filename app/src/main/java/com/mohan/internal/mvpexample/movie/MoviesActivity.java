package com.mohan.internal.mvpexample.movie;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.mohan.internal.mvpexample.R;
import com.mohan.internal.mvpexample.Utils.ActivityUtils;
import com.mohan.internal.mvpexample.model.MovieRepository;

public class MoviesActivity extends BaseActivity {

    private static final String TAG = "MoviesActivity";

    MoviePresenter mMoviePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MovieFragment moviesFragment =
                (MovieFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (moviesFragment == null) {
            // Create the fragment
            moviesFragment = MovieFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), moviesFragment, R.id.contentFrame);
        }

        // Create the presenter
        mMoviePresenter = new MoviePresenter(
                new MovieRepository(), moviesFragment);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu, menu);
        return true;
    }

}
