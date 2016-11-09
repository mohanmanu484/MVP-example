package com.mohan.internal.mvpexample;

import com.google.common.collect.Lists;
import com.mohan.internal.mvpexample.model.MovieDataSource;
import com.mohan.internal.mvpexample.model.MovieRepository;
import com.mohan.internal.mvpexample.movie.MoviePresenter;
import com.mohan.internal.mvpexample.movie.MoviesContract;
import com.mohan.internal.mvpexample.pojo.Movie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MovieUnitTest {

    private static List<Movie> MOVIES;

    @Mock
    private MovieRepository mMovieRepository;

    @Mock
    private MoviesContract.View mMoviesView;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<MovieDataSource.LoadMoviesCallback> mLoadMoviesCallbackCaptor;

    private MoviePresenter mMoviesPresenter;


    @Before
    public void setupTasksPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mMoviesPresenter = new MoviePresenter(mMovieRepository, mMoviesView);

        // The presenter won't update the view unless it's active.
        when(mMoviesView.isActive()).thenReturn(true);

        // We start the tasks to 3, with one active and two completed
        MOVIES = Lists.newArrayList(new Movie("movie1",1,"test1"),
                new Movie("movie2",2,"test2"), new Movie("movie3",3,"test3"));
    }

    @Test
    public void loadCompletedMoviesFromNetworkAndLoadIntoView() {
        // Given an initialized TasksPresenter with initialized tasks
        // When loading of Tasks is requested


        mMoviesPresenter.refreshMovies();


        // Callback is captured and invoked with stubbed tasks
        verify(mMovieRepository).getMovies(mLoadMoviesCallbackCaptor.capture());
        mLoadMoviesCallbackCaptor.getValue().onMoviesLoaded(MOVIES);

        ArgumentCaptor<List> showMoviesArgumentCaptor = ArgumentCaptor.forClass(List.class);
       verify(mMoviesView).showMovies(showMoviesArgumentCaptor.capture());
        assertTrue(showMoviesArgumentCaptor.getValue().size() == 3);
    }


    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}