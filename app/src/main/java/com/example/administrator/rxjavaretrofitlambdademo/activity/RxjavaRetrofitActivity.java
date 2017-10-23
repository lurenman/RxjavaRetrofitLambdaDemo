package com.example.administrator.rxjavaretrofitlambdademo.activity;

import android.app.Activity;


import com.example.administrator.rxjavaretrofitlambdademo.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class RxjavaRetrofitActivity extends BaseActivity
{

    @Override
    protected void initVariables()
    {

    }

    @Override
    protected void initViews()
    {
        setContentView(R.layout.activity_rxjavaretrofit);
        ButterKnife.bind((Activity) mContext);
    }

    @Override
    protected void loadData()
    {

    }
}
