package com.mohan.internal.mvpexample.movie;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import com.mohan.internal.mvpexample.R;

/**
 * Created by mohan on 5/10/16.
 */

public class BaseFragment extends Fragment {

    ProgressDialog progressDialog;

    void showProgress(){
        if(progressDialog==null){
            progressDialog=new ProgressDialog(getContext());
        }
        progressDialog.setTitle(getContext().getString(R.string.please_wait));
        progressDialog.show();

    }

    void hideProgress(){

        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }
}
