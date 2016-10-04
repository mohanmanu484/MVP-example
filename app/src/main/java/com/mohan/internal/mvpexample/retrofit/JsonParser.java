package com.mohan.internal.mvpexample.retrofit;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohan on 3/10/16.
 */

public class JsonParser  implements Callback<String> {

    private static final String TAG = "JsonParser";

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        Log.d(TAG, "onResponse: "+response.body());
        try {
            JSONObject jsonObject=new JSONObject(response.body());
            Log.d(TAG, "onResponse: "+jsonObject.getString("status"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }
}
