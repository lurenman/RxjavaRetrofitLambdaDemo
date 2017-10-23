package com.example.administrator.rxjavaretrofitlambdademo.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.rxjavaretrofitlambdademo.utils.Env;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public abstract class BaseActivity extends SwipeBackActivity
{
    protected Context mContext;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext=this;
        Env.initSystemBar(this, Color.TRANSPARENT);
        //发现下面设不设置都无所谓的
        SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
        int flag4 = SwipeBackLayout.EDGE_LEFT;    //全部
        //设置滑动模式
        mSwipeBackLayout.setEdgeTrackingEnabled(flag4);
        mSwipeBackLayout.setEnableGesture(true);
        initVariables();
        initViews();
        initEnvent();
        loadData();
    }

    protected abstract void initVariables();

    protected abstract void initViews();

    protected abstract void loadData();
    protected void initEnvent()
    {

    }

}
