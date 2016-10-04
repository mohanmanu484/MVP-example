package com.mohan.internal.mvpexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.mohan.internal.mvpexample.pojo.Movie;
import com.mohan.internal.mvpexample.pojo.MoviesResponse;
import com.mohan.internal.mvpexample.retrofit.NetworkCall;
import com.mohan.internal.mvpexample.retrofit.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void getMovies(View view){
        Retrofit retrofit = RestClient.getClient();
        String apiToken = "66731d2e5d5e953395193ec20af94cac";

        NetworkCall networkCall = retrofit.create(NetworkCall.class);
       /* networkCall.test(apiToken).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse: "+response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                t.printStackTrace();
            }
        });*/
        networkCall.getMovies(apiToken).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movieList=response.body().getResults();
                for (int i = 0; i < movieList.size(); i++) {
                   Movie movie= movieList.get(i);
                    Log.d(TAG, "onResponse: "+movie.getTitle());
                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

                t.printStackTrace();
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }
}
