package com.example.administrator.rxjavaretrofitlambdademo.activity;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.rxjavaretrofitlambdademo.R;
import com.example.administrator.rxjavaretrofitlambdademo.entity.flatMapClass;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class RxjavaActivity extends BaseActivity
{

    @BindView(R.id.tv_map)
    TextView tv_Map;
    @BindView(R.id.tv_flatMap)
    TextView tv_FlatMap;
    @BindView(R.id.tv_filter)
    TextView tv_Filter;
    @BindView(R.id.tv_thread)
    TextView tv_Thread;
    private flatMapClass mFlatMapClass;

    @Override
    protected void initVariables()
    {
        mFlatMapClass=new flatMapClass();
        mFlatMapClass.setFlat("flatMap转换");
    }

    @Override
    protected void initViews()
    {
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind((Activity) mContext);

    }

    @Override
    protected void initEnvent()
    {
        super.initEnvent();
        RxView.clicks(tv_Map).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid)
            {
               Observable.just(64).map(new Func1<Integer, String>() {
                   @Override
                   public String call(Integer integer)
                   {
                       String str="map转换："+Integer.toString(integer);
                       return str;
                   }
               }).subscribe(new Action1<String>() {
                   @Override
                   public void call(String s)
                   {
                       Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                   }
               });
//              Observable.just(64).map(integer ->"map转换："+Integer.toString(integer)).subscribe(s ->
//                      Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show());
            }
        });
        RxView.clicks(tv_FlatMap).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid)
            {
                Observable.just(mFlatMapClass).flatMap(new Func1<flatMapClass, Observable<String>>() {
                    @Override
                    public Observable<String> call(flatMapClass flatMapClass)
                    {
                        return Observable.just(flatMapClass.getFlat().toString());
                    }
                }).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s)
                    {
                        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                    }
                });
//                Observable.just(mFlatMapClass).flatMap(flatMapClass ->Observable.just(flatMapClass.getFlat().toString())).subscribe(s ->
//                        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show());
            }
        });
        //感觉这个filter就像做一个判断一样
        RxView.clicks(tv_Filter).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid)
            {
               Observable.just(10).filter(new Func1<Integer, Boolean>() {
                   @Override
                   public Boolean call(Integer integer)
                   {
                       return integer>0;
                   }
               }).subscribe(new Action1<Integer>() {
                   @Override
                   public void call(Integer integer)
                   {
                       Toast.makeText(getApplicationContext(),"filter过滤"+integer.toString(integer),Toast.LENGTH_SHORT).show();
                   }
               });
//                Observable.just(10).filter(integer -> integer>0).subscribe(integer ->
//                        Toast.makeText(getApplicationContext(),"filter过滤"+integer.toString(integer),Toast.LENGTH_SHORT).show());
            }
        });
        RxView.clicks(tv_Thread).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid)
            {
                Observable.just(1,2,3,4).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer)
                            {
                                Toast.makeText(getApplicationContext(),"线程控制:"+integer.toString(integer),Toast.LENGTH_SHORT).show();
                            }
                        });
/*                Observable.just(1,2,3,4).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(integer ->
                                Toast.makeText(getApplicationContext(),"线程控制:"+integer.toString(integer),Toast.LENGTH_SHORT).show());*/
            }
        });

    }

    @Override
    protected void loadData()
    {

    }
}
