package com.example.administrator.rxjavaretrofitlambdademo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.rxjavaretrofitlambdademo.R;
import com.example.administrator.rxjavaretrofitlambdademo.adapter.HzListViewAdapter;
import com.example.administrator.rxjavaretrofitlambdademo.entity.HzDataClass;
import com.example.administrator.rxjavaretrofitlambdademo.retrofit.DAL;
import com.example.administrator.rxjavaretrofitlambdademo.rqentity.rqHzClass;
import com.example.administrator.rxjavaretrofitlambdademo.view.CommonDialog;
import com.example.administrator.rxjavaretrofitlambdademo.view.RefreshLayout_Listview;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class RetrofitActivity extends BaseActivity {

    @BindView(R.id.lv_list)
    ListView lv_List;
    @BindView(R.id.swipe_layout)
    RefreshLayout_Listview swipe_Layout;
    @BindView(R.id.tv_upload)
    TextView tv_Upload;
    private HzListViewAdapter mHzListViewAdapter;
    List<HzDataClass.DataBean> mDataArray = new ArrayList<HzDataClass.DataBean>();
    private rqHzClass rqHz;

    @Override
    protected void initVariables() {
        rqHz = new rqHzClass();
        rqHz.setPageIndex("0");
        rqHz.setPageSize("5");
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind((Activity) mContext);
        swipe_Layout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mHzListViewAdapter = new HzListViewAdapter(mContext, mDataArray);
        lv_List.setAdapter(mHzListViewAdapter);

    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        swipe_Layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stopRefreshing();
                    }
                }, 3000);

            }
        });
        // 加载监听器
        swipe_Layout.setOnLoadListener(new RefreshLayout_Listview.OnLoadListener() {

            @Override
            public void onLoad() {
                //加载更多
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stopRefreshing();
                    }
                }, 3000);

            }
        });
        RxView.clicks(tv_Upload).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
//                Intent intent=new Intent(mContext,RetrofitUploadActivity.class);
//                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {
        CommonDialog dialog = new CommonDialog(RetrofitActivity.this, "加载中");
        dialog.show();
         Call<HzDataClass> call = DAL.shareDAL.baseAPIService().getCooperateBusiness(rqHz);
        //Call<HzDataClass> call = DAL.shareDAL.baseAPIService().getCooperateBusiness1("0", "5");
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("pageIndex", "0");
//        map.put("PageSize", "5");
       // Call<HzDataClass> call = DAL.shareDAL.baseAPIService().getCooperateBusiness3(map);

        call.enqueue(new Callback<HzDataClass>() {
            @Override
            public void onResponse(Call<HzDataClass> call, Response<HzDataClass> response) {
                HzDataClass body = response.body();
                List<HzDataClass.DataBean> data = body.getData();
                if (data != null && !data.isEmpty()) {
                    mDataArray.clear();
                    mDataArray.addAll(data);
                }
                mHzListViewAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<HzDataClass> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });


    }

    private void stopRefreshing() {
        if (swipe_Layout.isRefreshing())
            swipe_Layout.setRefreshing(false);
        if (swipe_Layout.isLoading) {
            swipe_Layout.setLoading(false);
        }
    }
}
