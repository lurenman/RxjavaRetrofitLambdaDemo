package com.example.administrator.rxjavaretrofitlambdademo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.rxjavaretrofitlambdademo.activity.EasyPermissionsActivity;
import com.example.administrator.rxjavaretrofitlambdademo.activity.EventBusActivity;
import com.example.administrator.rxjavaretrofitlambdademo.activity.RetrofitActivity;
import com.example.administrator.rxjavaretrofitlambdademo.activity.RxjavaActivity;
import com.example.administrator.rxjavaretrofitlambdademo.activity.RxjavaLambdaActivity;
import com.example.administrator.rxjavaretrofitlambdademo.activity.RxjavaRetrofitActivity;
import com.example.administrator.rxjavaretrofitlambdademo.constants.MsgConstants;
import com.example.administrator.rxjavaretrofitlambdademo.entity.MessageClass;
import com.example.administrator.rxjavaretrofitlambdademo.entity.MessageEntity;
import com.example.administrator.rxjavaretrofitlambdademo.utils.Env;
import com.example.administrator.rxjavaretrofitlambdademo.utils.ToastUtils;
import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_Rxjava)
    TextView tv_Rxjava;
    @BindView(R.id.tv_Rxjavalambda)
    TextView tv_Rxjavalambda;
    @BindView(R.id.tv_retrofit)
    TextView tv_Retrofit;
    @BindView(R.id.tv_Rxjavaretrofit)
    TextView tv_Rxjavaretrofit;
    @BindView(R.id.tv_eventbus)
    TextView tv_Eventbus;
    @BindView(R.id.tv_easypermissions)
    TextView tv_Easypermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Env.initSystemBar(this, Color.TRANSPARENT);
       // Observable.just(Env.getVirtualBarHeigh(this)).filter(integer -> integer > 0).subscribe(integer -> setVitualBotton(integer));
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initEnvent();
    }
    private void initEnvent()
    {
        RxView.clicks(tv_Rxjava).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Subscriber<Void>() {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onNext(Void aVoid)
            {
                Intent intent=new Intent(MainActivity.this,RxjavaActivity.class);
                startActivity(intent);
            }
        });
        RxView.clicks(tv_Rxjavalambda).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid)
            {
                Intent intent=new Intent(MainActivity.this,RxjavaLambdaActivity.class);
                startActivity(intent);
            }
        });
                                 //           rxbind+lambda
        RxView.clicks(tv_Retrofit).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(aVoid ->
                startActivity(new Intent(MainActivity.this,RetrofitActivity.class)));
        RxView.clicks(tv_Rxjavaretrofit).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(aVoid ->
                startActivity(new Intent(MainActivity.this,RxjavaRetrofitActivity.class)));
        RxView.clicks(tv_Eventbus).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(aVoid ->
                startActivity(new Intent(MainActivity.this,EventBusActivity.class)));
        RxView.clicks(tv_Easypermissions).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(aVoid ->
                startActivity(new Intent(MainActivity.this,EasyPermissionsActivity.class)));

    }
    //设置虚拟键的便宜量
    private void setVitualBotton(Integer integer)
    {
        findViewById(R.id.root).setPadding(0, 0, 0, integer);
    }
    //这个属性如果设置为private就崩了
//    ThreadMode总共四个：
//    MAIN UI主线程
//    BACKGROUND 后台线程
//    POSTING 和发布者处在同一个线程
//    ASYNC 异步线程
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(MessageClass messageClass)
    {
        ToastUtils.showToast(getApplicationContext(),messageClass.getMessage(),1000);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update1(MessageEntity entity)
    {
        if ( entity.what== MsgConstants.EventBus_CeShi)
        {
            ToastUtils.showToast(getApplicationContext(), entity.obj.toString(),1000);
        }else {
            ToastUtils.showToast(getApplicationContext(),"标识不一样",1000);
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
