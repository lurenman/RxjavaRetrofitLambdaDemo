package com.example.administrator.rxjavaretrofitlambdademo.activity;

import android.widget.TextView;

import com.example.administrator.rxjavaretrofitlambdademo.R;
import com.example.administrator.rxjavaretrofitlambdademo.entity.MessageEntity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class EventBusActivity extends BaseActivity {
    @BindView(R.id.tv_enventbus)
    TextView tv_Enventbus;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_eventbus);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.tv_enventbus)
    public void onViewClicked() {
//        MessageClass messageClass=new MessageClass();
//        messageClass.setMessage("haha");
//        EventBus.getDefault().post(messageClass);
        MessageEntity messageEntity = MessageEntity.obtianMessage();
        messageEntity.what = 1;
        messageEntity.obj = "EventBus";
        EventBus.getDefault().post(messageEntity);

        // this.finish();
    }
}
