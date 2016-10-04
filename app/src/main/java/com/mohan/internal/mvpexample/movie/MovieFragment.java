package com.mohan.internal.mvpexample.movie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mohan.internal.mvpexample.R;
import com.mohan.internal.mvpexample.pojo.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan on 4/10/16.
 */

public class MovieFragment extends BaseFragment implements MoviesContract.View {

    private MoviesContract.Presenter mPresenter;
    private ActionBar mActionBar;
    private MoviesAdapter moviesAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviesAdapter=new MoviesAdapter(new ArrayList<Movie>(0));
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.movie_list_fragment_layout,container,false);
        ListView moviesList= (ListView) view.findViewById(R.id.lvMovies);
        moviesList.setAdapter(moviesAdapter);
        return view;
    }

    @Override
    public void showProgress() {
        super.showProgress();
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
    }

    @Override
    public void setTitle(String title) {
        ((AppCompatActivity)getContext()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        moviesAdapter.replaceMovies(movies);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showNetworkError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(@NonNull MoviesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static MovieFragment newInstance() {
        return new MovieFragment();
    }


    public class MoviesAdapter extends BaseAdapter{

        List<Movie> movieList;

        public MoviesAdapter(List<Movie> movieList) {
            this.movieList = movieList;
        }

        void replaceMovies(List<Movie> movieList){
            this.movieList=movieList;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return movieList.size();
        }

        @Override
        public Object getItem(int i) {
            return movieList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return ((Movie)movieList.get(i)).getId();
        }

        @Override
        public View getView(int i, View converterView, ViewGroup viewGroup) {
            View view=converterView;
            if(view==null){
                LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
                view=inflater.inflate(R.layout.single_movie_list,viewGroup,false);
            }
            TextView heading= (TextView) view.findViewById(R.id.tvHeading);
            TextView desc= (TextView) view.findViewById(R.id.tvDescription);
            Movie movie=movieList.get(i);
            heading.setText(movie.getTitle());
            desc.setText(movie.getOverview());
            return view;
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.refresh:
                mPresenter.refreshMovies();
                break;
        }
        return true;
    }
}
